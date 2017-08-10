package com.pivot.pivot.fragments;

import android.text.TextUtils;
import android.widget.TextView;

import com.pivot.pivot.R;
import com.pivot.pivot.model.InstrumentTagData;

import java.util.List;

import simplifii.framework.fragments.BaseFragment;

/**
 * Created by kartikeya on 1/8/17.
 */

public class InstrumentFragment extends BaseFragment{

    private List<InstrumentTagData> instruments;
    public static InstrumentFragment getInstance(List<InstrumentTagData> instruments){
        InstrumentFragment instrumentFragment = new InstrumentFragment();
        instrumentFragment.instruments = instruments;

        return instrumentFragment;
    }

    @Override
    public void initViews() {
        TextView textView = (TextView) findView(R.id.btn_text);

        if (instruments!=null){
            String name = instruments.get(0).getName();
            if (!TextUtils.isEmpty(name)){
                textView.setText(name);
            }
        }

    }

    @Override
    public int getViewID() {
        return R.layout.fragment_instrument;
    }
}
