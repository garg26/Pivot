package com.pivot.pivot.model;

import java.io.Serializable;

public class TagListItem implements Serializable{

    private String mTag;
    private String mTid;
    private float mRSSI;
    private float mPhase;
    private int count;

    @SuppressWarnings("unused")
    public TagListItem(String tag) {
        mTag = tag;
        mTid = "";
        mRSSI = 0;
        mPhase = 0;
        count = 1;
    }

    public TagListItem(String tag, float rssi, float phase) {
        mTag = tag;
        mTid = "";
        mRSSI = rssi;
        mPhase = phase;
        count = 1;
    }

    public TagListItem(String tag, String tid, float rssi, float phase) {
        mTag = tag;
        mTid = tid;
        mRSSI = rssi;
        mPhase = phase;
        count = 1;
    }

    public String getTag(boolean displayPc) {
        return displayPc ? mTag : mTag.substring(4);
    }

    public String getTid() {
        return mTid;
    }

    public float getRSSI() {
        return mRSSI;
    }

    public float getPhase() {
        return mPhase;
    }

    public int getCount() {
        return count;
    }

    public void updateItem(float rssi, float phase) {
        mRSSI = rssi;
        mPhase = phase;
        count++;
    }
}