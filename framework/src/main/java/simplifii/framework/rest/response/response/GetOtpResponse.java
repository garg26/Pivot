package simplifii.framework.rest.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aman on 18/01/17.
 */

public class GetOtpResponse extends BaseApiResponse {


    @SerializedName("data")
    @Expose
    private OtpContainer data;

    public OtpContainer getData() {
        return data;
    }

    public void setData(OtpContainer data) {
        this.data = data;
    }
}
