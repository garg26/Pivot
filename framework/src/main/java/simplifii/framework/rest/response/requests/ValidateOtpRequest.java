package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aman on 19/01/17.
 */

public class ValidateOtpRequest {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("validated")
    @Expose
    private Boolean validated;

    public ValidateOtpRequest(String type, String value, Boolean validated) {
        this.type = type;
        this.value = value;
        this.validated = validated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

}

