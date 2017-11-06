package andy.backyard.admin.repo;

import andy.backyard.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ZhangGuohua on 2017/10/15.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    @Query("from Admin u where u.username=:username")
    Admin find(@Param("username") String username);
}
