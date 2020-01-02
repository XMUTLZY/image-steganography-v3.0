package sch.xmut.jake.imagestegangraphy.http.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2019/12/30
 */
public class OrderPaymentRequest {
    private Integer id;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("total_amount")
    private String totalAmount;
    private String subject;
    private String body;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
