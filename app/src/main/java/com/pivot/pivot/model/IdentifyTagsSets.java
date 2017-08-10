package com.pivot.pivot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kartikeya on 31/7/17.
 */

public class IdentifyTagsSets implements Serializable{
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

}
