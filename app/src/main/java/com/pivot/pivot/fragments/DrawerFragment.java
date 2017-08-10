package com.pivot.pivot.fragments;

import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pivot.pivot.R;
import com.pivot.pivot.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.fragments.BaseFragment;

/**
 * Created by mrnee on 6/2/2017.
 */

public class DrawerFragment extends BaseFragment implements AdapterView.OnItemClickListener, CustomListAdapterInterface {

    private ListView listDrawer;
    private static DrawerLayout drawerLayout;
    HomeActivity drawerActivity;
    List<String> values = new ArrayList<>();
    ArrayList<String> listItem = new ArrayList<>();
    private CustomListAdapter listAdapter;
    ToolbarTitleListner toolbarTitleListner;
    private boolean openBluetoothFrag;
    int itemPosition;

    public static DrawerFragment getInstance(List<String> arrayList, HomeActivity drawerActivity, DrawerLayout drawerLayout, ToolbarTitleListner toolbarTitleListner, boolean openBluetoothFrag) {
        DrawerFragment drawerFragment = new DrawerFragment();
        drawerFragment.values = arrayList;
        drawerFragment.drawerActivity = drawerActivity;
        drawerFragment.drawerLayout = drawerLayout;
        drawerFragment.toolbarTitleListner = toolbarTitleListner;
        drawerFragment.openBluetoothFrag = openBluetoothFrag;
        return drawerFragment;
    }

    @Override
    public void initViews() {
        listDrawer = (ListView) findView(R.id.listview_drawer);
        refreshDrawerData();
    }

    private void refreshDrawerData() {
        listAdapter = new CustomListAdapter(getActivity(), R.layout.row_drawer, listItem, this);
        listDrawer.setAdapter(listAdapter);
        putDataIntoList();
        listAdapter.notifyDataSetChanged();
        listDrawer.setOnItemClickListener(this);
//        if (openBluetoothFrag == true){
//            BluetoothConnectionFragment stayLineFragment = BluetoothConnectionFragment.getInstance();
//            drawerActivity.addFragment(stayLineFragment,true);
//            return;
//        } else {
//            listDrawer.setOnItemClickListener(this);
//        }
    }

    private void putDataIntoList() {
        String[] temp = getResources().getStringArray(R.array.drawer_list_item);
        int length = temp.length;
        listItem.clear();
        for (int i = 0; i < length; i++) {
            listItem.add(temp[i]);
        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_drawer;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listAdapter.notifyDataSetChanged();
        itemPosition = position;
        switch (itemPosition){
            case 0:
                HomeFragment homeFragment = HomeFragment.getInstance();
                drawerActivity.addFragment(homeFragment,true);
                toolbarTitleListner.sendTitle(listItem.get(itemPosition));
                break;
            case 1:
                BluetoothConnectionFragment connectionFragment = BluetoothConnectionFragment.getInstance();
                drawerActivity.addFragment(connectionFragment,true);
                toolbarTitleListner.sendTitle(listItem.get(itemPosition));
                break;
            case 2:

                break;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_drawer, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvItem.setText(listItem.get(position));

        return convertView;
    }

    class Holder {
        TextView tvItem;
        public Holder(View view) {
            tvItem = (TextView) view.findViewById(R.id.tv_drawer_item);
        }
    }

    public interface ToolbarTitleListner {
        public void sendTitle(String s);
    }
}
