package simplifii.framework.rest.response.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import simplifii.framework.rest.response.pojo.Address;
import simplifii.framework.rest.response.pojo.IdentityDoc;
import simplifii.framework.rest.response.requests.StayPref;


/**
 * Created by aman on 19/01/17.
 */

public class UserProfileData implements Serializable{

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("otpValidated")
    @Expose
    private Boolean otpValidated;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("anniversaryDate")
    @Expose
    private String anniversaryDate;
    @SerializedName("homeAddress")
    @Expose
    private Address homeAddress;
    @SerializedName("officeAddress")
    @Expose
    private Address officeAddress;
    @SerializedName("listStayPreferences")
    @Expose
    private List<StayPref> listStayPreferences ;
    @SerializedName("listIdentityDocs")
    @Expose
    private List<IdentityDoc> listIdentityDocs;
    @SerializedName("created")
    @Expose
    private long created;


    public UserProfileData(){};

    public UserProfileData(UserProfileData userProfileData){
        if(userProfileData == null){
            return;
        }
        this.firstName = userProfileData.getFirstName();
        this.lastName = userProfileData.getLastName();
        this.imageUrl = userProfileData.getImageUrl();
        this.number = userProfileData.getNumber();
        this.userId = userProfileData.getUserId();
        this.emailId = userProfileData.getEmailId();
        this.referralCode = userProfileData.getReferralCode();
        this.otpValidated = userProfileData.getOtpValidated();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Boolean getOtpValidated() {
        return otpValidated;
    }

    public void setOtpValidated(Boolean otpValidated) {
        this.otpValidated = otpValidated;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnniversary() {
        return anniversaryDate;
    }

    public void setAnniversary(String anniversary) {
        this.anniversaryDate = anniversary;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public List<StayPref> getListStayPreferences() {
        return listStayPreferences;
    }

    public void setListStayPreferences(List<StayPref> listStayPreferences) {
        this.listStayPreferences = listStayPreferences;
    }

    public List<IdentityDoc> getListIdentityDocs() {
        return listIdentityDocs;
    }

    public void setListIdentityDocs(List<IdentityDoc> listIdentityDocs) {
        this.listIdentityDocs = listIdentityDocs;
    }

    public String getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(String anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
