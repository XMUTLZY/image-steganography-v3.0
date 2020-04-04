package sch.xmut.jake.imagestegangraphy.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.service.user.UserService;

/**
 * Created by jake.lin on 2019/12/24
 */
@Controller
@RequestMapping("/userView")
public class UserViewController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String userLogin() {
        userService.clearUserInfoFromCache();//清理用户缓存
        return "/user/userLogin";
    }

    @RequestMapping("/index")
    public String userIndex(Model model) {
        User user = userService.getUserInfoFromCache();
        userService.recordVisitNumberByDay();    //记录访问量
        model.addAttribute("user", user);
        return "/user/userIndex";
    }

    @RequestMapping("/infoExtract")
    public String infoExtract() {
        return "/user/infoExtract";
    }

    @RequestMapping("/infoEmbed")
    public String infoEmbed() {
        return "/user/infoEmbed";
    }

    @RequestMapping("/algorithmDetail")
    public String algorithmDetail() {
        return "/user/algorithmDetail";
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
