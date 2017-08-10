package com.pivot.pivot.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.pivot.pivot.R;
import com.pivot.pivot.fragments.FragmentInstrumentRead;
import com.pivot.pivot.fragments.SetReadFragment;
import com.pivot.pivot.model.TagListItem;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;

/**
 * Created by mrnee on 6/2/2017.
 */

public class ReadActivity extends BaseActivity implements CustomPagerAdapter.PagerAdapterInterface, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private CustomPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private int currentItem;
    private FloatingActionButton fab;
    private ImageView ivLeft, ivRight;
    private ArrayList<TagListItem> tagListItems;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);

        initToolBar("Scanned Tags");
        findViews();

        tagListItems = (ArrayList<TagListItem>) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEYS.KEY_SERIALIZABLE_OBJECT);
        position = getIntent().getIntExtra(AppConstants.BUNDLE_KEYS.KEY_POSITION, 0);
//        initFragmentsList();
        fragmentList.add(SetReadFragment.getInstance(null));
        fragmentList.add(FragmentInstrumentRead.getInstance());
        pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), fragmentList, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        setOnClickListener(R.id.iv_left, R.id.iv_right, R.id.fb_add);
        viewPager.setCurrentItem(position);
    }

//    private void initFragmentsList() {
//        for (TagListItem item : tagListItems) {
//            fragmentList.add(SetReadFragment.getInstance(item));
//        }
//    }

    private void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        fab = (FloatingActionButton) findViewById(R.id.fb_add);
    }

    @Override
    public Fragment getFragmentItem(int position, Object listItem) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
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
