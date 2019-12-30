package sch.xmut.jake.imagestegangraphy.http.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2019/12/25
 */
public class UserRequest {
    private String mobile;
    private String password;
    private String code;
    @JsonProperty("account_name")
    private String accountName;
    private String company;
    @JsonProperty("hidden_data")
    private String hiddenData;
    @JsonProperty("orginal_image")
    private String orginalImage;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHiddenData() {
        return hiddenData;
    }

    public void setHiddenData(String hiddenData) {
        this.hiddenData = hiddenData;
    }

    public String getOrginalImage() {
        return orginalImage;
    }

    public void setOrginalImage(String orginalImage) {
        this.orginalImage = orginalImage;
    }
}
