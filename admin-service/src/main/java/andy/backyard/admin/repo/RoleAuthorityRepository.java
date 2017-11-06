package andy.backyard.admin.repo;

import andy.backyard.admin.entity.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/20.
 */
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long> {
    @Query("select e from RoleAuthority e where e.roleId=:roleId")
    List<RoleAuthority> listAuthoritiesOfRole(@Param("roleId") long roleId);
}
