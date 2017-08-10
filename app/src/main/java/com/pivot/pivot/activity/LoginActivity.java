package com.pivot.pivot.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pivot.pivot.ApiGenerator;
import com.pivot.pivot.R;
import com.pivot.pivot.model.LoginData;
import com.pivot.pivot.model.UserLoginResponse;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;
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


        HttpParamObject httpParamObject = ApiGenerator.userLogin(email, password);
        executeTask(AppConstants.TASK_CODES.LOGIN, httpParamObject);
//        Preferences.saveData(Preferences.LOGIN_KEY,true);
//        startNextActivity(HomeActivity.class);
//        finish();
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        switch (taskCode) {
            case AppConstants.TASK_CODES.LOGIN:
                UserLoginResponse userLoginResponse = (UserLoginResponse) response;
                if (userLoginResponse != null) {
                    LoginData data = userLoginResponse.getData();
                    if (data != null) {

                        String token = data.getToken();
                        if (!TextUtils.isEmpty(token))
                            Preferences.saveData(Preferences.KEY_TOKEN, token);

                    }
                    Preferences.saveData(Preferences.LOGIN_KEY,true);
                    startNextActivity(HomeActivity.class);
                    finish();

                }
                break;
        }
    }
}
