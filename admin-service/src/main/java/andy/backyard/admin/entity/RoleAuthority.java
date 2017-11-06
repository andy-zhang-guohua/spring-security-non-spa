package andy.backyard.admin.entity;

import andy.backyard.admin.enums.AdminAuthority;
import andy.backyard.common.entity.EntityCommon;
import lombok.Data;

import javax.persistence.*;

/**
 * 管理角色的权限分配建模 : 每个角色对应一组管理权限
 * <p>
 * Created by ZhangGuohua on 2017/10/19.
 */
@Entity
@Table(name = "role_authorities",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"roleId", "authority"})}
)
@Data
public class RoleAuthority extends EntityCommon {
    @Column(nullable = false)
    private Long roleId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminAuthority authority;
}
