package com.pivot.pivot.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.pivot.pivot.R;
import com.pivot.pivot.fragments.InstrumensReadFragment;
import com.pivot.pivot.fragments.SetReadFragment;
import com.pivot.pivot.model.IdentifyTagsInstrument;
import com.pivot.pivot.model.IdentifyTagsSets;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;


public class ReadActivity extends BaseActivity implements CustomPagerAdapter.PagerAdapterInterface, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<String> fragmentList = new ArrayList<>();
    private int currentItem;
    private ImageView ivLeft, ivRight;
    private ArrayList<IdentifyTagsSets> setsList;
    private ArrayList<String> tagList;
    private ArrayList<IdentifyTagsInstrument> instrumentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);

        initToolBar("Scanned Tags");
        findViews();
        addFragmentList();
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), fragmentList, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        setOnClickListener(R.id.iv_left, R.id.iv_right, R.id.fb_add);

    }

    private void addFragmentList() {

        if (setsList != null && instrumentList == null) {
            fragmentSize(setsList.size());
        } else if (setsList == null && instrumentList != null) {
            fragmentSize(instrumentList.size());
        } else if (setsList != null && instrumentList != null) {
            fragmentSize(setsList.size() + instrumentList.size());
        }

    }

    private void fragmentSize(int size) {
        for (int i = 0; i < size; i++) {
            fragmentList.add("");
        }
    }

    @Override
    protected void loadBundle(Bundle bundle) {
        setsList = (ArrayList<IdentifyTagsSets>) bundle.getSerializable(AppConstants.BUNDLE_KEYS.SETS);
        instrumentList = (ArrayList<IdentifyTagsInstrument>) bundle.getSerializable(AppConstants.BUNDLE_KEYS.INSTRUMENT);
        tagList = (ArrayList<String>) bundle.getSerializable(AppConstants.BUNDLE_KEYS.TAGLIST);
    }


    private void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
    }


    @Override
    public Fragment getFragmentItem(int position, Object listItem) {

        int j = 0;
        Fragment fragment[] = new Fragment[fragmentList.size()];
        for (int i = 0; i < fragmentList.size(); i++) {

            if (setsList != null && instrumentList != null) {
                if (i < setsList.size()) {
                    IdentifyTagsSets tagsSets = setsList.get(i);
                    fragment[i] = SetReadFragment.getInstance(tagsSets, tagList, i);
                } else {
                    IdentifyTagsInstrument identifyTagsInstrument = instrumentList.get(j);
                    fragment[i] = InstrumensReadFragment.getInstance(identifyTagsInstrument);
                    j++;
                }
            } else if (setsList != null && instrumentList == null) {
                IdentifyTagsSets tagsSets = setsList.get(i);
                fragment[i] = SetReadFragment.getInstance(tagsSets, tagList, i);
            }
            else if (setsList==null && instrumentList!=null){
                IdentifyTagsInstrument identifyTagsInstrument = instrumentList.get(i);
                fragment[i] = InstrumensReadFragment.getInstance(identifyTagsInstrument);
            }

        }
        return fragment[position];

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_left:
                viewPager.setCurrentItem(currentItem - 1);
                break;
            case R.id.iv_right:
                viewPager.setCurrentItem(currentItem + 1);
                break;
            case R.id.fb_add:
                showToast("Action Pending");
                break;
        }
    }

    @Override
    public CharSequence getPageTitle(int position, Object listItem) {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentItem = position;
        if (position == 0) {
            ivLeft.setVisibility(View.GONE);
        } else {
            ivLeft.setVisibility(View.VISIBLE);
        }
        if (position == fragmentList.size() - 1) {
            ivRight.setVisibility(View.GONE);
        } else {
            ivRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
