package com.pivot.pivot.model;

import android.bluetooth.BluetoothDevice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrnee on 7/5/2017.
 */

public class BluetoothDeviceList implements Serializable{

    private List<BluetoothDevice> deviceList = new ArrayList<>();
    private List<BluetoothDevice> pairList = new ArrayList<>();

    public List<BluetoothDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<BluetoothDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public List<BluetoothDevice> getPairList() {
        return pairList;
    }

    public void setPairList(List<BluetoothDevice> pairList) {
        this.pairList = pairList;
    }
}
