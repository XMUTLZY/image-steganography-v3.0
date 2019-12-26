package sch.xmut.jake.imagestegangraphy.http.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/25
 */
public class User implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonProperty("account_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mobile;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String encrypt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("real_name")
    private String realName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String city;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String company;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String career;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portrait;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("create_time")
    private Date createTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("update_time")
    private Date updateTime;
    @JsonProperty("format_create_time")
    private String formatCreateTime;
    @JsonProperty("format_update_time")
    private String formatUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getFormatUpdateTime() {
        return formatUpdateTime;
    }

    public void setFormatUpdateTime(String formatUpdateTime) {
        this.formatUpdateTime = formatUpdateTime;
    }

}
