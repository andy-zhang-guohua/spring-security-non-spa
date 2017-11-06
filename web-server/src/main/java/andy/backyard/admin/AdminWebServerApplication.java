package andy.backyard.admin;

import andy.backyard.common.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.request.RequestContextListener;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class}) //开启 Spring Session + Redis
/**
 * 缺省情况下 spring.aop.proxy-target-class 为 false , 也就是说 spring 缺省使用的 AOP 机制是 JDK Proxy,而不是 CGLIB
 * 我们使用CGLIB来实现AOP，所以需要配置文件中设置 spring.aop.proxy-target-class 为 true
 * 或者使用注解 @EnableAspectJAutoProxy(proxyTargetClass = true)
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
@Slf4j
public class AdminWebServerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdminWebServerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext applicationContext = app.run(args);
        log.info("应用启动完成 : {}", DateTimeUtils.toLocalDateTime(new Date(applicationContext.getStartupDate())));
    }

    /**
     * Add this bean to enable scoped bean injection
     *
     * @return
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}