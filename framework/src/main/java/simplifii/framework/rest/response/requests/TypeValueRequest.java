package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aman on 22/01/17.
 */

public class TypeValueRequest {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("value")
    @Expose
    private String value;

    public TypeValueRequest(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public TypeValueRequest(){};

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
