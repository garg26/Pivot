package simplifii.framework.rest.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aman on 21/02/17.
 */

public class GetUserResponse extends BaseApiResponse {

    @SerializedName("data")
    @Expose
    private UserProfileData data;

    public UserProfileData getData() {
        return data;
    }

    public void setData(UserProfileData data) {
        this.data = data;
    }
}
