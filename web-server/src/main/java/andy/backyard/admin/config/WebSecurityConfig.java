package andy.backyard.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity // 用与不用有什么影响 ?
// 启用控制方法层面的Security注解，例如最常用的@PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    static final String ROLE_PREFIX = "";

    /**
     * 缺省情况下， Spring Security 的 hasRole('ADMIN') 等价于 hasAuthority('ROLE_ADMIN'),
     * 定义此 Bean, 使 Java 注解 @PreAuthorize 中 hasRole 和 hasAuthority完全等价，
     * 也就是 hasRole('ADMIN') == hasAuthority('ADMIN')
     *
     * @return
     */
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(ROLE_PREFIX); // Remove the ROLE_ prefix
    }

    /**
     * @return
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源文件对外公开，不需要访问授权
        web.ignoring().antMatchers("/static/**");

        /**
         * 缺省情况下， Spring Security 的 hasRole('ADMIN') 等价于 hasAuthority('ROLE_ADMIN'),
         * 使用以下代码, 使 spring security JSP tag 中 hasRole 和 hasAuthority完全等价，
         * 也就是 hasRole('ADMIN') == hasAuthority('ADMIN')
         */
        if (web.getExpressionHandler() instanceof DefaultWebSecurityExpressionHandler) {
            DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = (DefaultWebSecurityExpressionHandler) web.getExpressionHandler();
            defaultWebSecurityExpressionHandler.setDefaultRolePrefix(ROLE_PREFIX);
        }
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.requestMatchers()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
