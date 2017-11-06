package andy.backyard.admin.controller;

import andy.backyard.admin.common.Constants;
import andy.backyard.admin.model.AdminDetails;
import andy.backyard.admin.model.AdminLoginRecord;
import andy.backyard.admin.model.CurrentLoggedInUser;
import andy.backyard.admin.service.AdminLoginRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 所有登录后功能页面(除了登录页面)控制器类的基类 ，提供了一些工具方法 :
 * 1 . populateCurrentUserInfoIntoModel, 填充所有所有模块共用的用户数据
 * <p>
 * Created by ZhangGuohua on 2017/10/18.
 */
@Slf4j
public class LoggedUserFunctionalPageControllerBase {
    @Autowired
    AdminLoginRecordService loginRecordService;

    @ModelAttribute
    public void populateCurrentUserInfoIntoModel(HttpServletRequest request, Model model) {
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication authentication = sc.getAuthentication();
        if (authentication == null)
            return;

        CurrentLoggedInUser user = CurrentLoggedInUser.from(authentication);

        LocalDateTime loginTime = getLoginTime(request, user);
        model.addAttribute(Constants.KEY_LOGIN_TIME, loginTime);

        AdminDetails principal = (AdminDetails) authentication.getPrincipal();
        model.addAttribute("user", principal);
    }

    /**
     * 从session获取登录时间，如果没有，从数据库读取登录时间，并且保存登录时间到session，
     *
     * @return
     */
    private LocalDateTime getLoginTime(HttpServletRequest request, CurrentLoggedInUser user) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            // 用户尚未登录
            return null;
        }

        final Object objLoginTime = session.getAttribute(Constants.KEY_LOGIN_TIME);
        if (objLoginTime != null) {
            // ApplicationListener<AuthenticationSuccessEvent> 事件监听逻辑会将登录时间记录到 session,
            //所以该方法按设计应该只会走这个逻辑分支
            LocalDateTime loginTime = (LocalDateTime) objLoginTime;
            return loginTime;
        }

        // 假如没能够从 request.getSession() session 中得到登录时间，尝试从数据库中根据登录用户信息查找其最近的登录信息
        LocalDateTime loginTime = null;
        AdminLoginRecord adminLoginRecord = loginRecordService.findUserLatestLoginRecord(user.getUsername(), user.getRemoteAddress());
        if (adminLoginRecord == null) {
            log.warn("没有找到用户的登录记录, 可能登录记录失败或者存在其他需要调查的问题 : {}", user);
            loginTime = LocalDateTime.now();
            return loginTime;
        }
        loginTime = adminLoginRecord.getLoginTime();
        session.setAttribute(Constants.KEY_LOGIN_TIME, loginTime);
        return loginTime;
    }
}
