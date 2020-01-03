package sch.xmut.jake.imagestegangraphy.http.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2020/1/1
 */
public class AdminRequest {
    private Integer id;
    private String mobile;
    private String password;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("role_id")
    private Integer roleId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
