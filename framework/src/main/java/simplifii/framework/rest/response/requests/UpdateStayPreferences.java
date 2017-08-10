package simplifii.framework.rest.response.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateStayPreferences {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("listStayPrefs")
@Expose
private List<StayPref> listStayPrefs = null;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public List<StayPref> getListStayPrefs() {
return listStayPrefs;
}

public void setListStayPrefs(List<StayPref> listStayPrefs) {
this.listStayPrefs = listStayPrefs;
}

}