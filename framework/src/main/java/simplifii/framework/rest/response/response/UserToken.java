package simplifii.framework.rest.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aman on 07/02/17.
 */

public class UserToken extends UserProfileData {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("password")
    @Expose
    private String password;

    public UserToken(){};

    public UserToken(UserToken userToken){
        if(userToken == null){
            return;
        }
        this.token = userToken.getToken();
        this.setLastName(userToken.getLastName());
        this.setFirstName(userToken.getFirstName());
        this.setEmailId(userToken.getEmailId());
        this.setGender(userToken.getGender());
        this.setUserId(userToken.getUserId());
        this.setImageUrl(userToken.getImageUrl());
        this.setNumber(userToken.getNumber());
        this.setOtpValidated(userToken.getOtpValidated());
        this.setCreated(userToken.getCreated());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
