package andy.backyard.admin.controller.admin.page;

import andy.backyard.admin.controller.LoggedUserFunctionalPageControllerBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZhangGuohua on 2017/10/15.
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends LoggedUserFunctionalPageControllerBase {

    @PreAuthorize("hasAuthority('VIEW_ADMINS')")
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String admins() {
        return "admin/index_admins";
    }

    //  @PreAuthorize("hasPermission('hello', 'view')")
    @PreAuthorize("hasRole('VIEW_ADMIN_ROLES')")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String roles() {
        return "admin/index_roles";
    }

    @ModelAttribute
    public void populateCurrentPageInfoIntoModel(Model model) {
        model.addAttribute("title", "管理员管理");
    }
}
