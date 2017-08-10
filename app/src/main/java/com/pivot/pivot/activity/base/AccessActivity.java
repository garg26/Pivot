package com.pivot.pivot.activity.base;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.exception.ATRfidReaderException;
import com.atid.lib.rfid.type.ResultCode;

public abstract class AccessActivity extends RfidMaskActivity implements OnCheckedChangeListener {

    private static final String TAG = AccessActivity.class.getSimpleName();

    public static final int VIEW_READ_MEMORY = 1003;
    public static final int VIEW_WRITE_MEMORY = 1004;
    public static final int VIEW_TAG_ACCESS = 1005;


    private boolean mLastContinuousMode;
    private String mMask;
    private String mPassword;

    private boolean mIsActionResult;

    public AccessActivity() {
        mMask = "";
        mIsActionResult = false;
    }

    @Override
    protected void initReader() {
        super.initReader();

        // Continuous Mode Backup
        try {
            mLastContinuousMode = getReader().getContinuousMode();
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to get continuous mode");
        }
        try {
            getReader().setContinuousMode(false);
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to set continuous mode");
        }

        // Get RSSI Mode
        try {
            boolean isChecked = getReader().getReportRSSI();
            isChecked = false;
            getReader().setReportRSSI(isChecked);
        } catch (Exception e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Faield to get rssi report mode");
        }

        ATLog.i(TAG, "INFO. initReader()");
    }

    @Override
    protected void destroyReader() {

        ATLog.i(TAG, "INFO. destroyReader()");

        try {
            getReader().setContinuousMode(mLastContinuousMode);
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. destroyReader() - Failed to set continuous mode backup");
        }

        super.destroyReader();
    }

    @Override
    public void onClick(View v) {
        ATLog.i(TAG, "INFO. onClick");

        super.onClick(v);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    // Stop Action
    protected void stopAction() {
        ResultCode res = ResultCode.NoError;
        enableActionWidgets(false);
        outputMessage("StopAction");

        if ((res = getReader().stop()) != ResultCode.NoError) {
            ATLog.e(TAG, "ERROR. stopAction() - Failed to stop operation [%s]", res);
            enableActionWidgets(true);
            outputFailMessage("Stop Action Failed");
            return;
        }

        ATLog.i(TAG, "INFO. stopAction()");
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();


        ATLog.i(TAG, "INFO. initWidgets()");
    }

    @Override
    protected void enableActionWidgets(boolean enabled) {
        super.enableActionWidgets(enabled);

        ATLog.i(TAG, "INFO. enableActionWidgets(%s)", enabled);
    }

    @Override
    protected void clearWidgets() {
        outputSelection("");
        outputMessage("");

        ATLog.i(TAG, "INFO. clearWidgets()");
    }

    protected void setMask(String mask) {
        mMask = mask;

        ATLog.i(TAG, "INFO. setMask([%s])", mask);
    }

    protected void outputSelection(String epc) {
        if (!mMask.equals(""))
            return;


        ATLog.i(TAG, "INFO. outputSelection([%s])", epc);
    }

    protected void outputMessage(int id) {
        outputMessage(getString(id));
    }

    protected void outputMessage(String msg) {

        ATLog.i(TAG, "INFO. outputMessage([%s])", msg);
    }

    protected void outputSuccessMessage(int id) {
        outputSuccessMessage(getString(id));
    }

    protected void outputSuccessMessage(String msg) {

        ATLog.i(TAG, "INFO. outputSuccessMessage([%s])", msg);
    }

    protected void outputFailMessage(int id) {
        outputFailMessage(getString(id));
    }

    protected void outputFailMessage(String msg) {

        ATLog.i(TAG, "INFO. outputFailMessage([%s])", msg);
    }

    protected synchronized boolean isActionResult() {
        return mIsActionResult;
    }

    protected synchronized void setActionResult() {
        mIsActionResult = true;
    }

    protected synchronized void resetActionResult() {
        mIsActionResult = false;
    }

    protected void setAccessPassword(String password) {
        mPassword = password;
        ATLog.i(TAG, "INFO. setAccessPassword([%s])", password);
    }

    protected String getAccessPassword() {
        ATLog.i(TAG, "INFO. getAccessPassword() - [%s]", mPassword);
        return mPassword;
    }

    protected void setRssi(float rssi, float phase) {

        ATLog.i(TAG, "INFO. setRssi(%.2f, %.2f)", rssi, phase);
    }

    protected String getRequestCode(int requestCode) {
        switch (requestCode) {
            case VIEW_READ_MEMORY:
                return "VIEW_READ_MEMORY";
            case VIEW_WRITE_MEMORY:
                return "VIEW_WRITE_MEMORY";
            case VIEW_TAG_ACCESS:
                return "VIEW_TAG_ACCESS";
        }
        return super.getRequestCode(requestCode);
    }

    private void showAccessPasswordDialog() {
        if (!mIsEnabled)
            return;


        ATLog.i(TAG, "INFO. showAccessPasswordDialog()");
    }
}
