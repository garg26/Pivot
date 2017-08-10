package com.pivot.pivot.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atid.lib.ATRfidManager;
import com.atid.lib.ATRfidReader;
import com.atid.lib.barcode.type.BarcodeType;
import com.atid.lib.device.type.ConnectionState;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.event.ATRfidEventListener;
import com.atid.lib.rfid.type.ActionState;
import com.atid.lib.rfid.type.CommandType;
import com.atid.lib.rfid.type.RemoteKeyState;
import com.atid.lib.rfid.type.ResultCode;
import com.pivot.pivot.R;

import simplifii.framework.activity.BaseActivity;

public class ReadingListActivity extends BaseReaderActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

    }

    // Initialize RFID Reader

}
