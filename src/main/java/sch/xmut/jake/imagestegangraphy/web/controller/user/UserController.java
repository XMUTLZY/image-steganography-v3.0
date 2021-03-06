package sch.xmut.jake.imagestegangraphy.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.http.request.user.ExtractImageRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.user.ExtractResultResponse;
import sch.xmut.jake.imagestegangraphy.http.response.user.UserResponse;
import sch.xmut.jake.imagestegangraphy.service.user.UserService;

import java.io.IOException;

/**
 * Created by jake.lin on 2019/12/25
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    @ResponseBody
    public BaseResponse register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

    //获取用户信息
    @PostMapping("/get")
    @ResponseBody
    public UserResponse get(@RequestBody UserRequest userRequest) {
        return userService.get(userRequest);
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody UserRequest userRequest) {
        return userService.login(userRequest);
    }

    //修改密码
    @PostMapping("/update-password")
    @ResponseBody
    public BaseResponse updatePassword(@RequestBody UserRequest userRequest) {
        return userService.updatePassword(userRequest);
    }

    //查询指定用户
    @GetMapping("/user-get")
    @ResponseBody
    public BaseResponse userGet(@RequestParam String mobile) {
        return userService.userGet(mobile);
    }

    //还原原始图像并提取数据
    @PostMapping("/extract-image")
    @ResponseBody
    public ExtractResultResponse extractImage(@RequestBody ExtractImageRequest request) throws IOException {
        return userService.extractImage(request);
    }

}
