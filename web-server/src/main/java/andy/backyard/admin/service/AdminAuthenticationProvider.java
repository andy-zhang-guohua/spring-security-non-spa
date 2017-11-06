package andy.backyard.admin.service;

import andy.backyard.admin.model.AdminDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminDetailsService userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        AdminDetails user = (AdminDetails) userService.loadUserByUsername(username);
        if (user == null) {
            log.warn("目标用户不存在 : {}", username);
            throw new BadCredentialsException("指定用户不存在");
        }

        //密码匹配检查逻辑
        boolean passwordMatch = matchPassword(user.getPassword(), password);
        if (!passwordMatch) {
            log.warn("密码不匹配 : {}", username);
            throw new BadCredentialsException("密码错误");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    private boolean matchPassword(String expected, String input) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean match = passwordEncoder.matches(input, expected);
        return match;
    }
}
