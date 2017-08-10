package com.pivot.pivot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.atid.lib.ATRfidManager;
import com.atid.lib.ATRfidReader;
import com.atid.lib.device.type.ConnectionState;
import com.pivot.pivot.R;

/**
 * Created by nbansal2211 on 12/06/17.
 */

public class ReadersListActivity extends BaseReaderActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initATRfidReader();
//        ATRfidManager.openDeviceListActivity(this);
        connectDevice();
    }

    public void connectDevice() {
        mReader.connectDevice(getIntent().getStringExtra("device_device"));
//        ATRfidManager.onActivityResult(2, -1, getIntent().getStringExtra("device_device"));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_start_stop_read:
                break;
        }
    }

    @Override
    public void onStateChanged(ATRfidReader atRfidReader, ConnectionState connectionState) {
        super.onStateChanged(atRfidReader, connectionState);
        if (ConnectionState.Connected == connectionState) {
            hideProgressBar();
            showToast(R.string.connected);
            startNextActivity(null, InventoryActivity.class);
            finish();
        } else if (ConnectionState.Disconnected == connectionState) {
            hideProgressBar();
            showToast(R.string.not_able_to_connect);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ATRfidManager.REQUEST_DEVICE_LIST) {
            ATRfidManager.onActivityResult(requestCode, resultCode, data);
            showProgressDialog(getString(R.string.connecting));
        } else {
            finish();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
