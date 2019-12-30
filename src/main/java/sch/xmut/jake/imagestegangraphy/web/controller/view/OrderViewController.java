package sch.xmut.jake.imagestegangraphy.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.service.order.OrderService;
import sch.xmut.jake.imagestegangraphy.service.user.UserService;

/**
 * Created by lin on 2019/12/24
 */
@RequestMapping(value = "/orderView")
@Controller
public class OrderViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/details")
    public String orderDetails() {
        return "/order/orderDetails";
    }

    @RequestMapping("/href")
    public String payIndexForm(Model model) {
        String payIndex = orderService.getPayIndexFormCache().getValue();
        model.addAttribute("payIndex", payIndex);
        return "/order/href";
    }

    @RequestMapping("/personalOrders")
    public String userIndex(Model model) {
        User user = userService.getUserInfoFromCache();
        model.addAttribute("user", user);
        return "/order/personalOrders";
    }
}
