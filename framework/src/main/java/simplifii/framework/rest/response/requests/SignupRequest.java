package simplifii.framework.rest.response.requests;

/**
 * Created by aman on 14/01/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupRequest {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("signupType")
    @Expose
    private Integer signupType;
    @SerializedName("otpValidated")
    @Expose
    private Boolean otpValidated;

    public SignupRequest(){}

    public SignupRequest(String firstName, String lastName, String number, String password, String emailId, String referralCode, Integer signupType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.password = password;
        this.emailId = emailId;
        this.referralCode = referralCode;
        this.signupType = signupType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Integer getSignupType() {
        return signupType;
    }

    public void setSignupType(Integer signupType) {
        this.signupType = signupType;
    }

    public Boolean getOtpValidated() {
        return otpValidated;
    }

    public void setOtpValidated(Boolean otpValidated) {
        this.otpValidated = otpValidated;
    }
}



