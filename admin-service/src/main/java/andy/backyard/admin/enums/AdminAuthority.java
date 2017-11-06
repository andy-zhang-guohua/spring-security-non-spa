package andy.backyard.admin.enums;

/**
 * 管理权限定义,每个管理权限对应 ：
 * 1. 页面上的某个功能入口的展示控制 或者
 * 2. 某个 Page Controller 方法访问控制 或者
 * 3. 某个 API Controller 方法访问控制
 * <p>
 * Created by ZhangGuohua on 2017/10/19.
 */
public enum AdminAuthority {
    VIEW_ADMINS("管理员管理", "查看管理员列表"),
    VIEW_ADMIN_DETAILS("管理员管理", "查看管理员详情"),
    ADMIN_ADD("管理员管理", "增加管理员账号"),
    ADMIN_ENABLING("管理员管理", "禁用/启用管理员账号"),
    ADMIN_REST_PASSWORD("管理员管理", "重置管理员账号密码"),
    ADMIN_ROLE_GRANT("管理员管理", "设置管理员账号权限组"),
    ADMIN_UPDATE("管理员管理", "更新管理员个人资料信息"),

    VIEW_ADMIN_ROLES("管理员管理", "查看管理角色列表"),
    VIEW_ADMIN_ROLE_DETAILS("管理员管理", "查看管理角色详情"),
    ADMIN_ROLE_ADD("管理员管理", "增加管理角色"),
    ADMIN_ROLE_UPDATE("管理员管理", "编辑管理角色"),

    VIEW_BIZ_AGENTS("业务管理", "查看业务人员列表"),
    VIEW_BIZ_CORPORATIONS("业务管理", "查看业务服务商列表"),

    VIEW_USERS("用户管理", "查看用户列表"),
    VIEW_USER_ASSETS("用户管理", "查看用户资产列表"),;

    private AdminAuthority(String group, String displayName) {
        this.group = group;
        this.displayName = displayName;
    }

    private String group;
    private String displayName;
}
