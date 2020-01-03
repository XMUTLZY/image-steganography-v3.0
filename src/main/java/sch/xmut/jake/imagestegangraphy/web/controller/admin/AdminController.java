package sch.xmut.jake.imagestegangraphy.web.controller.admin;

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
import sch.xmut.jake.imagestegangraphy.http.request.admin.AdminRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminService;
import sch.xmut.jake.imagestegangraphy.service.order.OrderService;
import sch.xmut.jake.imagestegangraphy.service.user.UserService;

/**
 * Created by jake.lin on 2020/1/1
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    //管理员登录
    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody AdminRequest adminRequest) {
        return adminService.login(adminRequest);
    }

    //用户列表
    @GetMapping("/user-list")
    @ResponseBody
    public LayerResponse userList(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "id");
        return userService.getUserList(pageable);
    }

    //删除指定用户
    @GetMapping("user-delete")
    @ResponseBody
    public LayerResponse userDelete(@RequestParam String mobile) {
        return userService.userDelete(mobile);
    }

    //查询指定用户
    @GetMapping("/user-get")
    @ResponseBody
    public BaseResponse userGet(@RequestParam String mobile) {
        return userService.userGet(mobile);
    }

    //修改指定用户信息
    @PostMapping("/user-update")
    @ResponseBody
    public BaseResponse userUpdate(@RequestBody UserRequest userRequest) {
        return userService.userUpdate(userRequest);
    }

    //模糊查询用户信息
    @PostMapping("/user-search")
    @ResponseBody
    public LayerResponse userSearch(@RequestBody UserRequest userRequest) {
        return userService.userSearch(userRequest);
    }

    //订单列表
    @GetMapping("/order-list")
    @ResponseBody
    public LayerResponse orderList(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "id");
        return orderService.orderList(pageable);
    }

    //管理员列表
    @GetMapping("/admin-list")
    @ResponseBody
    public LayerResponse adminList(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "id");
        return adminService.adminList(pageable);
    }

    //删除指定管理员
    @PostMapping("/admin-delete")
    @ResponseBody
    public BaseResponse adminDelete(@RequestBody AdminRequest adminRequest) {
        return adminService.adminDelete(adminRequest);
    }

    //查询管理员
    @PostMapping("/admin-search")
    @ResponseBody
    public LayerResponse adminSearch(@RequestBody AdminRequest adminRequest) {
        return adminService.adminSearch(adminRequest);
    }

    //管理员添加
    @PostMapping("/admin-add")
    @ResponseBody
    public BaseResponse adminAdd(@RequestBody AdminRequest adminRequest) {
        return adminService.adminAdd(adminRequest);
    }
}
