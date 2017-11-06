package andy.backyard.admin.service;

import andy.backyard.admin.model.Admin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdminServiceTest {
    @Autowired
    AdminService adminService;

    /**
     * 前提 : 空表
     */
    @Test
    public void testAddAndQuery() {
        // 123 ===sha256===> pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=
        adminService.addAdmin("john", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=");
        // 111 ===sha256===> 9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=
        adminService.addAdmin("tom", "9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=");

        // 456 ===sha256===> s6jg4fmrG/46NvIx9nb3i7MKUZ0rIebFMMDu6Ou0pdA=
        adminService.addAdmin("andy", "s6jg4fmrG/46NvIx9nb3i7MKUZ0rIebFMMDu6Ou0pdA=");

        List<Admin> admins = adminService.listAdmins();
        log.info("Admins : {}", admins);

        Assert.assertTrue(admins.size() == 3);
        // 567 ===sha256===> l6bSHffFHoKJrBqMAmqqwUPhWqGVf1T0LjDY+KhcOlU=
        admins.stream().filter(a -> a.getUsername().equals("andy")).findFirst().ifPresent(a -> {
            adminService.updateAdminPassword(a.getId(), "l6bSHffFHoKJrBqMAmqqwUPhWqGVf1T0LjDY+KhcOlU=");
        });
    }

    /**
     * 前提 : 无
     */
    @Test
    @Ignore
    public void tesPageableQuery() {
        adminService.addAdmin("a" + RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomNumeric(6));
        adminService.addAdmin("b" + RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomNumeric(6));
        adminService.addAdmin("c" + RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomNumeric(6));
        adminService.addAdmin("d" + RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomNumeric(6));
        adminService.addAdmin("e" + RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomNumeric(6));

        List<Admin> admins = adminService.listAdmins();
        log.info("Admins ({}): {}", admins.size(), admins);

        // 随机启用或者禁用账号
        admins.stream().forEach(a -> {
            if (RandomUtils.nextInt(0, 2) == 0)
                adminService.disableAdmin(a.getId(), 1L);
            else
                adminService.enableAdmin(a.getId(), 1L);
        });


        int pageNo = 1;
        int size = 3;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "username");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "enabled");
        Sort sort = new Sort(order1, order2);
        Page page = adminService.pageAdmins(new PageRequest(pageNo, size, sort));
        log.info("Page : {}, {} => {},previousPageable={},nextPageable={}", pageNo, size, page.getContent(), page.previousPageable(), page.nextPageable());
    }
}
