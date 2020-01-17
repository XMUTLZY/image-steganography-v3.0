package sch.xmut.jake.imagestegangraphy.web.controller.view;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.Admin;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminService;

/**
 * Created by jake.lin on 2020/1/1
 */
@Controller
@RequestMapping("/adminView")
public class AdminViewController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String adminLogin() {
        adminService.clearAdminInfoCache();
        return "/admin/adminLogin";
    }

    @RequestMapping("/index")
    public String adminIndex(Model model) {
        Admin admin = JSONObject.parseObject(adminService.getAdminInfoCache().getValue(), Admin.class);
        model.addAttribute("admin", admin);
        return "/admin/adminIndex";
    }
}
