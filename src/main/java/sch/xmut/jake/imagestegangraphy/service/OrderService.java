package sch.xmut.jake.imagestegangraphy.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sch.xmut.jake.imagestegangraphy.constants.OrderConstant;
import sch.xmut.jake.imagestegangraphy.domain.order.OrderEntity;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.order.Order;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.repository.order.OrderRepository;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/30
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    public BaseResponse getNoDownload() {
        BaseResponse response = new BaseResponse();
        User user = userService.getUserInfoFromCache();
        List<OrderEntity> orderEntityList = orderRepository.noDownloadOrder(user.getId(), OrderConstant.PAYMENT_STATUS_YES,
                OrderConstant.ORDER_STATUS_EXIT, OrderConstant.DOWNLOAD_NO);
        if (CollectionUtils.isEmpty(orderEntityList)) {
            return response;
        }
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            order.setOrderTime(SystemUtils.dateToFormat(orderEntity.getOrderTime()));
            orderList.add(order);
        }
        response.setVo(orderList);
        return response;
    }

    public LayerResponse personalOrders(Pageable pageable, Integer orderStatus) {
        User user = userService.getUserInfoFromCache();
        Page<OrderEntity> orderEntityPage;
        if (orderStatus == null) {
            orderEntityPage = orderRepository.findAllByUserId(user.getId(), pageable);
        } else {
            orderEntityPage = orderRepository.findAllByUserIdAndOrderStatus(user.getId(), orderStatus, pageable);
        }
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityPage) {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            if (order.getPaymentStatus() == OrderConstant.PAYMENT_STATUS_YES) {
                order.setPaymentStatusFormat("已付款");
            } else {
                order.setPaymentStatusFormat("待支付");
            }
            order.setOrderTime(SystemUtils.dateToFormat(orderEntity.getOrderTime()));
            orderList.add(order);
        }
        List<OrderEntity> orderEntityList;
        if (orderStatus == null) {
            orderEntityList = orderRepository.findAll();
        } else {
            orderEntityList = orderRepository.findAllByUserIdAndOrderStatus(user.getId(), orderStatus);
        }
        LayerResponse response = new LayerResponse();
        response.setData(orderList);
        if (CollectionUtils.isEmpty(orderEntityList)) {
            response.setCount(0);
        } else {
            response.setCount(orderEntityList.size());
        }
        return response;
    }

    public BaseResponse downloadImage(String orderNumber) {
        BaseResponse response = new BaseResponse();
        OrderEntity orderEntity = orderRepository.findByOrderNumber(orderNumber);
        if (orderEntity == null) {
            SystemUtils.buildErrorResponse(response, "查询不到该订单");
            return response;
        }
        if (orderEntity.getDownloadStatus() == OrderConstant.DOWNLOAD_NO) {
            orderEntity.setDownloadStatus(OrderConstant.DOWNLOAD_YES);
            orderRepository.save(orderEntity);
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderEntity, order);
        response.setVo(order);
        return response;
    }
}
