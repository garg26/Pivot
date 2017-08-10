package simplifii.framework.rest.response.response;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by aman on 19/01/17.
 */

public class LoginResponse extends BaseApiResponse {

    @SerializedName("data")
    @Expose
    private UserToken data;

    public UserToken getData() {
        return data;
    }

    public void setData(UserToken data) {
        this.data = data;
    }

    public static LoginResponse getInstance(){
        String json = Preferences.getData(AppConstants.PREF_KEYS.KEY_USER_DATA, "");
        if(!TextUtils.isEmpty(json)){
            return parseJson(json);
        }
        return null;
    }

    public static LoginResponse parseJson(String json){
        LoginResponse data = (LoginResponse) JsonUtil.parseJson(json, LoginResponse.class);
        if(data != null){
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_USER_DATA, json);
        }
        return data;
    }
}
