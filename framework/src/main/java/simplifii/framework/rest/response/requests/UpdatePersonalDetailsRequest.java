package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aman on 19/02/17.
 */

public class UpdatePersonalDetailsRequest {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;
    @SerializedName("anniversary")
    @Expose
    private String anniversary;
    @SerializedName("userId")
    @Expose
    private String userId;

    public UpdatePersonalDetailsRequest(String title, String firstName, String lastName, String dob, Integer maritalStatus, String anniversary, String userId) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        this.anniversary = anniversary;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
