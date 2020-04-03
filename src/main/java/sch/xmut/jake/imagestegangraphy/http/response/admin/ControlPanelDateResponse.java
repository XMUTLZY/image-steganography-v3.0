package sch.xmut.jake.imagestegangraphy.http.response.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;

/**
 * Created by Jake.lin on 2020/04/03
 */
public class ControlPanelDateResponse extends BaseResponse {
    @JsonProperty("visit_number_day")       //日访问量
    private Integer visitNumberDay;
    @JsonProperty("visit_number_total")     //总访问量
    private Integer visitNumberTotal;
    @JsonProperty("total_order_number")     //总订单数
    private Integer totalOrderNumber;
    @JsonProperty("pay_order_number")       //已付款订单数
    private Integer payOrderNumber;
    @JsonProperty("total_money_number")     //总收入
    private Double totalMoneyNumber;
    @JsonProperty("total_user_number")      //总用户数
    private Integer totalUserNumber;
    @JsonProperty("new_user_number")        //今日新增用户数
    private Integer newUserNumber;

    public Integer getVisitNumberDay() {
        return visitNumberDay;
    }

    public void setVisitNumberDay(Integer visitNumberDay) {
        this.visitNumberDay = visitNumberDay;
    }

    public Integer getVisitNumberTotal() {
        return visitNumberTotal;
    }

    public void setVisitNumberTotal(Integer visitNumberTotal) {
        this.visitNumberTotal = visitNumberTotal;
    }

    public Integer getTotalOrderNumber() {
        return totalOrderNumber;
    }

    public void setTotalOrderNumber(Integer totalOrderNumber) {
        this.totalOrderNumber = totalOrderNumber;
    }

    public Integer getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(Integer payOrderNumber) {
        this.payOrderNumber = payOrderNumber;
    }

    public Double getTotalMoneyNumber() {
        return totalMoneyNumber;
    }

    public void setTotalMoneyNumber(Double totalMoneyNumber) {
        this.totalMoneyNumber = totalMoneyNumber;
    }

    public Integer getTotalUserNumber() {
        return totalUserNumber;
    }

    public void setTotalUserNumber(Integer totalUserNumber) {
        this.totalUserNumber = totalUserNumber;
    }

    public Integer getNewUserNumber() {
        return newUserNumber;
    }

    public void setNewUserNumber(Integer newUserNumber) {
        this.newUserNumber = newUserNumber;
    }
}
