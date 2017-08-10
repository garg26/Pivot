package com.pivot.pivot.fragments;

import com.pivot.pivot.R;
import com.pivot.pivot.activity.ReadActivity;

import simplifii.framework.fragments.BaseFragment;

/**
 * Created by mrnee on 6/2/2017.
 */

public class FragmentInstrumentRead extends BaseFragment {

    public static FragmentInstrumentRead getInstance(){
        FragmentInstrumentRead fragmentInstrumentRead = new FragmentInstrumentRead();
        return fragmentInstrumentRead;
    }

    @Override
    public void initViews() {

    }

    @Override
    public int getViewID() {
        return R.layout.fragment_instrument_read;
    }
}
