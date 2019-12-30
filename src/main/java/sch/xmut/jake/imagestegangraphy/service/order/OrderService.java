package sch.xmut.jake.imagestegangraphy.service.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.mathworks.toolbox.javabuilder.MWException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.imagestegangraphy.constants.AlipayConstant;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.constants.OrderConstant;
import sch.xmut.jake.imagestegangraphy.constants.OssConstant;
import sch.xmut.jake.imagestegangraphy.domain.order.OrderEntity;
import sch.xmut.jake.imagestegangraphy.http.request.order.OrderPaymentRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.response.order.ImageResultResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiAlipay;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiOss;
import sch.xmut.jake.imagestegangraphy.http.vo.order.Order;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.repository.order.OrderRepository;
import sch.xmut.jake.imagestegangraphy.service.api.ApiService;
import sch.xmut.jake.imagestegangraphy.service.cache.CacheService;
import sch.xmut.jake.imagestegangraphy.service.user.UserService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import stegangraphy.embeddingInfo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jake.lin on 2019/12/30
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private CacheService cacheService;

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
            orderEntityPage = orderRepository.findAllByUserIdAndOrderStatusNot(user.getId(), OrderConstant.ORDER_STATUS_DELETE, pageable);
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
            orderEntityList = orderRepository.findAllByUserIdAndOrderStatusNot(user.getId(), OrderConstant.ORDER_STATUS_DELETE);
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

    @Transactional
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

    @Transactional
    public ImageResultResponse generateImage(UserRequest userRequest) {
        User user = userService.getUserInfoFromCache();
        ImageResultResponse imageResultResponse = new ImageResultResponse();
//        String imageUrl = userRequest.getOrginalImage();
//        runMatlab(userRequest);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setHiddenData(userRequest.getHiddenData());
        orderEntity.setOrginalImage(userRequest.getOrginalImage());
        orderEntity.setUserId(user.getId());
        orderEntity.setOrderTime(new Date());
        buildImageResultResponse(imageResultResponse, "ec0d0516-0480-46ca-bf91-10c4b95019ff.bmp", orderEntity);
        //buildImageResultResponse(imageResultResponse, imageUrl.substring(imageUrl.lastIndexOf("/") + 1), orderEntity);
        orderRepository.save(orderEntity);
        return imageResultResponse;
    }

    private void runMatlab(UserRequest userRequest) {
        embeddingInfo embeddingInfo = null;
        try {
            embeddingInfo = new embeddingInfo();
        } catch (MWException e) {
            e.printStackTrace();
        }
        try {
            embeddingInfo.start(userRequest.getOrginalImage(), converToUtf(userRequest.getHiddenData()));
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

    private int[] converToUtf(String inputInfo) {
        //将中文字符转换成bit（中文使用）
        byte[] b = null;
        try {
            b = inputInfo.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("转换前:"+inputInfo);
        System.out.print("转换后:");
        int[] bit = new int[b.length*8];
        int t = 0;
        for (int i = 0;i < b.length;i++){
            System.out.print(Integer.toBinaryString(b[i] & 0xff));
            for(int j = 0;j < 8;j++,t++){
                if(j%8==0)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,7);
                if(j%8==1)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,6)%10;
                if(j%8==2)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,5)%10;
                if(j%8==3)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,4)%10;
                if(j%8==4)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,3)%10;
                if(j%8==5)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,2)%10;
                if(j%8==6)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))/(int)Math.pow(10,1)%10;
                if(j%8==7)
                    bit[t] = (Integer.parseInt(Integer.toBinaryString(b[i] & 0xff)))%10;
            }
        }
        return bit;
    }

    private void buildImageResultResponse(ImageResultResponse response, String imageName, OrderEntity orderEntity) {
        Map<String, String> map = new HashMap<>();
        String localImageFloderPath = "C:\\resultImage\\";//该路径存放matlab算法生成的2张结果图  命名("test1.bmp" or "test2.bmp")
        String resultImageName1 = imageName.split("\\.")[0] + "One." + imageName.split("\\.")[1];
        String resultImageName2 = imageName.split("\\.")[0] + "Two." + imageName.split("\\.")[1];
        String resultImageNoStyle1 = uploadImageByLocal(localImageFloderPath, resultImageName1);
        String resultImageNoStyle2 = uploadImageByLocal(localImageFloderPath, resultImageName2);
        String resultImageFormat1 = resultImageNoStyle1 + "?"  + OssConstant.RESULT_IMAGE_STYLE2;
        String resultImageFormat2 = resultImageNoStyle2 + "?"  + OssConstant.RESULT_IMAGE_STYLE2;
        map.put("resultImageOne", resultImageFormat1);
        map.put("resultImageTwo", resultImageFormat2);
        orderEntity.setResultImage1(resultImageNoStyle1);
        orderEntity.setResultImage2(resultImageNoStyle2);
        response.setResultImageMap(map);
    }

    private String uploadImageByLocal(String localImageFloderPath, String resultImage) {
        ApiOss apiOss = apiService.getApiOssInfo();
        InputStream inputStream = null;
        OSSClient ossClient = new OSSClient(apiOss.getEndPoint(), apiOss.getAccessKey(), apiOss.getAccessSecret());
        try {
            inputStream =  new FileInputStream(localImageFloderPath + resultImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(resultImage.split("\\.")[1]);
        metadata.setContentDisposition("filename/filesize=" + resultImage + "/512" + "Byte.");
        ossClient.putObject(OssConstant.BUCKET_NAME, OssConstant.RESULT_IMAGE_FOLDER + resultImage, inputStream, metadata);
        String resultImageFormat = "https://" + OssConstant.BUCKET_NAME + "." + apiOss.getEndPoint() + "/" + OssConstant.RESULT_IMAGE_FOLDER + resultImage;
        return resultImageFormat;
    }

    public BaseResponse deleteOrder(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        orderEntity.setOrderStatus(OrderConstant.ORDER_STATUS_DELETE);
        orderRepository.save(orderEntity);
        return new BaseResponse();
    }

    public String payment(OrderPaymentRequest orderPaymentRequest) {
        String result = null;
        ApiAlipay apiAlipay = apiService.getApiAlipayInfo();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstant.GATEWAR_URL, AlipayConstant.APP_ID, AlipayConstant.MECHART_PRIVATE_KEY,
                "json", AlipayConstant.CHARSET, AlipayConstant.APLPAY_PUBLIC_KEY, AlipayConstant.SIGN_TYPE);
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setReturnUrl(AlipayConstant.RETURN_URL);
        alipayTradePagePayRequest.setNotifyUrl(AlipayConstant.NOTIFY_URL);
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ orderPaymentRequest.getOutTradeNo() +"\","
                + "\"total_amount\":\""+ orderPaymentRequest.getTotalAmount() +"\","
                + "\"subject\":\""+ orderPaymentRequest.getSubject() +"\","
                + "\"body\":\""+ orderPaymentRequest.getBody() +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            result = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();//返回一个表单页面
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        buildPayIndexFormCache(result);
        return result;
    }

    private void buildPayIndexFormCache(String result) {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.PAYMENT_INDEX_KEY);
        cacheRequest.setValue(result);
        cacheService.stringAdd(cacheRequest);
    }

    public CacheResponse getPayIndexFormCache() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.PAYMENT_INDEX_KEY);
        return cacheService.stringGet(cacheRequest);
    }
}
