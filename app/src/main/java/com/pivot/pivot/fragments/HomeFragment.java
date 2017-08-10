package com.pivot.pivot.fragments;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.gun0912.tedpermission.PermissionListener;
import com.pivot.pivot.R;
import com.pivot.pivot.activity.ReadersListActivity;
import com.pivot.pivot.bluetooth.BluetoothManager;
import com.pivot.pivot.bluetooth.BluetoothReceiver;

import java.util.ArrayList;

import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;

/**
 * Created by mrnee on 6/2/2017.
 */

public class HomeFragment extends BaseFragment implements BluetoothReceiver.GetBluetoothListListner {
    private Button btnStart;

    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    private void enableBluetoothReader() {
        BluetoothManager.askPermissions(getActivity(), new PermissionListener() {
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
        BluetoothManager manager = BluetoothManager.getInstance(getActivity());
        manager.onBluetooth();
        manager.getPairedDevices();
//        startNextActivity(null, ReadersListActivity.class);
        manager.startSearching(getActivity(), new BluetoothReceiver(this));
    }

    @Override
    public void initViews() {
        btnStart = (Button) findView(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bluetooth_device = Preferences.getData(AppConstants.PREF_KEYS.BLUETOOTH_DEVICE, null);
                Intent intent = new Intent(getActivity(),ReadersListActivity.class);
                intent.putExtra("device_device",bluetooth_device);
                startActivity(intent);


//                enableBluetoothReader();
//                FragmentManager fm = getChildFragmentManager();
//                ReadActivity fragment = new ReadActivity();
//                fm.beginTransaction().add(R.id.frame_container,fragment).commit();
            }
        });
    }


    @Override
    public int getViewID() {
        return R.layout.fragment_home;
    }

    @Override
    public void sendData(BluetoothDevice deviceList) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(AppConstants.BUNDLE_KEYS.BLUETOOTH_LIST, (Parcelable) deviceList);
//        startNextActivity(bundle, HomeActivity.class);
    }

    @Override
    public void noDevice(Boolean aBoolean) {

    }
}
