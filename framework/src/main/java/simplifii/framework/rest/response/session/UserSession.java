package simplifii.framework.rest.response.session;

import android.text.TextUtils;

import simplifii.framework.rest.response.response.UserProfileData;
import simplifii.framework.rest.response.response.UserToken;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by aman on 24/01/17.
 */

public class UserSession extends UserToken {

    private boolean loggedIn;

    public UserSession() {}

    public UserSession(UserToken userToken, boolean loggedIn){
        super(userToken);
        this.loggedIn = loggedIn;
    }

    private static UserSession sessionInstance;

    public static UserSession getSessionInstance(){
        if(sessionInstance == null){
            String json = Preferences.getData(Preferences.USER_SESSION, "");
            if(!TextUtils.isEmpty(json)){
                sessionInstance = (UserSession) JsonUtil.parseJson(json, UserSession.class);
                sessionInstance.loggedIn = Preferences.getData(Preferences.LOGIN_KEY, false);
            }
        }
        return sessionInstance;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static void saveUserSessionAndLogin(UserToken userToken){
        sessionInstance = new UserSession(userToken, true);
        String json = JsonUtil.toJson(userToken);
        Preferences.saveData(Preferences.USER_SESSION, json);
        Preferences.saveData(Preferences.LOGIN_KEY, true);
    }

    public static void removeCurrentSession(){
        sessionInstance = null;
        Preferences.removeData(Preferences.USER_SESSION);
        Preferences.removeData(Preferences.LOGIN_KEY);
    }

    public void saveSessionDataIntoPreference(){
        String json = JsonUtil.toJson(this);
        Preferences.saveData(Preferences.USER_SESSION, json);
        sessionInstance = this;
    }

    public static void saveUserData(UserProfileData userProfileData){
        UserSession userSession = new UserSession();
        userSession.setAnniversary(userProfileData.getAnniversary());
        userSession.setDob(userProfileData.getDob());
        userSession.setImageUrl(userProfileData.getImageUrl());
        userSession.setFirstName(userProfileData.getFirstName());
        userSession.setLastName(userProfileData.getLastName());
        userSession.setEmailId(userProfileData.getEmailId());
        userSession.setOtpValidated(userProfileData.getOtpValidated());
        userSession.setMaritalStatus(userProfileData.getMaritalStatus());
        userSession.setNumber(userProfileData.getNumber());
        userSession.setReferralCode(userProfileData.getReferralCode());
        userSession.setUserId(userProfileData.getUserId());
        userSession.setHomeAddress(userProfileData.getHomeAddress());
        userSession.setTitle(userProfileData.getTitle());
        userSession.setOfficeAddress(userProfileData.getOfficeAddress());
        userSession.setListIdentityDocs(userProfileData.getListIdentityDocs());
        userSession.setListStayPreferences(userProfileData.getListStayPreferences());
        userSession.setCreated(userProfileData.getCreated());
        userSession.saveSessionDataIntoPreference();
    }
}
