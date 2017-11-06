package andy.backyard.admin.service;

import andy.backyard.admin.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Test
    public void testAddAndQuery() {
        roleService.addRole("超级管理员");
        roleService.addRole("HR管理员");
        roleService.addRole("IT管理员");

        List<Role> roles = roleService.listRoles();
        log.info("Roles : {}", roles);

        Assert.assertTrue(roles.size() == 3);
    }
}
