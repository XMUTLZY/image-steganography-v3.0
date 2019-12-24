package sch.xmut.jake.imagestegangraphy.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jake.lin on 2019/12/24
 */
@Controller
@RequestMapping("/userView")
public class UserViewController {
    @RequestMapping("/login")
    public String userLogin() {
        return "/user/userLogin";
    }

    @RequestMapping("/index")
    public String userIndex(HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        model.addAttribute("user", user);
        return "/user/userIndex";
    }

    @RequestMapping("/forget")
    public String userForget() {
        return "/user/userForget";
    }

    @RequestMapping("/register")
    public String userRegister() {
        return "/user/userRegister";
    }
}
