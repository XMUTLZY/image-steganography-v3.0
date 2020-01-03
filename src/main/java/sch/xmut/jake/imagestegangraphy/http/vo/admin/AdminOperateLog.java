package sch.xmut.jake.imagestegangraphy.http.vo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jake.lin on 2020/1/3
 */
public class AdminOperateLog {
    @JsonProperty("admin_id")
    private Integer adminId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("operate_str")
    private String operateStr;
    private String ip;
    @JsonProperty("operate_time")
    private String operateTime;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOperateStr() {
        return operateStr;
    }

    public void setOperateStr(String operateStr) {
        this.operateStr = operateStr;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
