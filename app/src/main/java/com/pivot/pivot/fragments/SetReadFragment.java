package com.pivot.pivot.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pivot.pivot.R;
import com.pivot.pivot.activity.ReadActivity;
import com.pivot.pivot.model.SetReadModel;
import com.pivot.pivot.model.TagListItem;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.fragments.BaseFragment;

/**
 * Created by mrnee on 6/2/2017.
 */

public class SetReadFragment extends BaseFragment implements CustomListAdapterInterface {
    private ListView listSetRead;
    private CustomListAdapter listAdapter;
    private List<SetReadModel> readModelList = new ArrayList<>();
    private int currentPosition;
    private TagListItem item;

    public static SetReadFragment getInstance(TagListItem item) {
        SetReadFragment setReadFragment = new SetReadFragment();
        setReadFragment.item = item;
        return setReadFragment;
    }

    @Override
    public void initViews() {
        findViews();
        listAdapter = new CustomListAdapter(getActivity(), R.layout.row_set_read, readModelList, this);
        listSetRead.setAdapter(listAdapter);
        setDataToList();
    }

    private void findViews() {
        listSetRead = (ListView) findView(R.id.list_set_read);
//        setText("Tag # :" + item.getTag(true), R.id.tv_set_id);
    }

    private void setDataToList() {
        for (int i = 0; i < 10; i++) {
            SetReadModel setReadModel = new SetReadModel();
            setReadModel.setTitleName("Read" + i);
            setReadModel.setSubTitle("instrument name" + i);
            readModelList.add(setReadModel);
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
        holder.tvTitle.setText(setReadModel.getTitleName());
        holder.tvSunTitle.setText(setReadModel.getSubTitle());

        return convertView;
    }

    class Holder {
        TextView tvTitle, tvSunTitle;

        public Holder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvSunTitle = (TextView) view.findViewById(R.id.tv_sub_title);
        }
    }
}
