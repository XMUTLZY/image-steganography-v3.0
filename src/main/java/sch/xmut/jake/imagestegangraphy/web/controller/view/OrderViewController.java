package sch.xmut.jake.imagestegangraphy.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lin on 2019/12/24
 */
@RequestMapping(value = "/orderView")
@Controller
public class OrderViewController {
    @RequestMapping("/details")
    public String orderDetails() {
        return "/order/orderDetails";
    }

    @RequestMapping("/href")
    public String test(HttpServletRequest request, Model model) {
        String payIndex = (String) request.getSession().getAttribute("payIndex");
        model.addAttribute("payIndex", payIndex);
        return "/order/href";
    }
}
