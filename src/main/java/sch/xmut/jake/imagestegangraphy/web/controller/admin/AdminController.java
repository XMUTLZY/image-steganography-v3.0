package sch.xmut.jake.imagestegangraphy.web.controller.admin;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.constants.AdminConstant;
import sch.xmut.jake.imagestegangraphy.http.request.admin.AdminRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.Admin;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminService;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jake.lin on 2020/1/1
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    @ResponseBody
    public BaseResponse login(@RequestBody AdminRequest adminRequest) {
        return adminService.login(adminRequest);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse registerAdmin(@RequestBody Admin admin, HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        String encrypt = new SecureRandomNumberGenerator().nextBytes().toString();//生成盐
        String encodePassword = new SimpleHash(AdminConstant.ENCRYPTION_TYPE, admin.getPassword(), encrypt, AdminConstant.ENCRYPTION_TIMES).toString();
        admin.setPassword(encodePassword);
        admin.setEncrypt(encrypt);
        adminService.saveAdmin(admin);
        response.setMessage("注册成功");
        return response;
    }
}
