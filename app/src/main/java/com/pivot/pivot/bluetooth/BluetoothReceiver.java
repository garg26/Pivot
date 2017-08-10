package com.pivot.pivot.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nbansal2211 on 10/06/17.
 */

public class BluetoothReceiver extends BroadcastReceiver {
    private static final String TAG = "BluetoothReceiver";
    private BluetoothDevice deviceData;
    private List<String> stringList = new ArrayList<>();
    private GetBluetoothListListner getBluetoothListListner;

    public BluetoothReceiver(GetBluetoothListListner getBluetoothListListner) {
        this.getBluetoothListListner = getBluetoothListListner;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Message msg = Message.obtain();
        Log.d(TAG, "BluetoothReciver");
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            Log.d(TAG, "ActionFound");
            deviceData = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.d("Device", deviceData.getName() + "\n" + deviceData.getAddress());
//            deviceList.add(device);
            getBluetoothListListner.noDevice(false);
            getBluetoothListListner.sendData(deviceData);
            try {
//                device.getClass().getMethod("setPairingConfirmation", boolean.class).invoke(device, true);
//                device.getClass().getMethod("cancelPairingUserInput", boolean.class).invoke(device);
            } catch (Exception e) {
                Log.i("Log", "Inside the exception: ");
                e.printStackTrace();
            }

        } else {
            getBluetoothListListner.noDevice(true);
        }
    }

    public interface GetBluetoothListListner{
        public void sendData(BluetoothDevice deviceData);
        public void noDevice(Boolean aBoolean);
    }

}
