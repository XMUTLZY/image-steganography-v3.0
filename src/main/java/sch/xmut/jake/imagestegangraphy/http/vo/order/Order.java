package sch.xmut.jake.imagestegangraphy.http.vo.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.constants.OrderConstant;
import java.util.Date;

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
    @JsonProperty("result_image_first")
    private String resultImageOne;
    @JsonProperty("result_image_second")
    private String resultImageSecond;
    @JsonProperty("download_status")
    private Integer downloadStatus = OrderConstant.DOWNLOAD_NO;
    @JsonProperty("payment_status")
    private Integer paymentStatus = OrderConstant.PAYMENT_STATUS_NO;
    @JsonProperty("order_status")
    private Integer orderStatus = OrderConstant.ORDER_STATUS_EXIT;
    private Date date;
    @JsonProperty("payment_status_string")
    private String paymentStatusString;
    @JsonProperty("order_time")
    private String orderTime;

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

    public String getResultImageOne() {
        return resultImageOne;
    }

    public void setResultImageOne(String resultImageOne) {
        this.resultImageOne = resultImageOne;
    }

    public String getResultImageSecond() {
        return resultImageSecond;
    }

    public void setResultImageSecond(String resultImageSecond) {
        this.resultImageSecond = resultImageSecond;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentStatusString() {
        return paymentStatusString;
    }

    public void setPaymentStatusString(String paymentStatusString) {
        this.paymentStatusString = paymentStatusString;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
