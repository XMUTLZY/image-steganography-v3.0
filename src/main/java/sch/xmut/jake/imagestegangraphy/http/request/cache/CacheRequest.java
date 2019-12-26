package sch.xmut.jake.imagestegangraphy.http.request.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by jake.lin on 2019/12/26
 */
public class CacheRequest {
    @NotNull
    private String member;
    @Pattern(regexp = CacheConstant.KEY_PATTERN, message = "只允许大写字母和_")
    private String key;
    private String value;
    @JsonProperty("life_time")
    private Double lifeTime;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Double lifeTime) {
        this.lifeTime = lifeTime;
    }
}
