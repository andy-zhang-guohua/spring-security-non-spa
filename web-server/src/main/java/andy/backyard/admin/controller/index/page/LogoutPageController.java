package andy.backyard.admin.controller.index.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ZhangGuohua on 2017/11/01.
 */
@Slf4j
@Controller
public class LogoutPageController {


    @Autowired
    HttpSession session;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication == null)
            return;

        /**
         *  基于 request.getSession()的 logout
         */
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        /**
         * 基于  @Autowired HttpSession session  的 logout
         */
        try {
            // 因为 Spring security 的安全机制,@Autowired HttpSession session 和从 request.getSession()获得的 session 会不同，
            // 从安全的角度出发，用户退出登录时也销毁 @Autowired HttpSession session
            session.invalidate();
        } catch (Exception e) {
            ;// do nothing now when redirect fails
        }

        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            ;// do nothing now when redirect fails
        }
    }
}
