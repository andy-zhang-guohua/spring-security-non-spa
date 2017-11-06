package andy.backyard.admin.entity;

import andy.backyard.common.entity.EntityCommon;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 管理员的角色权限分配 : 每个管理员可以分配多个角色
 * <p>
 * Created by ZhangGuohua on 2017/10/19.
 */
@Entity
@Table(name = "admin_roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"adminId", "roleId"})}
)
@Data
public class AdminRole extends EntityCommon {
    @Column(nullable = false)
    private Long adminId;

    @Column(nullable = false)
    private Long roleId;
}
