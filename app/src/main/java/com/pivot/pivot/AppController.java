package com.pivot.pivot;

import android.app.Application;

import simplifii.framework.utility.Preferences;


public class AppController extends Application {
    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        Preferences.initSharedPreferences(this);


    }

    public static AppController getInstance(){
        return instance;
    }
}
