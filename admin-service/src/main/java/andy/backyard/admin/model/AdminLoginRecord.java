package andy.backyard.admin.model;

import andy.backyard.common.model.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/11/1.
 */
@Data
public class AdminLoginRecord extends Model {
    /**
     * 用户名
     */
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
