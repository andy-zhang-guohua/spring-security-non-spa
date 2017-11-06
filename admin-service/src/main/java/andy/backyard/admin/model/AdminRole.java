package andy.backyard.admin.model;

import andy.backyard.common.model.Model;
import lombok.Data;

/**
 * 管理员的角色权限分配 : 每个管理员可以分配多个角色
 * <p>
 * Created by ZhangGuohua on 2017/10/19.
 */
@Data
public class AdminRole extends Model {
    private Long adminId;

    private Long roleId;
}
