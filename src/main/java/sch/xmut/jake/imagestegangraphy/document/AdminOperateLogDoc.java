package sch.xmut.jake.imagestegangraphy.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by jake.lin on 2020/1/3
 */
@Document("admin_operate_log")
public class AdminOperateLogDoc {
    @Field("admin_id")
    private Integer adminId;
    @Field("user_name")
    private String userName;
    @Field("role_name")
    private String roleName;
    @Field("operate_str")
    private String operateStr;
    private String ip;
    @Field("operate_time")
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
