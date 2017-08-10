package com.pivot.pivot.activity.base;

import android.view.View;
import android.widget.TextView;

import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.exception.ATRfidReaderException;
import com.atid.lib.rfid.params.MinMaxValue;

import java.util.Locale;

public abstract class RfidPowerGainActivity extends RfidClearActivity {

	private static final String TAG = RfidPowerGainActivity.class.getSimpleName();

	private static final int DEFAULT_POWER_GAIN = 300;

	private int mPowerGain;
	private int mOperationTime;
	private MinMaxValue mPowerRange;

	protected boolean mIsEnabled;

	@Override
	protected void createReader() {
		super.createReader();
		mPowerGain = DEFAULT_POWER_GAIN;
		mPowerRange = getReader().getPowerGainRange();
		mIsEnabled = false;

		ATLog.i(TAG, "INFO. createReader()");
	}

	// Initialize Reader
	@Override
	protected void initReader() {
		int power = 0;
		int time = 0;

		// Get Power Gain
		try {
			power = getReader().getPowerGain();
		} catch (ATRfidReaderException e) {
			power = mPowerRange.getMax();
		}
		setPowerGain(power);
		// Get Operation Time
		try {
			time = getReader().getOperationTime();
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, e, "ERROR. initReader() - Failed to get operation time");
			time = 0;
		}
		setOperationTime(time);

		ATLog.i(TAG, "INFO. initReader()");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

	protected void initWidgets() {
		super.initWidgets();

		// Power Gain Spinner
		setOperationTime(mOperationTime);
		
		ATLog.i(TAG, "INFO. initWidgets()");
	}

	protected void enableActionWidgets(boolean enabled) {
		super.enableActionWidgets(enabled);

		mIsEnabled = isEnabledWidget(enabled);

		ATLog.i(TAG, "INFO. enableActionWidgets(%s)", enabled);
	}

	protected void setPowerGain(int power) {
		mPowerGain = power;
//		txtPower.setText(String.format(Locale.US, "%.1f dBm", (double) power / 10.0));

		try {
			getReader().setPowerGain(mPowerGain);
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, "ERROR. setPowerGain(%d) - Failed to set power gain", mPowerGain);
			return;
		}
		ATLog.i(TAG, "INFO. setPowerGain(%d)", power);
	}

	protected int getPowerGain() {
		ATLog.i(TAG, "INFO. getPowerGain() - [%d]", mPowerGain);
		return mPowerGain;
	}

	protected void setOperationTime(int time) {
		mOperationTime = time;
//		txtOperationTime.setText(String.format(Locale.US, "%d ms", mOperationTime));
		ATLog.i(TAG, "INFO. setOperationTime(%d)", time);
	}

	protected int getOperationTime() {
		ATLog.i(TAG, "INFO. getOperationTime() - [%d]", mOperationTime);
		return mOperationTime;
	}

	private void showPowerGainDialog() {

		if (!mIsEnabled)
			return;

		ATLog.i(TAG, "INFO. showPowerGinaDialog()");
	}

	private void showOperationTimeDialog() {

		if (!mIsEnabled)
			return;

		ATLog.i(TAG, "INFO. showOperationTimeDialog()");
	}
}
