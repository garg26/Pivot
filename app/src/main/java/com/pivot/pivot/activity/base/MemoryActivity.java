package com.pivot.pivot.activity.base;

import android.view.View;

import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.type.MemoryBank;

public class MemoryActivity extends AccessActivity {

	private static final String TAG = MemoryActivity.class.getSimpleName();

	protected static final int MAX_MEM_OFFSET = 16;
	protected static final int DEFAULT_MEM_OFFSET = 2;


	private MemoryBank mBank;
	private int mOffset;

	@Override
	protected void initWidgets() {
		super.initWidgets();

		// Bank Spinner

		setMemBank(MemoryBank.EPC);
		setMemOffset(DEFAULT_MEM_OFFSET);

		ATLog.i(TAG, "INFO. initWidgets()");
	}

	@Override
	public void onClick(View v) {
		ATLog.i(TAG, "INFO. onClick");
		super.onClick(v);
	}

	@Override
	protected void clearWidgets() {
		super.clearWidgets();

		ATLog.i(TAG, "INFO. clearWidgets()");
	}

	protected void setMemBank(MemoryBank bank) {
		mBank = bank;
		ATLog.i(TAG, "INFO. setMemBank(%s)", bank);
	}

	protected MemoryBank getMemBank() {
		ATLog.i(TAG, "INFO. getMemBank() - [%s]", mBank);
		return mBank;
	}

	protected void setMemOffset(int offset) {
		mOffset = offset;
		ATLog.i(TAG, "INFO. setMemOffset(%d)", offset);
	}

	protected int getMemOffset() {
		ATLog.i(TAG, "INFO. getMemOffset() - [%d]", mOffset);
		return mOffset;
	}

	private void showMemBankDialog() {

		if (!mIsEnabled)
			return;

		ATLog.i(TAG, "INFO. showMemBankDialog()");
	}

	private void showMemOffsetDialog() {

		if (!mIsEnabled)
			return;
		ATLog.i(TAG, "INFO. showMemOffsetDialog()");
	}
}
