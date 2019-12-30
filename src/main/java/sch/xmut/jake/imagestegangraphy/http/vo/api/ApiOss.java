package sch.xmut.jake.imagestegangraphy.http.vo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2019/12/30
 */
public class ApiOss {
    private Integer id;
    @JsonProperty("end_point")
    private String endPoint;
    private String encrypt;
    @JsonProperty("access_key")
    private String accessKey;
    @JsonProperty("access_secret")
    private String accessSecret;
    @JsonProperty("create_time")
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
