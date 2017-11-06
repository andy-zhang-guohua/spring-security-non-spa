package andy.backyard.admin.service;

import andy.backyard.admin.model.RoleAuthority;
import andy.backyard.admin.enums.AdminAuthority;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RoleAuthorityServiceTest {
    @Autowired
    RoleAuthorityService roleAuthorityService;

    @Test
    public void testAddAndQuery() {
        long roleId = 1L;
        roleAuthorityService.addRoleAuthority(roleId, AdminAuthority.VIEW_ADMINS);
        roleAuthorityService.addRoleAuthority(roleId, AdminAuthority.ADMIN_ROLE_GRANT);
        roleAuthorityService.addRoleAuthority(roleId, AdminAuthority.ADMIN_ADD);
        roleAuthorityService.addRoleAuthority(roleId, AdminAuthority.ADMIN_REST_PASSWORD);

        List<RoleAuthority> roleAuthorities = roleAuthorityService.listAuthoritiesOfRole(roleId);
        log.info("Role authorities : {}", roleAuthorities);
    }
}
