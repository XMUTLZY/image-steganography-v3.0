package sch.xmut.jake.imagestegangraphy.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.service.common.CommonService;

/**
 * Created by Jake.lin on 2020/03/21
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @RequestMapping(value = "/get-location", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getLocation(@RequestParam String ip) {
        return commonService.getLocation(ip);
    }
}
