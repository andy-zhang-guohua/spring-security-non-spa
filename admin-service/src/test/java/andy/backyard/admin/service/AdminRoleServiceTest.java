package andy.backyard.admin.service;

import andy.backyard.admin.model.AdminRole;
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
public class AdminRoleServiceTest {
    @Autowired
    AdminRoleService adminRoleService;

    @Test
    public void testAddAndQuery() {
        long adminId = 1L;
        adminRoleService.addAdminRole(adminId, 1L);
        adminRoleService.addAdminRole(adminId, 2L);
        adminRoleService.addAdminRole(adminId, 3L);
        adminRoleService.addAdminRole(adminId, 4L);

        List<AdminRole> roles = adminRoleService.listAdminRoles(adminId);
        log.info("Admin roles : {}", roles);
    }
}
