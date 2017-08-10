package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aman on 23/01/17.
 */

public class UpdatePasswordRequest extends TypeValueRequest {

    @SerializedName("password")
    @Expose
    private String password;

    public UpdatePasswordRequest(Integer type, String value, String password) {
        super(type, value);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
