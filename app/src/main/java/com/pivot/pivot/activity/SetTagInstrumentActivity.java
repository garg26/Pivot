package com.pivot.pivot.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pivot.pivot.R;
import com.pivot.pivot.fragments.InstrumentFragment;
import com.pivot.pivot.model.IdentifyTagsSets;
import com.pivot.pivot.model.InstrumentTagData;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.NonSwipeableViewPager;

public class SetTagInstrumentActivity extends BaseActivity implements CustomPagerAdapter.PagerAdapterInterface {

    private ArrayList<IdentifyTagsSets> setsList;
    private NonSwipeableViewPager nonSwipeableViewPager;
    private List<String> mFragmentList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tag_instrument);

        nonSwipeableViewPager = (NonSwipeableViewPager) findViewById(R.id.nonSwipeableViewPager);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setsList = (ArrayList<IdentifyTagsSets>) bundle.getSerializable(AppConstants.BUNDLE_KEYS.SETSLIST);
        }

        addSetsInstrumnetFragment(setsList.size());
    }

    private void addSetsInstrumnetFragment(final int size) {
        nonSwipeableViewPager.setNonSwipe(true);
        addListToPager(size);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), mFragmentList, this);
        nonSwipeableViewPager.setAdapter(pagerAdapter);
    }


    private void addListToPager(int size) {
        for (int i = 0; i < size; i++) {
            mFragmentList.add("");
        }
    }


    @Override
    public Fragment getFragmentItem(int position, Object listItem) {
        String instrumentID = null;
        List<InstrumentTagData> instruments = setsList.get(position).getInstruments();
        if (instruments != null) {
            instrumentID = instruments.get(position).getId();
        }

        switch (instrumentID) {
            case "INST35201700011":
                InstrumentFragment instrumentFragment = InstrumentFragment.getInstance(instruments);
                return instrumentFragment;


        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position, Object listItem) {
        return null;
    }
}
