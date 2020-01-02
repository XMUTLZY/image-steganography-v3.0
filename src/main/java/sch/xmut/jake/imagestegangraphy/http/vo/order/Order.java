package sch.xmut.jake.imagestegangraphy.http.vo.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.constants.OrderConstant;

/**
 * Created by jake.lin on 2019/12/25
 */
public class Order {
    private Integer id;
    @JsonProperty("order_number")
    private String orderNumber;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("orginal_image")
    private String orginalImage;
    @JsonProperty("hidden_data")
    private String hiddenData;
    @JsonProperty("payment_amout")
    private String paymentAmount;
    @JsonProperty("result_image1")
    private String resultImage1;
    @JsonProperty("result_image2")
    private String resultImage2;
    @JsonProperty("download_status")
    private Integer downloadStatus = OrderConstant.DOWNLOAD_NO;
    @JsonProperty("payment_status")
    private Integer paymentStatus = OrderConstant.PAYMENT_STATUS_NO;
    @JsonProperty("order_status")
    private Integer orderStatus = OrderConstant.ORDER_STATUS_EXIT;
    @JsonProperty("payment_status_format")
    private String paymentStatusFormat;
    @JsonProperty("order_time")
    private String orderTime;
    @JsonProperty("user_mobile")
    private String userMobile;
    @JsonProperty("user_account_name")
    private String userAccountName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrginalImage() {
        return orginalImage;
    }

    public void setOrginalImage(String orginalImage) {
        this.orginalImage = orginalImage;
    }

    public String getHiddenData() {
        return hiddenData;
    }

    public void setHiddenData(String hiddenData) {
        this.hiddenData = hiddenData;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getResultImage1() {
        return resultImage1;
    }

    public void setResultImage1(String resultImage1) {
        this.resultImage1 = resultImage1;
    }

    public String getResultImage2() {
        return resultImage2;
    }

    public void setResultImage2(String resultImage2) {
        this.resultImage2 = resultImage2;
    }

    public Integer getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPaymentStatusFormat() {
        return paymentStatusFormat;
    }

    public void setPaymentStatusFormat(String paymentStatusFormat) {
        this.paymentStatusFormat = paymentStatusFormat;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }
}
