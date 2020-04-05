package sch.xmut.jake.imagestegangraphy.http.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jake.lin on 2020/04/05
 */
public class ExtractImageRequest {
    @JsonProperty("major_image")
    private String majorImage;
    @JsonProperty("assist_image")
    private String assistImage;

    public String getMajorImage() {
        return majorImage;
    }

    public void setMajorImage(String majorImage) {
        this.majorImage = majorImage;
    }

    public String getAssistImage() {
        return assistImage;
    }

    public void setAssistImage(String assistImage) {
        this.assistImage = assistImage;
    }
}
