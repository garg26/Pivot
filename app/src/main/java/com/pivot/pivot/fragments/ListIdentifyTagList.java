package com.pivot.pivot.fragments;

import android.widget.ListView;

import com.pivot.pivot.R;
import com.pivot.pivot.model.IdentifyTagsSets;

import java.util.List;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.fragments.BaseFragment;

/**
 * Created by kartikeya on 31/7/17.
 */

public class ListIdentifyTagList extends BaseFragment{

    private ListView list_service;
    private List<IdentifyTagsSets> sets;
    private List<String> tags;

    public static ListIdentifyTagList getInstance(List<IdentifyTagsSets> sets,List<String> tags){
        ListIdentifyTagList identifyTagList = new ListIdentifyTagList();
        identifyTagList.sets = sets;
        identifyTagList.tags = tags;
        return identifyTagList;
    }

    @Override
    public void initViews() {
        list_service = (ListView) findView(R.id.list_service);

    }

    @Override
    public int getViewID() {
        return R.layout.fragment_identify_tags_list;
    }
}
