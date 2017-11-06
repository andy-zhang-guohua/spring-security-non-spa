package andy.backyard.admin.entity;

import andy.backyard.common.entity.EntityCommon;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 管理员账号建模
 * <p>
 * Created by ZhangGuohua on 2017/10/15.
 */
@Entity
@Table(name = "admin")
@Data
public class Admin extends EntityCommon {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    /**
     * 账号是否启用，用于管理员主动启用或者禁止另外一个管理员账号
     */
    @Column(nullable = false)
    private boolean enabled = true;

    /**
     * 账号是否被锁定，用于在类似多次失败登录之后锁定账号的情况
     */
    @Column(nullable = false)
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
    @Column(unique = true)
    private String mobile;


    /**
     * 头像
     */
    private String avatar;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }
}
