package andy.backyard.admin.repo;

import andy.backyard.admin.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhangGuohua on 2017/10/15.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional // 测试完成后不提交到数据库
public class AdminRepositoryTest {
    @Autowired
    AdminRepository repository;

    @Test
    public void testAddAndQuery() {
        repository.save(new Admin("Jack", "passw0rd"));
        repository.save(new Admin("Chloe", "passw0rd"));

        // fetch all customers
        log.info("Admins found with findAll():");
        log.info("-------------------------------");
        for (Admin admin : repository.findAll()) {
            log.info(admin.toString());
        }
        log.info("");

        // fetch customers by username
        log.info("Admin found with findByUsername('Jack'):");
        log.info("--------------------------------------------");
        Admin admin0 = repository.findByUsername("Jack");
        log.info("--------------------------------");
        log.info(admin0.toString());
        log.info("");

        admin0.setEnabled(false);
        repository.save(admin0);

        // fetch an individual customer by ID
        Admin admin1 = repository.findOne(admin0.getId());
        log.info("Admin found with findOne(" + admin0.getId() + "):");
        log.info("--------------------------------");

        Assert.assertTrue(admin1.isEnabled() == false);
        log.info(admin1.toString());
        log.info(admin1.toJSON());
        log.info("");
    }
}
