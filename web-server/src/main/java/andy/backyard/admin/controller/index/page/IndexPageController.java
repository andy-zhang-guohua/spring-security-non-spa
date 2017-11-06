package andy.backyard.admin.controller.index.page;

import andy.backyard.admin.controller.LoggedUserFunctionalPageControllerBase;
import lombok.extern.slf4j.Slf4j;
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
public class IndexPageController extends LoggedUserFunctionalPageControllerBase {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "ping";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @ModelAttribute
    public void populateCurrentPageInfoIntoModel(Model model) {
        model.addAttribute("title", "首页");
    }
}
