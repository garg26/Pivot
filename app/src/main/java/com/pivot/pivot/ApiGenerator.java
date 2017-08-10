package com.pivot.pivot;

import com.pivot.pivot.model.IdentifyTagsResponse;
import com.pivot.pivot.model.SetLoginRequest;
import com.pivot.pivot.model.SetsResponse;
import com.pivot.pivot.model.UserLoginResponse;

import java.util.ArrayList;

import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by kartikeya on 31/7/17.
 */

public class ApiGenerator {

    public static HttpParamObject userLogin(String email, String password) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.addParameter("email",email);
        httpParamObject.addParameter("password",password);
        httpParamObject.setUrl(AppConstants.PAGE_URL.LOGIN);
        httpParamObject.setPostMethod();
        httpParamObject.setClassType(UserLoginResponse.class);
        return httpParamObject;
    }

    public static HttpParamObject identifyTag(String tagList) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.addParameter("input_tags", tagList);
        httpParamObject.addParameter("token",Preferences.getData(Preferences.KEY_TOKEN,""));
        httpParamObject.setUrl(AppConstants.PAGE_URL.IDENTIFY_TAGS);
        httpParamObject.setPostMethod();
        httpParamObject.setClassType(IdentifyTagsResponse.class);
        return httpParamObject;

    }

    public static HttpParamObject refreshSets(String setsId, String tagList) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.addParameter("set_id",setsId);
        httpParamObject.addParameter("input_tags", tagList);
        httpParamObject.setUrl(AppConstants.PAGE_URL.REFRESH_SETS);
        httpParamObject.setPostMethod();
        httpParamObject.setClassType(SetsResponse.class);
        return httpParamObject;
    }


}
