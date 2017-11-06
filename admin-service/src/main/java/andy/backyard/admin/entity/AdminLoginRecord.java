package andy.backyard.admin.entity;

import andy.backyard.common.entity.EntityCommon;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/11/1.
 */
@Entity
@Table(name = "admin_login_record")
@Data
public class AdminLoginRecord extends EntityCommon {
    /**
     * 用户名
     */
    @Column(nullable = false)
    String username;

    /**
     * 登录时间
     */
    LocalDateTime loginTime;

    /**
     * 来源:IP
     */
    String remoteAddress;
}
