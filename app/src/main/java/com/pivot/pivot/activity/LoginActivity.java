package com.pivot.pivot.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gun0912.tedpermission.PermissionListener;
import com.pivot.pivot.R;
import com.pivot.pivot.bluetooth.BluetoothManager;
import com.pivot.pivot.bluetooth.BluetoothReceiver;

import java.util.ArrayList;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.Util;

public class LoginActivity extends BaseActivity {

    private Button btnLogin;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_pass);

        btnLogin.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_login:
//                enableBluetoothReader();
                validateEntry();
                break;
        }
    }

    private void validateEntry() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            showToast(getString(R.string.empty_email));
            return;
        } else if (!Util.isValidEmail(email)) {
            showToast(getString(R.string.invalid_email));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(getString(R.string.empty_pasword));
            return;
        } else if (password.length() <= 4) {
            showToast(getString(R.string.short_password));
            return;
        }

        startNextActivity(HomeActivity.class);
        finish();
    }
}
