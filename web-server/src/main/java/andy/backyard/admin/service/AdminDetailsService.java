package andy.backyard.admin.service;

import andy.backyard.admin.enums.AdminAuthority;
import andy.backyard.admin.model.Admin;
import andy.backyard.admin.model.AdminDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Service
public class AdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminsService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user;
        try {
            user = usersService.findAdminByUsername(username);
        } catch (Exception e) {
            log.error("目标用户查找异常 : {}", username, e);
            throw new UsernameNotFoundException("目标用户查找异常");
        }
        if (user == null) {
            log.error("目标用户不存在 : {}", username);
            throw new UsernameNotFoundException("目标用户不存在");
        }

        try {
            List<AdminAuthority> authorities = usersService.listAdminAuthorities(user.getId());
            return new AdminDetails(user, authorities);
        } catch (Exception e) {
            log.error("目标用户权限信息读取异常 : {}", username, e);
            throw new UsernameNotFoundException("目标用户权限信息读取异常");
        }

    }
}
