package simplifii.framework.rest.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import simplifii.framework.rest.response.requests.StayPref;

/**
 * Created by neeraj on 13/4/17.
 */

public class PrefData {
    @SerializedName("listPreferneces")
    @Expose
    private List<StayPref> listPreferneces = null;

    public List<StayPref> getListPreferneces() {
        return listPreferneces;
    }

    public void setListPreferneces(List<StayPref> listPreferneces) {
        this.listPreferneces = listPreferneces;
    }
}
