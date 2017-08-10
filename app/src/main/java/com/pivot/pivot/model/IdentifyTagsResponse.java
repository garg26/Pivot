package com.pivot.pivot.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import simplifii.framework.utility.JsonUtil;

/**
 * Created by kartikeya on 31/7/17.
 */

public class IdentifyTagsResponse {
    @SerializedName("instruments")
    @Expose
    private List<IdentifyTagsInstrument> instruments = null;
    @SerializedName("sets")
    @Expose
    private List<IdentifyTagsSets> sets = null;

    public List<IdentifyTagsInstrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<IdentifyTagsInstrument> instruments) {
        this.instruments = instruments;
    }

    public List<IdentifyTagsSets> getSets() {
        return sets;
    }

    public void setSets(List<IdentifyTagsSets> sets) {
        this.sets = sets;
    }

    public static IdentifyTagsResponse parseJson(String json){
        IdentifyTagsResponse identifyTagsResponse = null;
        if (!TextUtils.isEmpty(json)){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject data = jsonObject.getJSONObject("data");
                String toJson = new Gson().toJson(data);
                identifyTagsResponse = (IdentifyTagsResponse) JsonUtil.parseJson(data.toString(), IdentifyTagsResponse.class);

            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
        return identifyTagsResponse;
    }
}
