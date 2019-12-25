package sch.xmut.jake.imagestegangraphy.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;

/**
 * Created by jake.lin on 2019/12/25
 */
public class UserResponse extends BaseResponse<User>{
    @JsonProperty("is_register")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean isRegister;

    public Boolean getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Boolean isRegister) {
        isRegister = isRegister;
    }
}
