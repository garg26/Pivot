package com.pivot.pivot.activity.base;

import android.view.View;
import android.view.View.OnClickListener;

import com.atid.lib.diagnostics.ATLog;

public abstract class RfidClearActivity extends RfidActivity implements
        OnClickListener {

    private static final String TAG = RfidClearActivity.class.getSimpleName();


    @Override
    public void onClick(View v) {

        ATLog.i(TAG, "INFO. onClick");
    }

    protected abstract void clearWidgets();

    protected void initWidgets() {

        ATLog.i(TAG, "INFO. initWidgets()");
    }

    protected void enableActionWidgets(boolean enabled) {

        ATLog.i(TAG, "INFO. enableActionWidgets(%s)", enabled);
    }
}
