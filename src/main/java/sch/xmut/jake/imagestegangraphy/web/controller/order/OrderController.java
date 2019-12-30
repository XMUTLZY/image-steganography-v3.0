package sch.xmut.jake.imagestegangraphy.web.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.jake.imagestegangraphy.http.request.order.OrderPaymentRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.response.order.ImageResultResponse;
import sch.xmut.jake.imagestegangraphy.service.order.OrderService;

/**
 * Created by jake.lin on 2019/12/30
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //获取未下载的图片信息
    @PostMapping("/get-no-download")
    @ResponseBody
    public BaseResponse getNoDownload() {
        return orderService.getNoDownload();
    }

    //获取图片信息
    @PostMapping("/personal-orders")
    @ResponseBody
    public LayerResponse personalOrders(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page,
                                        @RequestParam("orderStatus") Integer orderStatus) {
        Pageable pageable = PageRequest.of(page-1, limit, Sort.Direction.ASC, "orderNumber");
        return orderService.personalOrders(pageable, orderStatus);
    }

    //下载图片并修改状态
    @GetMapping("/download-image")
    @ResponseBody
    public BaseResponse downloadImage(@RequestParam(value = "order_number", required = false) String orderNumber) {
        return orderService.downloadImage(orderNumber);
    }

    //生成隐写图片
    @PostMapping("/generate-image")
    @ResponseBody
    public ImageResultResponse generateImage(@RequestBody UserRequest userRequest) {
        return orderService.generateImage(userRequest);
    }

    //删除订单
    @GetMapping("/delete")
    @ResponseBody
    public BaseResponse deleteOrder(@RequestParam Integer id) {
        return orderService.deleteOrder(id);
    }

    //付款
    @PostMapping("/pay")
    @ResponseBody
    public String payment(@RequestBody OrderPaymentRequest orderPaymentRequest) {
        return orderService.payment(orderPaymentRequest);
    }
}
