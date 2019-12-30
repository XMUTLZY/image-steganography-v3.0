package sch.xmut.jake.imagestegangraphy.http.vo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/30
 */
public class ApiAlipay {
    private Integer id;
    @JsonProperty("app_id")
    private String appId;
    private String encrypt;
    @JsonProperty("mechart_private_key")
    private String mechartPrivateKey;
    @JsonProperty("alipay_public_key")
    private String alipayPublicKey;
    @JsonProperty("create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getMechartPrivateKey() {
        return mechartPrivateKey;
    }

    public void setMechartPrivateKey(String mechartPrivateKey) {
        this.mechartPrivateKey = mechartPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
