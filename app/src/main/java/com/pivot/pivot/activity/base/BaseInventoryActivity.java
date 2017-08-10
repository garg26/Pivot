package com.pivot.pivot.activity.base;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.atid.lib.ATRfidReader;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.exception.ATRfidReaderException;
import com.atid.lib.rfid.type.ActionState;
import com.atid.lib.rfid.type.CommandType;
import com.atid.lib.rfid.type.ResultCode;

public class BaseInventoryActivity extends RfidMaskActivity implements OnCheckedChangeListener {

	private static final String TAG = BaseInventoryActivity.class.getSimpleName();


	@Override
	protected void onStart() {
		super.onStart();

//		// Set Key Action
//		try {
//			getReader().setUseKeyAction(getKeyAction());
//		} catch (ATRfidReaderException e) {
//			ATLog.e(TAG, e, "ERROR. onStart() - Failed to set use key action");
//		}
		
//		ATLog.i(TAG, "INFO. onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		// Set Key Action
		try {
			getReader().setUseKeyAction(getKeyAction());
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, e, "ERROR. onStart() - Failed to set use key action");
		}
		
		ATLog.i(TAG, "INFO. onResume()");
	}

	@Override
	protected void onPause() {
		// Set Key Action
		try {
			getReader().setUseKeyAction(false);
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, e, "ERROR. onStart() - Failed to set use key action");
		}

		ATLog.i(TAG, "INFO. onPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		
		// Set Key Action
		try {
			getReader().stop();  //2016-06-17 mkj 
			getReader().setUseKeyAction(false);
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, e, "ERROR. onStart() - Failed to set use key action");
		}

		ATLog.i(TAG, "INFO. onStart()");
		super.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		ATLog.i(TAG, "INFO. onActivityResult(%s, %s)", getRequestCode(requestCode), getResultCode(resultCode));

		if (resultCode == RESULT_DISCONNECTED) {
			setResult(RESULT_DISCONNECTED);
			finish();
			return;
		}

		switch (requestCode) {
		case AccessActivity.VIEW_READ_MEMORY:
		case AccessActivity.VIEW_WRITE_MEMORY:
		case AccessActivity.VIEW_TAG_ACCESS:
			enableActionWidgets(true);
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		ATLog.i(TAG, "INFO. onCheckedChanged");

	}

	@Override
	public void onActionChanged(ATRfidReader reader, ActionState action) {
		ATLog.d(TAG, "EVENT. onActionChanged(%s)", action);


		enableActionWidgets(true);
	}

	@Override
	public void onCommandComplete(ATRfidReader reader, CommandType command) {
		ATLog.d(TAG, "EVENT. onCommandComplete(%s)", command);
		
		enableActionWidgets(true);
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

	// Initialize Widgets
	@Override
	protected void initWidgets() {
		super.initWidgets();


		ATLog.i(TAG, "INFO. initWidgets()");
	}

	// Enable/Disable Action Widgets
	@Override
	protected void enableActionWidgets(boolean enabled) {
		super.enableActionWidgets(enabled);
		ATLog.i(TAG, "INFO. enableActionWidgets(%s)", enabled);
	}
	
	@Override
	protected void clearWidgets() {


		ATLog.i(TAG, "INFO. clearWidgets()");
	}

	//2016-06-01 mkj90 private --> protected
	protected void startInventory() {
		enableActionWidgets(false);
		int time = getOperationTime();
		try {
			getReader().setOperationTime(time);
		} catch (ATRfidReaderException e) {
			ATLog.e(TAG, e, "ERROR. startInventory() - Failed to set operation time(%d)", time);
		}
		if (getReader().inventory() != ResultCode.NoError) {
			ATLog.e(TAG, "ERROR. startInventory() - Failed to start inventory()");
			enableActionWidgets(true);
			return;
		}
		ATLog.i(TAG, "INFO. startInventory()");
	}

	//2016-06-01 mkj90 private --> protected
	protected void stopInventory() {
		enableActionWidgets(false);
		if (getReader().stop() != ResultCode.NoError) {
			ATLog.e(TAG, "ERROR. stopInventory() - Failed to stop inventory()");
			enableActionWidgets(true);
			return;
		}
		ATLog.i(TAG, "INFO. stopInventory()");
	}
	
}
