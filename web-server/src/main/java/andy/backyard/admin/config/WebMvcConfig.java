package andy.backyard.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.core.XHTMLOutputFormat;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.TemplateException;
import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
//@EnableWebMvc // Adding this annotation to an @Configuration class imports the Spring MVC configuration from WebMvcConfigurationSupport
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 如果启用  @EnableWebMvc, 需要打开下面注释掉的行
        //converters.add(converter());
    }

    /**
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        // 设置根路径url  / 等同于 /index
        registry.addViewController("/").setViewName("forward:/index");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // 配置静态文件的路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/web/static/");
    }

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    @Primary
    MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    //////////////////// 以下区域是 Freemarker 相关的配置 ////////////////////
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Autowired
    @Bean
    public FreeMarkerConfig springFreeMarkerConfig(ServletContext servletContext) throws IOException, TemplateException {
        FreeMarkerConfigurer freemarkerConfig = configFreeMarkerConfigurer(servletContext);
        freemarker.template.Configuration configuration = freemarkerConfig.getConfiguration();
        TaglibFactory taglibFactory = taglibFactory(freemarkerConfig);
        return new MyFreeMarkerConfig(configuration, taglibFactory);
    }

    private TaglibFactory taglibFactory(FreeMarkerConfigurer freemarkerConfig) throws IOException, TemplateException {
        TaglibFactory taglibFactory = freemarkerConfig.getTaglibFactory();
        taglibFactory.setObjectWrapper(freemarker.template.Configuration.getDefaultObjectWrapper(freemarker.template.Configuration.getVersion()));
        return taglibFactory;
    }

    private static FreeMarkerConfigurer configFreeMarkerConfigurer(ServletContext servletContext) throws IOException,
            TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:/web/templates/");
        factory.setDefaultEncoding("UTF-8");
        Map<String, Object> variables = new HashMap<String, Object>();
        factory.setFreemarkerVariables(variables);

        ServletContext servletContextProxy = (ServletContext) Proxy.newProxyInstance(
                ServletContextResourceHandler.class.getClassLoader(),
                new Class<?>[]{ServletContext.class},
                new ServletContextResourceHandler(servletContext));
        freeMarkerConfigurer.setServletContext(servletContextProxy);


        freemarker.template.Configuration cfg = factory.createConfiguration();
        cfg.setNumberFormat("0.######");
        cfg.setDateFormat("yyyy-MM-dd");
        cfg.setTimeFormat("hh:mm:ss");
        cfg.setDateTimeFormat("yyyy-MM-dd hh:mm:ss");
        cfg.setOutputFormat(XHTMLOutputFormat.INSTANCE);
        //1. Java 8 date and time api (jsr310) support , third party library, not freemarker official offers : https://github.com/amedia/freemarker-java-8
        // when freemarker official support is ready, will use freemarker official support. 要求 v2.3.25 以上
        //2. 对 interface default methods 的支持要求 v2.3.26 以上
        cfg.setObjectWrapper(new Java8ObjectWrapper(freemarker.template.Configuration.VERSION_2_3_26));


        Properties settings = new Properties();
        settings.put("default_encoding", "UTF-8");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        freeMarkerConfigurer.afterPropertiesSet();
        freeMarkerConfigurer.setConfiguration(cfg);

        return freeMarkerConfigurer;
    }

    /**
     * 定制该类后，才能在 freemarker 模板文件中使用
     * <#assign security=JspTaglibs["/META-INF/security.tld"] />,
     * 参考资料 : http://moradanen.sopovs.com/2014/02/freemarker-loading-taglibs-from-classpath.html
     */
    private static class ServletContextResourceHandler implements InvocationHandler {

        private final ServletContext target;

        private ServletContextResourceHandler(ServletContext target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("getResourceAsStream".equals(method.getName())) {
                Object result = method.invoke(target, args);
                if (result == null) {
                    result = WebMvcConfig.class.getResourceAsStream((String) args[0]);
                }
                return result;
            } else if ("getResource".equals(method.getName())) {
                Object result = method.invoke(target, args);
                if (result == null) {
                    result = WebMvcConfig.class.getResource((String) args[0]);
                }
                return result;
            }

            return method.invoke(target, args);
        }
    }

    private static class MyFreeMarkerConfig implements FreeMarkerConfig {

        private final freemarker.template.Configuration configuration;
        private final TaglibFactory taglibFactory;

        private MyFreeMarkerConfig(freemarker.template.Configuration configuration, TaglibFactory taglibFactory) {
            this.configuration = configuration;
            this.taglibFactory = taglibFactory;
        }

        @Override
        public freemarker.template.Configuration getConfiguration() {
            return configuration;
        }

        @Override
        public TaglibFactory getTaglibFactory() {
            return taglibFactory;
        }
    }
}