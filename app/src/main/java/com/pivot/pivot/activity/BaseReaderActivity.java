package com.pivot.pivot.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.atid.lib.ATRfidManager;
import com.atid.lib.ATRfidReader;
import com.atid.lib.barcode.type.BarcodeType;
import com.atid.lib.device.type.ConnectionState;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.event.ATRfidEventListener;
import com.atid.lib.rfid.exception.ATRfidReaderException;
import com.atid.lib.rfid.type.ActionState;
import com.atid.lib.rfid.type.CommandType;
import com.atid.lib.rfid.type.RemoteKeyState;
import com.atid.lib.rfid.type.ResultCode;
import com.pivot.pivot.R;
import com.pivot.pivot.activity.base.RfidActivity;
import com.pivot.pivot.type.MaskType;

import java.util.Timer;
import java.util.TimerTask;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by nbansal2211 on 12/06/17.
 */

public class BaseReaderActivity extends BaseActivity implements ATRfidEventListener {
    protected ATRfidReader mReader;
    protected String mDeviceAddress;
    private Timer timerCheckBattery;

    private static final String TAG = "BaseReader";

    private static final String LOG_PATH = "Log";
    private static final String LOG_PREFIX = "RFBlasterDemo";

    private static final String APP_NAME = "rfid.reader.demo";
    private static final String KEY_DEVICE_ADDRESS = "device_address";
    private static final String KEY_BATTERY_INTERVAL = "battery_interval";
    private static final String KEY_MASK_TYPE = "mask_type";

    private static final String DEFAULT_DEVICE_ADDRESS = null;
    private static final int DEFAULT_BATTERY_INTERVAL = 10;
    private static final int DEFAULT_MASK_TYPE = 0;
    private static final int DEFAULT_OPERATION_TIME = 0;
    private static final int MAX_LOGO_TOUCH_COUNT = 5;

    private static final int VIEW_INVENTORY = 1000;
    private static final int VIEW_FILTER_INVENTORY = 1001;
    private static final int VIEW_STORED_TAG = 1002;
    private static final int VIEW_OPTION = 1006;
    private static final int VIEW_BARCODE_DEMO = 1007;
    private static final int VIEW_BARCODE_OPTION = 1008;

    private static final int RESULT_DISCONNECTED = Activity.RESULT_FIRST_USER + 1;
    private int mBatteryInterval;
    private MaskType mMaskType;
    private boolean mKeyAction;

    protected void initATRfidReader() {

        ATRfidManager.setContext(this);
        if (!ATRfidManager.isBluetoothSupported()) {
            showToast(R.string.bluetooth_not_supported);
        }

        ATRfidManager.checkEnableBluetooth(this);


        mReader = ATRfidManager.getInstance();
        mReader.setAddress(mDeviceAddress);
        mReader.setEventListener(this);

        ATLog.i(TAG, "INFO. initATRfidReader()");
    }

    // Load Configuration
    private void loadConfig() {
        SharedPreferences prefs = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        mDeviceAddress = prefs.getString(KEY_DEVICE_ADDRESS, DEFAULT_DEVICE_ADDRESS);
        ATLog.d(TAG, "DEBUG. loadConfig() - Device Address : [%s]", mDeviceAddress);
        mBatteryInterval = prefs.getInt(KEY_BATTERY_INTERVAL, DEFAULT_BATTERY_INTERVAL);
        if (mBatteryInterval > 60) mBatteryInterval = DEFAULT_BATTERY_INTERVAL;
        ATLog.d(TAG, "DEBUG. loadConfig() - Batery Check Interval : [%d]", mBatteryInterval);
        mMaskType = MaskType.valueOf(prefs.getInt(KEY_MASK_TYPE, DEFAULT_MASK_TYPE));
        ATLog.d(TAG, "DEBUG. loadConfig() - Mask Type : [%s]", mMaskType);
        mKeyAction = prefs.getBoolean(RfidActivity.KEY_ACTION, RfidActivity.DEFAULT_KEY_ACTION);
        ATLog.d(TAG, "DEBUG. loadConfig(); - Key Action : %s", mKeyAction);

        ATLog.i(TAG, "INFO. loadConfig()");
    }

    // Save Configuration
    private void saveConfig() {
        SharedPreferences prefs = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(KEY_DEVICE_ADDRESS, mDeviceAddress);
        ATLog.d(TAG, "DEBUG. saveConfig() - Device Address : [%s]", mDeviceAddress);
        editor.putInt(KEY_BATTERY_INTERVAL, mBatteryInterval);
        ATLog.d(TAG, "DEBUG. saveConfig() - Batery Check Interval : [%d]", mBatteryInterval);
        editor.putInt(KEY_MASK_TYPE, mMaskType.getCode());
        ATLog.d(TAG, "DEBUG. saveConfig() - Mask Type : [%s]", mMaskType);
        editor.putBoolean(RfidActivity.KEY_ACTION, mKeyAction);
        ATLog.d(TAG, "DEBUG. saveConfig() - Key Action : %s", mKeyAction);

        editor.commit();

        ATLog.i(TAG, "INFO. saveConfig()");
    }

    private void startCheckBattery() {
        ATLog.i(TAG, "INFO. startCheckBattery()");
        int interval = mBatteryInterval * 1000;
        timerCheckBattery = new Timer();
        timerCheckBattery.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(mActionCheckBattery);
            }

        }, interval, interval);
    }

    private void stopCheckBattery() {
        if (timerCheckBattery == null)
            return;
        timerCheckBattery.cancel();
        timerCheckBattery = null;
        ATLog.i(TAG, "INFO. stopCheckBattery()");
    }

    private Runnable mActionCheckBattery = new Runnable() {

        @Override
        public void run() {
            if (timerCheckBattery != null)
                checkBattery();
        }

    };

    private void checkBattery() {

        int battery = 0;

        try {
            battery = mReader.getBatteryStatus();
        } catch (ATRfidReaderException e) {
            battery = 0;
        }

        ATLog.i(TAG, "INFO. checkBattery() - [%d]", battery);
    }

    private void setDisconnectedState() {

        mReader.disconnectDevice();

        stopCheckBattery();

        ATLog.i(TAG, "INFO. setDisconnectedState()");
    }


    private String getRequestCode(int requestCode) {
        switch (requestCode) {
            case ATRfidManager.REQUEST_DEVICE_LIST:
                return "REQUEST_DEVICE_LIST";
            case ATRfidManager.REQUEST_BLE_DEVICE_LIST:
                return "REQUEST_BLE_DEVICE_LIST";
            case ATRfidManager.REQUEST_ENABLE_BLUETOOTH:
                return "REQUEST_ENABLE_BLUETOOTH";
        }
        return "";
    }

    private String getResultCode(int resultCode) {
        switch (resultCode) {
            case Activity.RESULT_CANCELED:
                return "RESULT_CANCELED";
            case Activity.RESULT_OK:
                return "RESULT_OK";
            case Activity.RESULT_FIRST_USER:
                return "RESULT_FIRST_USER";
            case RESULT_DISCONNECTED:
                return "RESULT_DISCONNECTED";
        }
        return "";
    }

    @Override
    public void onActionChanged(ATRfidReader reader, ActionState action) {
        ATLog.d(TAG, "EVENT. onActionChanged(%s)", action);


    }

    @Override
    public void onCommandComplete(ATRfidReader reader, CommandType command) {
        ATLog.d(TAG, "EVENT. onCommandComplete(%s)", command);

    }

    @Override
    public void onReadedTag(ATRfidReader reader, ActionState action, String tag, float rssi, float phase) {

        ATLog.i(TAG, "EVENT. onReadedTag(%s, [%s], %.2f, %.2f)", action, tag, rssi, phase);

    }

    @Override
    public void onAccessResult(ATRfidReader reader, ResultCode code, ActionState action, String epc, String data,
                               float rssi, float phase) {

        ATLog.i(TAG, "EVENT. onAccessResult(%s, [%s], [%s]%.2f, %.2f)", action, epc, data, rssi, phase);

    }


    @Override
    public void onStateChanged(ATRfidReader atRfidReader, ConnectionState connectionState) {
        Log.d(TAG, "onStateChanged");
    }


    @Override
    public void onLoadTag(ATRfidReader atRfidReader, String s) {
        Log.d(TAG, "onLoadTag");
    }

    @Override
    public void onDebugMessage(ATRfidReader atRfidReader, String s) {
        Log.d(TAG, "onDebugMessage");
    }

    @Override
    public void onDetactBarcode(ATRfidReader atRfidReader, BarcodeType barcodeType, String s, String s1) {
        Log.d(TAG, "onDetectBarCode");
    }

    @Override
    public void onRemoteKeyStateChanged(ATRfidReader atRfidReader, RemoteKeyState remoteKeyState) {
        Log.d(TAG, "onRemoteKeyStateChanged");
    }
}
