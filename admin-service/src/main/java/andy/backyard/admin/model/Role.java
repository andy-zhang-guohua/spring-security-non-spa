package andy.backyard.admin.model;

import andy.backyard.common.model.Model;
import lombok.Data;

/**
 * 管理角色建模: 一组管理权限的集合
 * <p>
 * Created by ZhangGuohua on 2017/10/15.
 */
@Data
public class Role extends Model {

    private String name;


    private boolean enabled = true;
}
