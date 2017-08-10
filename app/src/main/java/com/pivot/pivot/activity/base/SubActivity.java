package com.pivot.pivot.activity.base;

import android.app.Activity;
import android.view.MenuItem;

import com.atid.lib.diagnostics.ATLog;

import java.util.Locale;

import simplifii.framework.activity.BaseActivity;

public abstract class SubActivity extends BaseActivity {

	private static final String TAG = SubActivity.class.getSimpleName();
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		ATLog.i(TAG, "INFO. onOptionsItemSelected");
		
		switch (item.getItemId()) {
		case android.R.id.home:
			this.setResult(Activity.RESULT_CANCELED);
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected abstract void initWidgets();

	protected abstract void enableActionWidgets(boolean enabled);

	protected String getRequestCode(int requestCode) {
		return String.format(Locale.US, "%d", requestCode);
	}

	protected String getResultCode(int resultCode) {
		switch (resultCode) {
		case Activity.RESULT_CANCELED:
			return "RESULT_CANCELED";
		case Activity.RESULT_OK:
			return "RESULT_OK";
		case Activity.RESULT_FIRST_USER:
			return "RESULT_FIRST_USER";
		}
		return String.format(Locale.US, "%d", resultCode);
	}
}
