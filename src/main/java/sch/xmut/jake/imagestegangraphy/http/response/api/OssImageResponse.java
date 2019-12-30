package sch.xmut.jake.imagestegangraphy.http.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;

/**
 * Created by jake.lin on 2019/12/30
 */
public class OssImageResponse extends BaseResponse {
    @JsonProperty("image_url")
    private String iamgeUrl;

    public String getIamgeUrl() {
        return iamgeUrl;
    }

    public void setIamgeUrl(String iamgeUrl) {
        this.iamgeUrl = iamgeUrl;
    }
}
