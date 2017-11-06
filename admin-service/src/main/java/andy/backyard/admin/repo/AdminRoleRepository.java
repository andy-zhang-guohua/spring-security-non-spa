package andy.backyard.admin.repo;

import andy.backyard.admin.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
    @Query("select e from AdminRole e where e.adminId=:adminId")
    List<AdminRole> listRolesOfAdmin(@Param("adminId") long adminId);
}
