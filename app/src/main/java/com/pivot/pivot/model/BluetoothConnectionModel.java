package com.pivot.pivot.model;

import android.bluetooth.BluetoothDevice;
import android.nfc.Tag;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.Preferences;

/**
 * Created by mrnee on 6/2/2017.
 */

public class BluetoothConnectionModel {
    private String readerName;
    private boolean selected;
    private boolean ifPaired = false;
    private boolean isFirst;
    private BluetoothDevice bluetoothDevice;
//    private List<BluetoothDevice> deviceList = new ArrayList<>();
//    private List<BluetoothDevice> pairList = new ArrayList<>();
//    private List<BluetoothDevice> bluetoothDeviceLists = new ArrayList<>();


    public boolean isIfPaired() {
        return ifPaired;
    }

    public void setIfPaired(boolean ifPaired) {
        this.ifPaired = ifPaired;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    //    public List<BluetoothDevice> getBluetoothDeviceLists() {
//        return bluetoothDeviceLists;
//    }
//
//    public void setBluetoothDeviceLists(List<BluetoothDevice> bluetoothDeviceLists) {
//        this.bluetoothDeviceLists = bluetoothDeviceLists;
//    }
//
//    public List<BluetoothDevice> getDeviceList() {
//        return deviceList;
//    }
//
//    public void setDeviceList(List<BluetoothDevice> deviceList) {
//        this.deviceList = deviceList;
//    }

//    public List<BluetoothDevice> getPairList() {
//        return pairList;
//    }
//
//    public void setPairList(List<BluetoothDevice> pairList) {
//        this.pairList = pairList;
//    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
}
