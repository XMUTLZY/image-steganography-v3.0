package sch.xmut.jake.imagestegangraphy.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.api.CodeResponse;
import sch.xmut.jake.imagestegangraphy.service.ApiService;

/**
 * Created by jake.lin on 2019/12/25
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    //发送验证码
    @PostMapping("/send-code")
    @ResponseBody
    public CodeResponse sendCode(@RequestBody UserRequest userRequest) {
        return apiService.sendCode(userRequest);
    }
}
