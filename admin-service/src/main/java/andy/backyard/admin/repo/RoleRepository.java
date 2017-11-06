package andy.backyard.admin.repo;

import andy.backyard.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZhangGuohua on 2017/10/20.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
