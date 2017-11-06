package andy.backyard.admin.repo;

import andy.backyard.admin.entity.AdminLoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/15.
 */
public interface AdminLoginRecordRepository extends JpaRepository<AdminLoginRecord, Long> {
    @Query("from AdminLoginRecord r where r.username=:username")
    List<AdminLoginRecord> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM admin_login_record r WHERE r.username=:username ORDER BY r.login_time DESC Limit 0, 1", nativeQuery = true)
    AdminLoginRecord findUserLatestLoginRecord(@Param("username") String username);

    @Query(value = "SELECT * FROM admin_login_record r WHERE r.username=:username AND r.remote_address=:remote_address ORDER BY r.login_time DESC Limit 0, 1", nativeQuery = true)
    AdminLoginRecord findUserLatestLoginRecord(@Param("username") String username,@Param("remote_address") String remoteAddress);
}
