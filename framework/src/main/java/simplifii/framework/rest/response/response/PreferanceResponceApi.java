package simplifii.framework.rest.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by neeraj on 13/4/17.
 */

public class PreferanceResponceApi extends BaseApiResponse {

    @SerializedName("data")
    @Expose
    private PrefData data;

    public PrefData getData() {
        return data;
    }

    public void setData(PrefData data) {
        this.data = data;
    }

    public static PreferanceResponceApi parseJson(String json){
        PreferanceResponceApi preferanceResponceApi = (PreferanceResponceApi) JsonUtil.parseJson(json, PreferanceResponceApi.class);
        if(preferanceResponceApi!=null&&!preferanceResponceApi.getError()){
            Preferences.saveData(Preferences.KEY_PREFERENCES, JsonUtil.toJson(preferanceResponceApi.getData()));
        }
        return preferanceResponceApi;
    }

}
