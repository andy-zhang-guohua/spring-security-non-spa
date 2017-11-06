package andy.backyard.admin.service;

import andy.backyard.admin.model.Role;
import andy.backyard.admin.model.RoleAuthority;
import andy.backyard.admin.enums.AdminAuthority;
import andy.backyard.admin.model.Admin;
import andy.backyard.admin.model.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Service
public class AdminsService {
    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    RoleAuthorityService roleAuthorityService;

    public Admin findAdminByUsername(String username) {
        return adminService.findByUsername(username);
    }

    public List<Role> listAminRoles(long adminId) {
        List<AdminRole> adminRoles = adminRoleService.listAdminRoles(adminId);
        if (adminRoles == null || adminRoles.isEmpty())
            return Collections.EMPTY_LIST;

        List<Role> roles = new ArrayList<>();
        adminRoles.stream().forEach(
                adminRole -> {
                    long roleId = adminRole.getRoleId();
                    Role role = roleService.findById(roleId);
                    if (role == null)
                        return;

                    roles.add(role);
                }
        );

        return roles;
    }

    public List<Role> listEnabledAdminRoles(long adminId) {
        List<AdminRole> adminRoles = adminRoleService.listAdminRoles(adminId);
        if (adminRoles == null || adminRoles.isEmpty())
            return Collections.EMPTY_LIST;

        List<Role> roles = new ArrayList<>();
        adminRoles.stream().forEach(
                adminRole -> {
                    long roleId = adminRole.getRoleId();
                    Role role = roleService.findById(roleId);
                    if (role == null)
                        return;

                    boolean isEnabled = role.isEnabled();
                    if (!isEnabled)
                        return;

                    roles.add(role);
                }
        );

        return roles;
    }

    public List<AdminAuthority> listAdminAuthorities(long adminId) {
        List<Role> roles = listEnabledAdminRoles(adminId);

        List<AdminAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(
                role -> {
                    final long roleId = role.getId();
                    List<RoleAuthority> roleAuthorities = roleAuthorityService.listAuthoritiesOfRole(roleId);
                    if (roleAuthorities == null || roleAuthorities.isEmpty())
                        return;

                    roleAuthorities.stream().forEach(roleAuthority -> {
                        AdminAuthority authority = roleAuthority.getAuthority();
                        if (authority == null)
                            return;

                        authorities.add(authority);
                    });
                }
        );

        return authorities;
    }
}
