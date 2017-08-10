package com.pivot.pivot.fragments;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.pivot.pivot.R;
import com.pivot.pivot.activity.HomeActivity;
import com.pivot.pivot.bluetooth.BluetoothManager;
import com.pivot.pivot.bluetooth.BluetoothReceiver;
import com.pivot.pivot.model.BluetoothConnectionModel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;
import simplifii.framework.utility.Preferences;

/**
 * Created by mrnee on 6/2/2017.
 */

public class BluetoothConnectionFragment extends BaseFragment implements CustomListAdapterInterface, BluetoothReceiver.GetBluetoothListListner {

    private CustomListAdapter listAdapter;
    private ProgressBar progressBar;
    private TextView tvEmptyView;
    private List<BluetoothConnectionModel> connectionModelList = new ArrayList<>();
    private boolean readerSelected;
    private BluetoothReceiver receiver;
    private Boolean deviceSelected = false;
    private int REQUEST_ENABLE_BT = 0;
    //    private List<BluetoothDevice> unpairedDeviceList = new ArrayList<>();
    private List<BluetoothDevice> pairedDevices = new ArrayList<>();
//    private List<BluetoothDevice> bluetoothDeviceLists = new ArrayList<>();


    public static BluetoothConnectionFragment getInstance() {
        BluetoothConnectionFragment bluetoothConnectionFragment = new BluetoothConnectionFragment();
        return bluetoothConnectionFragment;
    }

    private void enableBluetoothReader(Context context) {

        BluetoothManager.askPermissions(context, new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                onPermissionReceived();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        });
    }


    private void onPermissionReceived() {
        // progressBar.setVisibility(View.VISIBLE);
        showProgressBar();
        BluetoothManager manager = BluetoothManager.getInstance(getActivity());
        manager.onBluetooth();
        pairedDevices = manager.getPairedDevices();
        if (CollectionUtils.isNotEmpty(pairedDevices)) {
            //    progressBar.setVisibility(View.GONE);
            hideProgressBar();
        }
        for (BluetoothDevice device : pairedDevices) {
            BluetoothConnectionModel connectionModel = new BluetoothConnectionModel();
            connectionModel.setBluetoothDevice(device);
            connectionModelList.add(connectionModel);
            connectionModel.setIfPaired(true);
        }
        setFirstObject();
        //listAdapter.notifyDataSetChanged();
        if (receiver == null) {
            receiver = new BluetoothReceiver(this);
        }
        manager.startSearching(getActivity(), receiver);
    }

    @Override
    public void initViews() {
        Button btnCancel = (Button) findView(R.id.btn_cancel);
        TextView tvCount = (TextView) findView(R.id.tv_count);
        Button btnOk = (Button) findView(R.id.btn_ok);
        tvEmptyView = (TextView) findView(R.id.tv_empty_view);
        progressBar = (ProgressBar) findView(R.id.progress_bar);
        enableBluetoothReader(getContext());
        ListView listBluetoothConnection = (ListView) findView(R.id.list_bluetooth_connection);
        listAdapter = new CustomListAdapter(getActivity(), R.layout.row_bluetooth_connection, connectionModelList, this);
        listBluetoothConnection.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        setOnClickListener(R.id.btn_cancel, R.id.btn_ok);

        listBluetoothConnection.setEmptyView(tvEmptyView);
        IntentFilter intent = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        getActivity().registerReceiver(mPairReceiver, intent);
    }


    @Override
    public int getViewID() {
        return R.layout.fragment_bluetooth_connection;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent, int resourceID, LayoutInflater inflater) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(resourceID, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final BluetoothConnectionModel bluetoothConnectionModel = connectionModelList.get(position);


        final BluetoothDevice bluetoothDevice = bluetoothConnectionModel.getBluetoothDevice();
        String myDeviceAddress = Preferences.getData(AppConstants.PREF_KEYS.BLUETOOTH_DEVICE, null);
        if (!CollectionUtils.isEmpty(myDeviceAddress)) {
            if (bluetoothDevice.getAddress().equals(myDeviceAddress)) {
                bluetoothConnectionModel.setSelected(true);
                deviceSelected = true;
                holder.radioButton.setChecked(true);
            }
        }


        if (bluetoothConnectionModel.isIfPaired() == false) {
            holder.radioButton.setVisibility(View.GONE);
            holder.btnPair.setVisibility(View.VISIBLE);
        } else {
            holder.radioButton.setVisibility(View.VISIBLE);
            holder.btnPair.setVisibility(View.GONE);
        }

        if (bluetoothConnectionModel.isFirst()) {
            holder.tvTitleMain.setVisibility(View.VISIBLE);
            if (bluetoothConnectionModel.isIfPaired()) {
                holder.tvTitleMain.setText("Paired Device");
            } else {
                holder.tvTitleMain.setText("Un-Paired Device");
            }
        } else {
            holder.tvTitleMain.setVisibility(View.GONE);
        }

        if (bluetoothDevice != null) {
            holder.tvItem.setText(bluetoothDevice.getName());
        }

        holder.btnPair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevice(bluetoothDevice);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothConnectionModel.isIfPaired()) {
                    return;
                }
                for (BluetoothConnectionModel model : connectionModelList) {
                    model.setSelected(false);
                }
                if (bluetoothDevice != null) {
                    Preferences.saveData(AppConstants.PREF_KEYS.BLUETOOTH_DEVICE, bluetoothDevice.getAddress());
                }
                bluetoothConnectionModel.setSelected(true);
                listAdapter.notifyDataSetChanged();
            }
        });


        if (bluetoothConnectionModel.isSelected() == true) {
            holder.radioButton.setChecked(true);
            readerSelected = true;
        } else {
            holder.radioButton.setChecked(false);
        }

        holder.radioButton.setClickable(false);
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    deviceSelected = true;

                }
            }
        });

        listAdapter.notifyDataSetChanged();
        return convertView;
    }


    private void pairedDevice(BluetoothDevice bluetoothDevice) {
        try {
            Log.d("pairDevice()", "Start Pairing...");
            Method m = bluetoothDevice.getClass().getMethod("createBond", (Class[]) null);
            m.invoke(bluetoothDevice, (Object[]) null);
            Log.d("pairDevice()", "Pairing finished.");
            listAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("pairDevice()", e.getMessage());
        }
    }

    @Override
    public void sendData(BluetoothDevice deviceList) {
        if (deviceList != null)
            hideProgressBar();
        boolean ifAlreadyPresent = false;
        for (BluetoothConnectionModel connectios : connectionModelList) {
            if (connectios.getBluetoothDevice().getAddress().equalsIgnoreCase(deviceList.getAddress())) {
                ifAlreadyPresent = true;
                break;
            }
        }
        if (!ifAlreadyPresent) {
            BluetoothConnectionModel connectionModel = new BluetoothConnectionModel();
            connectionModel.setBluetoothDevice(deviceList);
            connectionModelList.add(connectionModel);
        }
        setFirstObject();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void noDevice(Boolean aBoolean) {
        if (aBoolean == true) {
            hideProgressBar();
            //progressBar.setVisibility(View.GONE);
            tvEmptyView.setText("No Device Found");
        } else {
            tvEmptyView.setVisibility(View.GONE);
        }
    }

    private void setFirstObject() {
        for (BluetoothConnectionModel model : connectionModelList) {
            if (model.isIfPaired()) {
                model.setFirst(true);
                break;
            }
        }
        for (BluetoothConnectionModel model : connectionModelList) {
            if (!model.isIfPaired()) {
                model.setFirst(true);
                break;
            }
        }
    }

    class Holder {
        TextView tvItem, tvTitleMain;
        RadioButton radioButton;
        Button btnPair;

        public Holder(View view) {
            tvItem = (TextView) view.findViewById(R.id.tv_name);
            tvTitleMain = (TextView) view.findViewById(R.id.tv_title_main);
            radioButton = (RadioButton) view.findViewById(R.id.radio_btn);
            btnPair = (Button) view.findViewById(R.id.btn_pair);
        }
    }

    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);
                BluetoothDevice deviceData = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    connectionModelList.clear();
                    // onPermissionReceived();
                    enableBluetoothReader(context);
                    // listAdapter.notifyDataSetChanged();
                    showToast("Paired");
                    boolean paired = true;
                }

            }
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_cancel:
                startNextActivity(HomeActivity.class);
                getActivity().finish();
                break;
            case R.id.btn_ok:
                if (pairedDevices != null) {
                    if (deviceSelected) {

                        startNextActivity(HomeActivity.class);
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lay_fragment_container,new HomeFragment()).commit();
                    } else {
                        showToast("Please Select Device");
                    }
                } else {
                    showToast("Please select a reader first");
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mPairReceiver);
        getActivity().unregisterReceiver(receiver);
    }
}
