package com.pivot.pivot.bluetooth;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nbansal2211 on 10/06/17.
 */

public class BluetoothManager {

    private static final String TAG = "BluetoothManager";
    private static BluetoothManager instance;
    private BluetoothAdapter adapter;

    public static BluetoothManager getInstance(Context context) {
        if (instance == null) {
            instance = new BluetoothManager();
        }
        instance.initBluetoothAdapter();
        return instance;
    }

    private void initBluetoothAdapter() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    // function to get paired devices
    public List<BluetoothDevice> getPairedDevices() {
        return new ArrayList<>(this.adapter.getBondedDevices());
//        if (pairedDevice.size() > 0) {
//            for (BluetoothDevice device : pairedDevice) {
//                Log.d(TAG, device.getName() + "\n" + device.getAddress());
////                arrayListpaired.add(device.getName() + "\n" + device.getAddress());
////                arrayListPairedBluetoothDevices.add(device);
//            }
//        }
    }

    private Boolean connect(BluetoothDevice bdDevice) {
        Boolean bool = false;
        try {
            Log.i("Log", "service method is called ");
            Class cl = Class.forName("android.bluetooth.BluetoothDevice");
            Class[] par = {};
            Method method = cl.getMethod("createBond", par);
            Object[] args = {};
            bool = (Boolean) method.invoke(bdDevice);//, args);// this invoke creates the detected devices paired.
            //Log.i("Log", "This is: "+bool.booleanValue());
            //Log.i("Log", "devicesss: "+bdDevice.getName());
        } catch (Exception e) {
            Log.i("Log", "Inside catch of serviceFromDevice Method");
            e.printStackTrace();
        }
        return bool.booleanValue();
    }

    public boolean removeBond(BluetoothDevice btDevice)
            throws Exception {
        Class btClass = Class.forName("android.bluetooth.BluetoothDevice");
        Method removeBondMethod = btClass.getMethod("removeBond");
        Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }


    public boolean createBond(BluetoothDevice btDevice)
            throws Exception {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    public void startSearching(Context context, BroadcastReceiver receiver) {
        Log.i("Log", "in the start searching method");
//        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(receiver, filter);
        adapter.startDiscovery();
    }

    public void onBluetooth() {
        if (!adapter.isEnabled()) {
            adapter.enable();
            Log.i("Log", "Bluetooth is Enabled");
        }
    }

    public void offBluetooth() {
        if (adapter.isEnabled()) {
            adapter.disable();
        }
    }

    public void makeDiscoverable(Context context) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        context.startActivity(discoverableIntent);
        Log.i("Log", "Discoverable ");
    }

    public static void askPermissions(Activity context, PermissionListener listener) {
        new TedPermission(context).setPermissions(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION).setPermissionListener(listener).check();
    }

}
