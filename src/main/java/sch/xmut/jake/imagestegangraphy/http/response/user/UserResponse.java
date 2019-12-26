package sch.xmut.jake.imagestegangraphy.http.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;

/**
 * Created by jake.lin on 2019/12/25
 */
public class UserResponse extends BaseResponse<User> {
    public static final Integer IS_REGISTER_YES = 1;
    public static final Integer IS_REGISTER_NO = 0;
    @JsonProperty("is_register")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer isRegister;

    public Integer getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Integer isRegister) {
        this.isRegister = isRegister;
    }

}
