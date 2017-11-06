package andy.backyard.admin.controller.user.page;

import andy.backyard.admin.controller.LoggedUserFunctionalPageControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZhangGuohua on 2017/10/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends LoggedUserFunctionalPageControllerBase {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String agents() {
        return "user/index_users";
    }

    @RequestMapping(value = "/assets", method = RequestMethod.GET)
    public String corporations() {
        return "user/index_assets";
    }

    @ModelAttribute
    public void populateCurrentPageInfoIntoModel(Model model) {
        model.addAttribute("title", "用户管理");
    }
}
