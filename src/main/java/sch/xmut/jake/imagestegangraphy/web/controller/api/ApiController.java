package sch.xmut.jake.imagestegangraphy.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.api.CodeResponse;
import sch.xmut.jake.imagestegangraphy.http.response.api.OssImageResponse;
import sch.xmut.jake.imagestegangraphy.service.api.ApiService;

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

    //上传图片
    @PostMapping("/image-upload-oss")
    @ResponseBody
    public OssImageResponse imageUploadOss(@RequestParam("file") MultipartFile file) {
        return apiService.imageUploadOss(file);
    }
}
