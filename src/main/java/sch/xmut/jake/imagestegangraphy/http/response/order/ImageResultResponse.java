package sch.xmut.jake.imagestegangraphy.http.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import java.util.Map;

/**
 * Created by jake.lin on 2019/12/30
 */
public class ImageResultResponse extends BaseResponse {
    @JsonProperty("result_image_map")
    private Map<String, String> resultImageMap;

    public Map<String, String> getResultImageMap() {
        return resultImageMap;
    }

    public void setResultImageMap(Map<String, String> resultImageMap) {
        this.resultImageMap = resultImageMap;
    }
}
