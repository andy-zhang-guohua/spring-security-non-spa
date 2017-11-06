package andy.backyard.admin.listener;

import andy.backyard.admin.service.AdminLoginRecordService;
import andy.backyard.admin.common.Constants;
import andy.backyard.common.utils.DateTimeUtils;
import andy.backyard.admin.model.CurrentLoggedInUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/10/30.
 */
@Slf4j
@Component
public class AdminLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    AdminLoginRecordService loginRecordService;

    /**
     * 因为 Spring security 的安全机制,@Autowired HttpSession session 和从 request.getSession()获得的 session 会不同，
     * 这里使用 @Autowired HttpServletRequest request 用于获取 session 的来源
     */
    @Autowired
    HttpServletRequest request;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // 此时  SecurityContext sc = SecurityContextHolder.getContext(); 中的 authentication 还是未设置状态
        // 获取认证信息
        Authentication authentication = event.getAuthentication();
        long loginTimestamp = event.getTimestamp();
        LocalDateTime loginTime = DateTimeUtils.toLocalDateTime(loginTimestamp);
        recordLogin(authentication, loginTime);

        request.getSession().setAttribute(Constants.KEY_LOGIN_TIME, loginTime);
    }

    private void recordLogin(Authentication authentication, LocalDateTime loginTime) {
        CurrentLoggedInUser user = CurrentLoggedInUser.from(authentication);
        String username = user.getUsername();
        String remoteAddress = user.getRemoteAddress();

        //记录管理员登录日志
        loginRecordService.recordLogin(username, loginTime, remoteAddress);
    }
}
