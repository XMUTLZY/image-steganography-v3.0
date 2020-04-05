package sch.xmut.jake.imagestegangraphy.http.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;

/**
 * Created by Jake.lin on 2020/04/05
 */
public class ExtractResultResponse extends BaseResponse {
    @JsonProperty("result_date")
    private String resultDate;
    @JsonProperty("result_image")
    private String resultImage;

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public String getResultImage() {
        return resultImage;
    }

    public void setResultImage(String resultImage) {
        this.resultImage = resultImage;
    }
}
