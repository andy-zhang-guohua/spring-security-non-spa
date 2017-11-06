package andy.backyard.admin.model;

import andy.backyard.common.model.Model;
import andy.backyard.admin.enums.AdminAuthority;
import lombok.Data;

/**
 * 管理角色的权限分配建模 : 每个角色对应一组管理权限
 * <p>
 * Created by ZhangGuohua on 2017/10/19.
 */
@Data
public class RoleAuthority extends Model {
    private Long roleId;

    private AdminAuthority authority;
}
