package com.pivot.pivot.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.atid.lib.ATRfidReader;
import com.atid.lib.diagnostics.ATLog;
import com.atid.lib.rfid.exception.ATRfidReaderException;
import com.atid.lib.rfid.type.ActionState;
import com.atid.lib.rfid.type.MemoryBank;
import com.atid.lib.rfid.type.ResultCode;
import com.pivot.pivot.ApiGenerator;
import com.pivot.pivot.R;
import com.pivot.pivot.activity.base.BaseInventoryActivity;
import com.pivot.pivot.adapters.TagListAdapter;
import com.pivot.pivot.model.IdentifyTagsInstrument;
import com.pivot.pivot.model.IdentifyTagsResponse;
import com.pivot.pivot.model.IdentifyTagsSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;

public class InventoryActivity extends BaseInventoryActivity {

    private static final String TAG = InventoryActivity.class.getSimpleName();

    private static final int DEFAULT_TID_LENGTH = 2;

    private long mTotalTagCount;
    private long mLastTime;
    private double mTagSpeed;

    //2016-06-01 mkj90 ReInventoryTime
    private long mSpeedTagCount;  //2016-06-01 TotalTagCount�� Speed�� ���� �̹Ƿ� ���� ����.

    private int mRestartTime;
    private int mTidLength;
    private boolean mIsRunRestart;
    private Handler mHandler;

    private TagListAdapter tagListAdapter;
    private ListView listView;
    private boolean isStopped;
    private TextView emptyView, scanCount;
    private ArrayList<String> tagList = new ArrayList<>();
    private ArrayList<IdentifyTagsSets> setsList = new ArrayList<>();
    private ArrayList<IdentifyTagsInstrument> instrumentList = new ArrayList<>();
    private CustomListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        ATLog.i(TAG, "INFO. onCreate()");

        mHandler = new Handler();

        // Create Reader
        createReader();

        // Initialize Mask
        initMask();

        // Initialize Widget
        initWidgets();

        // Initialize Reader
        initReader();

        mTotalTagCount = 0;
        mLastTime = 0;
        mTagSpeed = 0.0;
        mRestartTime = 0;
        mIsRunRestart = false;
        mTidLength = 2;

        // Enable action widget
        enableActionWidgets(true);

        //2016-06-01 mkj90 ReInventoryHandler
        mSpeedTagCount = 0;
        startInventory();
        setActionText(false);


        setOnClickListener(R.id.tv_start_stop_read);
    }

    @Override
    public void onDestroy() {

        ATLog.i(TAG, "INFO. onDestroy()");

        // Destroy Reader
        destroyReader();

//        adpTags.shutDown();

        super.onDestroy();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        super.onCheckedChanged(buttonView, isChecked);

        ATLog.i(TAG, "INFO. onCheckedChanged");

//		switch (buttonView.getId()) {
//		case R.id.continuous_mode:
//			try {
//				getReader().setContinuousMode(isChecked);
//			} catch (ATRfidReaderException e) {
//				ATLog.e(TAG, e, "ERROR. onCheckedChanged(%s, %s) - Failed to set continuous mode",
//						ResUtil.getId(buttonView.getId()), isChecked);
//			}
//			break;
//		case R.id.report_rssi:
//			try {
//				getReader().setReportRSSI(isChecked);
//			} catch (ATRfidReaderException e) {
//				ATLog.e(TAG, e, "ERROR. onCheckedChanged(%s, %s) - Failed to set report RSSI",
//						ResUtil.getId(buttonView.getId()), isChecked);
//			}
//			clearWidgets();
//			adpTags.setReportRSSI(isChecked);
//			break;
//		case R.id.report_tid:
//			try {
//				getReader().setOperationMode(isChecked ? OperationMode.TID : OperationMode.Normal);
//			} catch (ATRfidReaderException e) {
//				ATLog.e(TAG, e, "ERROR. onCheckedChanged(%s, %s) - Failed to set report TID",
//						ResUtil.getId(buttonView.getId()), isChecked);
//			}
//			clearWidgets();
//			adpTags.setReportTID(isChecked);
//			break;
//		}
    }

    @Override
    public void onClick(View v) {
        ATLog.i(TAG, "INFO. onClick");
//		switch (v.getId()) {
//		case R.id.restart_time:
//			showRestartTimeDialog();
//			break;
//		case R.id.tid_length:
//			showTidLengthDialog();
//			break;
//		}
        switch (v.getId()) {
            case R.id.tv_start_stop_read:
                // identifyTags();
                if (isStopped) {
                    stopInventory();
                } else {
                    startInventory();
                }
                break;
        }
        super.onClick(v);
    }

    private void identifyTags() {

        if (CollectionUtils.isNotEmpty(tagList)) {
            String tagList = TextUtils.join(",", this.tagList);
            HttpParamObject httpParamObject = ApiGenerator.identifyTag(tagList);
            executeTask(AppConstants.TASK_CODES.IDENTIFY_TAGS, httpParamObject);
        }
    }


    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        switch (taskCode) {
            case AppConstants.TASK_CODES.IDENTIFY_TAGS:
                Bundle bundle = new Bundle();
                IdentifyTagsResponse identifyTagsResponse = (IdentifyTagsResponse) response;
                if (identifyTagsResponse != null) {
                    ArrayList<IdentifyTagsInstrument> instruments = (ArrayList<IdentifyTagsInstrument>) identifyTagsResponse.getInstruments();
                    ArrayList<IdentifyTagsSets> sets = (ArrayList<IdentifyTagsSets>) identifyTagsResponse.getSets();

                    if (CollectionUtils.isNotEmpty(sets)) {
                        bundle.putSerializable(AppConstants.BUNDLE_KEYS.SETS, sets);
                        setsList.addAll(sets);
                    }
                    if (CollectionUtils.isNotEmpty(instruments)) {
                        bundle.putSerializable(AppConstants.BUNDLE_KEYS.INSTRUMENT, instruments);
                        instrumentList.addAll(instruments);

                    }
                    if (tagList != null) {
                        bundle.putSerializable(AppConstants.BUNDLE_KEYS.TAGLIST, tagList);
                    }
                    startNextActivity(bundle, ReadActivity.class);
                    finish();

                }
                break;
        }
    }

    @Override
    public void onReadedTag(ATRfidReader reader, ActionState action, String tag, float rssi, float phase) {
        long time = System.currentTimeMillis();
        double interval = 0.0;

        if (!tagList.contains(tag)) {
            tagList.add(tag);
        }
        ATLog.i(TAG, "EVENT. onReadedTag(%s, [%s], %.2f, %.2f)", action, tag, rssi, phase);

        Log.d(TAG, "Tag Readed:" + tag);
        mTotalTagCount++;
        mSpeedTagCount++;  //2016-06-01 mkj90 TagSpeed �߰�
        if (mLastTime == 0) {
            mTagSpeed = 0.0;
            mLastTime = time;
        } else {
            interval = (double) (time - mLastTime) / 1000.0;
            mTagSpeed = (double) mSpeedTagCount / interval;
        }
        ATLog.d(TAG, "@@@ DEBUG. Tag Speed [%.2f, %d, %.3f, %d, %d]", mTagSpeed, mTotalTagCount, interval, time,
                mLastTime);

        //tagListAdapter.addTag(tag, rssi, phase);
        //tagListAdapter.notifyDataSetChanged();
//        listAdapter.notifyDataSetChanged();
//        setEmptyView();
        super.onReadedTag(reader, action, tag, rssi, phase);
    }

    private void setEmptyView() {
        if (listAdapter.getCount() > 0) {
            emptyView.setVisibility(View.GONE);
            scanCount.setVisibility(View.VISIBLE);
            scanCount.setText("Total tags scanned : " + listAdapter.getCount());
        } else {
            scanCount.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAccessResult(ATRfidReader reader, ResultCode code, ActionState action, String epc, String data,
                               float rssi, float phase) {

        long time = System.currentTimeMillis();
        double interval = 0.0;

        ATLog.i(TAG, "EVENT. onAccessResult(%s, [%s], [%s], %.2f, %.2f)", action, epc, data, rssi, phase);


        mTotalTagCount++;
        mSpeedTagCount++;
        if (mLastTime == 0) {
            mTagSpeed = 0.0;
            mLastTime = time;
        } else {
            interval = (double) (time - mLastTime) / 1000.0;
            mTagSpeed = (double) mSpeedTagCount / interval;
        }
        ATLog.d(TAG, "@@@ DEBUG. Tag Speed [%.2f, %d, %.3f, %d, %d]", mTagSpeed, mTotalTagCount, interval, time,
                mLastTime);
        //tagListAdapter.addTag(epc, data, rssi, phase);
        //tagListAdapter.notifyDataSetChanged();
        //setEmptyView();
        super.onAccessResult(reader, code, action, epc, data, rssi, phase);
//
//		txtTotalCount.setText("" + mTotalTagCount);
//		txtSpeed.setText(String.format(Locale.US, "%.2f tps", mTagSpeed));
    }

    @Override
    public void onActionChanged(ATRfidReader reader, ActionState action) {
        super.onActionChanged(reader, action);
        if (action == ActionState.Stop) {
            setActionText(true);
            identifyTags();
        } else {
            setActionText(false);
        }
//		if (mIsRunRestart) {
//			adpTags.notifyDataSetChanged();
//			if (action != ActionState.Stop) {
//				enableActionWidgets(true);
//			}
//		} else {
//			super.onActionChanged(reader, action);
//		}
//
//		if(action == ActionState.Stop){
//			ATLog.e(TAG, "INFO. onActionChanged()   "+action);
//			mSpeedTagCount = 0;
//			mLastTime =0;
//
//			if (mRestartTime > 0 && mIsRunRestart) {
//				mHandler.postDelayed(mRestartHandler, mRestartTime);
//			}
//		}
    }

    private void setActionText(boolean isStopped) {
//        if (isStopped) {
//            setText(getString(R.string.start_scan), R.id.tv_start_stop_read);
//        } else {
//            setText(getString(R.string.stop_scanning), R.id.tv_start_stop_read);
//        }
        this.isStopped = isStopped;
    }

    // Initialize Reader
    @Override
    protected void initReader() {
        super.initReader();
        // Set Stored Mode Disable
        try {
            getReader().setStoredMode(false);
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to set stored mode disable");
        }
        // Set Rpoert Mode Disable
        try {
            getReader().setReportMode(false);
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to set report mode disable");
        }
        // Set Reader Continuous Mode
        try {
            getReader().setContinuousMode(true);
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to get continuous mode");
        }
//		swtContinuousMode.setChecked(true);
        // Get Report RSSI
        boolean isReportRSSI = false;
//		swtReportRSSI.setChecked(isReportRSSI);
//		adpTags.setReportRSSI(isReportRSSI);
        // Get Report TID
        boolean isReportTID = false;
//		swtReportTID.setChecked(isReportTID);
//		adpTags.setReportTID(isReportTID);
        // TID Length
        int tidLength = DEFAULT_TID_LENGTH;
        try {
            tidLength = getReader().getActionTIDReadLength();
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. initReader() - Failed to get continuous mode");
        }
        setTidLength(tidLength);

        ATLog.i(TAG, "INFO. initReader()");
    }

    // Initialize Widgets
    @Override
    protected void initWidgets() {
        super.initWidgets();
        initToolBar(getString(R.string.scanned_tags_list));
        listView = (ListView) findViewById(R.id.listView);
//        listAdapter = new CustomListAdapter(this, R.layout.item_tag_list, setsList, this);
//        listView.setAdapter(listAdapter);
//        tagListAdapter = new TagListAdapter(this);

        emptyView = (TextView) findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);
        scanCount = (TextView) findViewById(R.id.tv_scan_count);
//		// Continuous Mode CheckBox
//		swtContinuousMode = (Switch) findViewById(R.id.continuous_mode);
//		swtContinuousMode.setOnCheckedChangeListener(this);
//		// Report RSSI
//		swtReportRSSI = (Switch) findViewById(R.id.report_rssi);
//		swtReportRSSI.setOnCheckedChangeListener(this);
//		// Report TID
//		swtReportTID = (Switch) findViewById(R.id.report_tid);
//		swtReportTID.setOnCheckedChangeListener(this);
//
//		txtTidLength = (TextView)findViewById(R.id.tid_length);
//		txtTidLength.setOnClickListener(this);
        setTidLength(mTidLength);

        // Total Count TextView
//		txtTotalCount = (TextView) findViewById(R.id.total_tag_count);
//		txtTotalCount.setText(String.format(Locale.US, "%d", mTotalTagCount));
//
//		txtSpeed = (TextView) findViewById(R.id.speed);
//		txtSpeed.setText("0 tps");
//		// txtSpeed.setVisibility(View.GONE);
//
//		txtRestartTime = (TextView)findViewById(R.id.restart_time);
//		txtRestartTime.setOnClickListener(this);
        setRestartTime(mRestartTime);

        ATLog.i(TAG, "INFO. initWidgets()");
    }

    // Enable/Disable Action Widgets
    @Override
    protected void enableActionWidgets(boolean enabled) {
        super.enableActionWidgets(enabled);
//
//		swtContinuousMode.setEnabled(isEnabledWidget(enabled));
//		swtReportRSSI.setEnabled(isEnabledWidget(enabled));
//		swtReportTID.setEnabled(isEnabledWidget(enabled));
        ATLog.i(TAG, "INFO. enableActionWidgets(%s)", enabled);
    }

    @Override
    protected void clearWidgets() {
        super.clearWidgets();

        mSpeedTagCount = 0; // 2016-06-01 mkj90 SpeedTagCount �ʱ�ȭ.
        mTotalTagCount = 0;
        mLastTime = 0;
        mTagSpeed = 0.0;
//		txtTotalCount.setText("" + mTotalTagCount);
//		txtSpeed.setText(String.format(Locale.US, "%.2f tps", mTagSpeed));

        ATLog.i(TAG, "INFO. clearWidgets()");
    }

    private void setRestartTime(int time) {
        mRestartTime = time;
//		txtRestartTime.setText(String.format(Locale.US, "%d ms", mRestartTime));
        ATLog.i(TAG, "INFO. setOperationTime(%d)", time);
    }

    private void showRestartTimeDialog() {

        if (!mIsEnabled)
            return;


        ATLog.i(TAG, "INFO. showRestartTimeDialog()");
    }

    private void setTidLength(int length) {
        mTidLength = length;
//		txtTidLength.setText(String.format(Locale.US, "%d word", mTidLength));
        ATLog.i(TAG, "INFO. setTidLength(%d)", length);
    }

    private void showTidLengthDialog() {
        if (!mIsEnabled)
            return;
//		if (!adpTags.getReportTID())
//			return;

        ATLog.i(TAG, "INFO. showTidLengthDialog()");
    }

    @Override
    protected void startInventory() {
        mIsRunRestart = mRestartTime > 0;
        super.startInventory();
//        if (adpTags.getReportTID()) {
//            startTidInventory();
//        } else {
//            super.startInventory();
//        }
    }

    @Override
    protected void stopInventory() {
        mIsRunRestart = false;
        super.stopInventory();
    }

    private void startTidInventory() {
        enableActionWidgets(false);
        int time = getOperationTime();
        int count = 2;
        try {
            getReader().setOperationTime(time);
            count = getReader().getActionTIDReadLength();
        } catch (ATRfidReaderException e) {
            ATLog.e(TAG, e, "ERROR. startTidInventory() - Failed to set operation time(%d)", time);
        }
        if (getReader().readMemory(MemoryBank.TID, 0, count) != ResultCode.NoError) {
            ATLog.e(TAG, "ERROR. startTidInventory() - Failed to start TID inventory()");
            enableActionWidgets(true);
            return;
        }
        ATLog.i(TAG, "INFO. startTidInventory()");
    }

    private Runnable mRestartHandler = new Runnable() {

        @Override
        public void run() {
            if (getReader().inventory() != ResultCode.NoError) {
                ATLog.e(TAG, "ERROR. $mRestartHandler.run() - Failed to start inventory()");
                enableActionWidgets(true);
                return;
            }
            ATLog.i(TAG, "INFO. $mRestartHandler.run()");
        }
    };

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ArrayList<TagListItem> list = tagListAdapter.getTagsList();
//        if (list != null && list.size() > 0) {
//            Bundle b = new Bundle();
//            b.putSerializable(AppConstants.BUNDLE_KEYS.KEY_SERIALIZABLE_OBJECT, list);
//            b.putInt(AppConstants.BUNDLE_KEYS.KEY_POSITION, i);
//            startNextActivity(b, ReadActivity.class);
//        }
//    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
//        Holder holder = null;
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.item_tag_list, parent, false);
//            holder = new Holder(convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (Holder) convertView.getTag();
//        }
//        IdentifyTagsSets tagsSets = setsList.get(position);
//        if (tagsSets != null) {
//            String name = tagsSets.getName();
//            if (!TextUtils.isEmpty(name)) {
//                holder.tvItem.setText(name);
//            }
//        }
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onItemClick(null, view, position, 0);
//            }
//        });
//        return convertView;
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        String name = setsList.get(position).getName();
//
//        switch (name) {
//            case "Set 0":
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(AppConstants.BUNDLE_KEYS.SETSLIST, setsList);
//                startNextActivity(bundle, SetTagInstrumentActivity.class);
//                break;
//        }
//    }
//
//    class Holder {
//        TextView tvItem;
//
//        public Holder(View view) {
//            tvItem = (TextView) view.findViewById(R.id.tag_value);
//        }
//    }
}
