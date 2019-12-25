package sch.xmut.jake.imagestegangraphy.http.vo.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jake.lin on 2019/12/25
 */
public class AdminPrivilegeRole implements Serializable {
    private Integer id;
    @JsonProperty("role_id")
    private Integer RoleId;
    @JsonProperty("privilege_id")
    private Integer privilegeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}
