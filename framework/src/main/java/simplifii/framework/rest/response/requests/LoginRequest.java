package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aman on 19/01/17.
 */

public class LoginRequest extends TypeValueRequest {

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("loginType")
    @Expose
    private Integer loginType;
    @SerializedName("signupRequest")
    @Expose
    private SignupRequest signupRequest;

    public LoginRequest(Integer type, String value, String password, Integer loginType, SignupRequest signupRequest) {
        super(type, value);
        this.password = password;
        this.loginType = loginType;
        this.signupRequest = signupRequest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public SignupRequest getSignupRequest() {
        return signupRequest;
    }

    public void setSignupRequest(SignupRequest signupRequest) {
        this.signupRequest = signupRequest;
    }
}

