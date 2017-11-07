package andy.backyard.admin.model;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 建模用于界面上显示的当前登录的用户的信息
 * <p>
 * Created by ZhangGuohua on 2017/10/18.
 */
@Data
public class CurrentLoggedInUser {
    /**
     * 用户名
     */
    String username;

    /**
     * 登录地址
     */
    String remoteAddress;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;


    public static CurrentLoggedInUser from(Authentication authentication) {
        CurrentLoggedInUser user = new CurrentLoggedInUser();
        if (authentication == null)
            return user;

        Object principal = authentication.getPrincipal();
        Object details = authentication.getDetails();

        String username = "";
        if (principal instanceof AdminDetails) {
            username = ((AdminDetails) principal).getUsername();
        } else if (principal instanceof Map) {
            username = ((Map) principal).getOrDefault("username", "").toString();
        } else if (principal instanceof String) {
            username = (String) principal;
        }
        user.setUsername(username);

        String remoteAddress = "";
        if (details instanceof WebAuthenticationDetails) {
            remoteAddress = ((WebAuthenticationDetails) details).getRemoteAddress();
        }
        user.setRemoteAddress(remoteAddress);

        return user;
    }
}
