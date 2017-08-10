package com.pivot.pivot.fragments;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pivot.pivot.ApiGenerator;
import com.pivot.pivot.R;
import com.pivot.pivot.model.IdentifyTagsInstrument;
import com.pivot.pivot.model.IdentifyTagsResponse;
import com.pivot.pivot.model.IdentifyTagsSets;
import com.pivot.pivot.model.InstrumentTagData;
import com.pivot.pivot.model.SetReadModel;
import com.pivot.pivot.model.SetsResponse;
import com.pivot.pivot.model.TagListItem;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionUtils;

/**
 * Created by mrnee on 6/2/2017.
 */

public class InstrumensReadFragment extends BaseFragment {


    private IdentifyTagsInstrument tagsInstrument;
    private TextView tv_set_id;
    private TextView tv_set_name;

    public static InstrumensReadFragment getInstance(IdentifyTagsInstrument item) {
        InstrumensReadFragment instrumentReadFragment = new InstrumensReadFragment();
        instrumentReadFragment.tagsInstrument = item;
        return instrumentReadFragment;
    }

    @Override
    public void initViews() {
        findViews();
        setDataToList();
    }


    private void findViews() {
        tv_set_id = (TextView) findView(R.id.tv_set_id);
        tv_set_name = (TextView) findView(R.id.tv_set_name);

    }

    private void setDataToList() {
        String name = tagsInstrument.getName();
        String id = tagsInstrument.getId();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id)) {
            tv_set_id.setText(id);
            tv_set_name.setText(name);
        }
    }


    @Override
    public int getViewID() {
        return R.layout.fragment_instrument_read;
    }


}
