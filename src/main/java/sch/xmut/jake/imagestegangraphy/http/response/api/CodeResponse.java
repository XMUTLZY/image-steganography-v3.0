package sch.xmut.jake.imagestegangraphy.http.response.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiSms;

/**
 * Created by jake.lin on 2019/12/26
 */
public class CodeResponse extends BaseResponse<ApiSms> {
    @JsonProperty("code_status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String codeStatus;

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }
}
