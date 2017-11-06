package andy.backyard.admin.entity;

import andy.backyard.common.entity.EntityCommon;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 管理角色建模: 一组管理权限的集合
 * <p>
 * Created by ZhangGuohua on 2017/10/15.
 */
@Entity
@Table(name = "role")
@Data
public class Role extends EntityCommon {
    /**
     * 角色名称
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * 角色是否被启用
     */
    @Column(nullable = false)
    private boolean enabled = true;
}
