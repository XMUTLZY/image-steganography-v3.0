package sch.xmut.jake.imagestegangraphy.http.vo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2019/12/25
 */
public class ApiSms {
    @JsonProperty("sms_content_type")
    private String smsContentType;
    @JsonProperty("sms_uid")
    private String smsUid;
    @JsonProperty("sms_key")
    private String smsKey;
    @JsonProperty("sms_text")
    private String smsText;
    @JsonProperty("life_time")
    private Double lifeTime;

    public String getSmsContentType() {
        return smsContentType;
    }

    public void setSmsContentType(String smsContentType) {
        this.smsContentType = smsContentType;
    }

    public String getSmsUid() {
        return smsUid;
    }

    public void setSmsUid(String smsUid) {
        this.smsUid = smsUid;
    }

    public String getSmsKey() {
        return smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public Double getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Double lifeTime) {
        this.lifeTime = lifeTime;
    }

}
