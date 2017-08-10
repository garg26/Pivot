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
 * Created by kartikeya on 2/8/17.
 */

public class SetsResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Integer location;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("reader")
    @Expose
    private Boolean reader;
    @SerializedName("instruments")
    @Expose
    private List<InstrumentTagData> instruments = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getReader() {
        return reader;
    }

    public void setReader(Boolean reader) {
        this.reader = reader;
    }

    public List<InstrumentTagData> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<InstrumentTagData> instruments) {
        this.instruments = instruments;
    }

    public static SetsResponse parseJson(String json){
        SetsResponse setsResponse = null;
        if (!TextUtils.isEmpty(json)){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject jsonSets = data.getJSONObject("sets");
                setsResponse = (SetsResponse) JsonUtil.parseJson(jsonSets.toString(), SetsResponse.class);

            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
        return setsResponse;
    }
}
