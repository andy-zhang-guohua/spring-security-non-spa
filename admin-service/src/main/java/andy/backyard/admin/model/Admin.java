package andy.backyard.admin.model;

import andy.backyard.common.model.Model;
import lombok.Data;

/**
 * 管理员账号建模
 * <p>
 * Created by ZhangGuohua on 2017/10/15.
 */
@Data
public class Admin extends Model {

    private String username;


    private String password;


    /**
     * 账号是否启用，用于管理员主动启用或者禁止另外一个管理员账号
     */
    private boolean enabled = true;

    /**
     * 账号是否被锁定，用于在类似多次失败登录之后锁定账号的情况
     */
    private boolean locked = false;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    boolean gender;

    /**
     * 部门
     */
    private String department;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;
}
