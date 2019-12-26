package sch.xmut.jake.imagestegangraphy.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.user.UserResponse;
import sch.xmut.jake.imagestegangraphy.service.UserService;

/**
 * Created by jake.lin on 2019/12/25
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    @ResponseBody
//    public UserResponse register(@RequestBody UserRequest userRequest) {
//
//    }

    //获取用户信息
    @PostMapping("/get")
    @ResponseBody
    public UserResponse get(@RequestBody UserRequest userRequest) {
        return userService.get(userRequest);
    }

}
