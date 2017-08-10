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

public class SetReadFragment extends BaseFragment implements CustomListAdapterInterface {
    private ListView listSetRead;
    private CustomListAdapter listAdapter;
    private List<SetReadModel> readModelList = new ArrayList<>();
    private int currentPosition;
    private TagListItem item;
    private IdentifyTagsSets setsItem;
    private TextView tv_set_id;
    private ArrayList<String> tagList;
    private TextView tv_set_name;
    private ImageView iv_refresh_set;
    private int position;

    public static SetReadFragment getInstance(IdentifyTagsSets item, ArrayList<String> tagList, int i) {
        SetReadFragment setReadFragment = new SetReadFragment();
        setReadFragment.setsItem = item;
        setReadFragment.tagList = tagList;
        setReadFragment.position = i;
        return setReadFragment;
    }

    @Override
    public void initViews() {
        findViews();
        listAdapter = new CustomListAdapter(getActivity(), R.layout.row_set_read, readModelList, this);
        listSetRead.setAdapter(listAdapter);
        setDataToList();

        setOnClickListener(R.id.iv_refresh_set);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_refresh_set:
                refreshSets();
                break;
        }
    }

    private void refreshSets() {
        String setsId = setsItem.getId();
        if (!TextUtils.isEmpty(setsId)) {
            String taglistString = TextUtils.join(",", tagList);
            HttpParamObject httpParamObject = ApiGenerator.refreshSets(setsId, taglistString);
            executeTask(AppConstants.TASK_CODES.REFRESH_SETS, httpParamObject);
        }
    }

    @Override
    public void onPreExecute(int taskCode) {
        Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        iv_refresh_set.startAnimation(rotation);
    }


    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        iv_refresh_set.setAnimation(null);
        if (response == null)
            return;

        switch (taskCode) {
            case AppConstants.TASK_CODES.REFRESH_SETS:
                SetsResponse setsResponse = (SetsResponse) response;
                if (setsResponse != null) {
                    setDataToList(setsResponse);
                }
                break;

        }
    }


    private void findViews() {
        listSetRead = (ListView) findView(R.id.list_set_read);
        tv_set_id = (TextView) findView(R.id.tv_set_id);
        tv_set_name = (TextView) findView(R.id.tv_set_name);
        iv_refresh_set = (ImageView) findView(R.id.iv_refresh_set);
//        setText("Tag # :" + item.getTag(true), R.id.tv_set_id);
    }

    private void setDataToList() {

        readModelList.clear();
        String name = setsItem.getName();
        String id = setsItem.getId();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id)) {
            tv_set_id.setText(id);
            tv_set_name.setText(name);
        }

        List<InstrumentTagData> instruments = setsItem.getInstruments();
        if (instruments != null || instruments.size() > 0) {
            for (InstrumentTagData instrumentTagData : instruments) {
                SetReadModel setReadModel = new SetReadModel();
                String instrumentID = instrumentTagData.getId();
                String instrumentName = instrumentTagData.getName();
                String rfidCode = instrumentTagData.getRfidCode();
                setReadModel.setTitleName(instrumentID);
                setReadModel.setSubTitle(instrumentName);
                setReadModel.setRfidReaderID(rfidCode);
                readModelList.add(setReadModel);
            }
        }

        listAdapter.notifyDataSetChanged();
    }

    private void setDataToList(SetsResponse setsResponse) {

        readModelList.clear();
        String name = setsResponse.getName();
        String id = setsResponse.getId();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id)) {
            tv_set_id.setText(id);
            tv_set_name.setText(name);
        }

        List<InstrumentTagData> instruments = setsResponse.getInstruments();
        if (instruments != null || instruments.size() > 0) {
            for (InstrumentTagData instrumentTagData : instruments) {
                SetReadModel setReadModel = new SetReadModel();
                String instrumentID = instrumentTagData.getId();
                String instrumentName = instrumentTagData.getName();
                String rfidCode = instrumentTagData.getRfidCode();
                setReadModel.setTitleName(instrumentID);
                setReadModel.setSubTitle(instrumentName);
                setReadModel.setRfidReaderID(rfidCode);
                readModelList.add(setReadModel);
            }
        }

        listAdapter.notifyDataSetChanged();
    }


    @Override
    public int getViewID() {
        return R.layout.fragment_set_read;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(resourceID, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        SetReadModel setReadModel = readModelList.get(position);
        String titleName = setReadModel.getTitleName();
        String rfidReaderID = setReadModel.getRfidReaderID();
        if (tagList.contains(rfidReaderID)) {
            holder.iv_exclamation.setBackgroundResource(R.mipmap.warning);
            holder.iv_right_angle.setBackgroundResource(R.mipmap.right_black);

        } else {
            holder.iv_exclamation.setBackgroundResource(R.mipmap.warning_red);
            holder.iv_right_angle.setBackgroundResource(R.mipmap.multiply);
            holder.tvTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.dark_red));
        }
        holder.tvTitle.setText(setReadModel.getTitleName());
        holder.tvSunTitle.setText(setReadModel.getSubTitle());

        return convertView;
    }

    class Holder {
        TextView tvTitle, tvSunTitle;
        ImageView iv_exclamation, iv_right_angle;

        public Holder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvSunTitle = (TextView) view.findViewById(R.id.tv_sub_title);
            iv_exclamation = (ImageView) view.findViewById(R.id.iv_exclamation);
            iv_right_angle = (ImageView) view.findViewById(R.id.iv_right_angle);
        }
    }
}
