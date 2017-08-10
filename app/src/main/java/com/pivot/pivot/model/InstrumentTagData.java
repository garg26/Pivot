package com.pivot.pivot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kartikeya on 31/7/17.
 */

public class InstrumentTagData implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagged")
    @Expose
    private String tagged;
    @SerializedName("vendor_uid")
    @Expose
    private String vendorUid;
    @SerializedName("rfid_code")
    @Expose
    private String rfidCode;
    @SerializedName("found_rfid_id")
    @Expose
    private Boolean foundRfidId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("set_id")
    @Expose
    private String setId;
    @SerializedName("asset_type")
    @Expose
    private String assetType;
    @SerializedName("reader")
    @Expose
    private Boolean reader;
    @SerializedName("ins_master_code")
    @Expose
    private String insMasterCode;

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

    public String getTagged() {
        return tagged;
    }

    public void setTagged(String tagged) {
        this.tagged = tagged;
    }

    public String getVendorUid() {
        return vendorUid;
    }

    public void setVendorUid(String vendorUid) {
        this.vendorUid = vendorUid;
    }

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String rfidCode) {
        this.rfidCode = rfidCode;
    }

    public Boolean getFoundRfidId() {
        return foundRfidId;
    }

    public void setFoundRfidId(Boolean foundRfidId) {
        this.foundRfidId = foundRfidId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Boolean getReader() {
        return reader;
    }

    public void setReader(Boolean reader) {
        this.reader = reader;
    }

    public String getInsMasterCode() {
        return insMasterCode;
    }

    public void setInsMasterCode(String insMasterCode) {
        this.insMasterCode = insMasterCode;
    }
}
