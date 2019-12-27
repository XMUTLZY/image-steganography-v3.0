package sch.xmut.jake.imagestegangraphy.web.controller.view;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.service.serviceInterface.StringCacheServiceInterface;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;

/**
 * Created by jake.lin on 2019/12/24
 */
@Controller
@RequestMapping("/userView")
public class UserViewController {
    @Reference
    private StringCacheServiceInterface stringCacheServiceInterface;

    @RequestMapping("/login")
    public String userLogin() {
        return "/user/userLogin";
    }

    @RequestMapping("/index")
    public String userIndex(Model model) {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.USER_INFO_KEY);
        User user = JSON.parseObject(stringCacheServiceInterface.get(cacheRequest).getValue(), User.class);
        model.addAttribute("user", user);
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
