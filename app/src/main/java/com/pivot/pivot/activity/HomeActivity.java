package com.pivot.pivot.activity;

import android.bluetooth.BluetoothDevice;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pivot.pivot.R;
import com.pivot.pivot.fragments.BluetoothConnectionFragment;
import com.pivot.pivot.fragments.DrawerFragment;
import com.pivot.pivot.fragments.HomeFragment;

import java.util.ArrayList;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;

public class HomeActivity extends BaseActivity implements DrawerLayout.DrawerListener, DrawerFragment.ToolbarTitleListner {

    private FrameLayout container;
    public TextView tvTitle;
    private DrawerLayout drawerLayout;
    private boolean isDrawerOpen;
    private FragmentManager fragmentManager;
    private boolean openBluetoothFrag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolBar("Pivot");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            BluetoothDevice bluetoothDevice = bundle.getParcelable(AppConstants.BUNDLE_KEYS.BLUETOOTH_LIST);
            if (bluetoothDevice != null){
                openBluetoothFrag = true;
            }
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_home);
        drawerLayout.addDrawerListener(this);
        fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = HomeFragment.getInstance();
        BluetoothConnectionFragment connectionFragment = BluetoothConnectionFragment.getInstance();
        addDrawerFragment(connectionFragment);
        addFragment(connectionFragment, false);
    }

    public void addFragment(Fragment fragment, boolean b) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        if (b) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.lay_fragment_container, fragment).commit();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onHomePressed() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void addDrawerFragment(BluetoothConnectionFragment homeFragment) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        String[] val = getResources().getStringArray(R.array.drawer_list_item);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : val) {
            arrayList.add(s);
        }

        DrawerFragment drawerFragment = DrawerFragment.getInstance(arrayList, this, drawerLayout, this, openBluetoothFrag);
        fragmentManager.beginTransaction().replace(R.id.lay_drawer, drawerFragment).commit();

    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen) {
            drawerLayout.closeDrawer(Gravity.LEFT);
            isDrawerOpen = false;
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        initToolBar("Pivot");
        super.onBackPressed();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        isDrawerOpen = true;
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isDrawerOpen = false;
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void sendTitle(String s) {
        initToolBar(s);
    }
}
