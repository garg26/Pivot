package com.pivot.pivot.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pivot.pivot.R;
import com.pivot.pivot.model.IdentifyTagsSets;
import com.pivot.pivot.model.TagListItem;

public class TagListAdapter extends BaseAdapter {

    private static final int UPDATE_TIME = 300;

    private LayoutInflater mInflater;


    private ArrayList<IdentifyTagsSets> mList;
    private HashMap<String, TagListItem> mMap;
    private boolean mIsDisplayPc;
    private boolean mIsReportRSSI;
    private boolean mIsShowCount;
    private boolean mIsReportTID;
    private Context mContext;
    private Handler mHandler;
    private UpdateThread mUpdateThread;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    public TagListAdapter(Context context) {
        super();

        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<IdentifyTagsSets>();
        mMap = new HashMap<String, TagListItem>();
        mIsDisplayPc = true;
        mIsShowCount = true;
        mIsReportRSSI = false;
        mIsReportTID = false;
        mUpdateThread = null;
        mHandler = new Handler();
    }

    public void clear() {
        mList.clear();
        mMap.clear();
        notifyDataSetChanged();
    }

    public ArrayList<IdentifyTagsSets> getTagsList() {
        return mList;
    }

    public boolean getDisplayPc() {
        return mIsDisplayPc;
    }

    public void setDisplayPc(boolean enabled) {
        mIsDisplayPc = enabled;
        notifyDataSetChanged();
    }

    public boolean getShowCount() {
        return mIsShowCount;
    }

    public void setShowCount(boolean enabled) {
        mIsShowCount = enabled;
        notifyDataSetChanged();
    }

    public boolean getReportRSSI() {
        return mIsReportRSSI;
    }

    public void setReportRSSI(boolean enabled) {
        if (mIsReportRSSI == enabled)
            return;
        mIsReportRSSI = enabled;
        clear();
    }

    public boolean getReportTID() {
        return mIsReportTID;
    }

    public void setReportTID(boolean enabled) {
        if (mIsReportTID == enabled)
            return;
        mIsReportTID = enabled;
        clear();
    }

    public void start() {
        mUpdateThread = new UpdateThread();
        mUpdateThread.start();
    }

    public void shutDown() {
        if (mUpdateThread != null) {
            mUpdateThread.cancel();
            try {
                mUpdateThread.join();
            } catch (InterruptedException e) {
            }
            mUpdateThread = null;
        }
    }

//    public void addTag(String tag, float rssi, float phase) {
//        TagListItem item = null;
//
//        if (null == (item = mMap.get(tag))) {
//            item = new TagListItem(tag, rssi, phase);
//            mList.add(item);
//            mMap.put(tag, item);
//        } else {
//            item.updateItem(rssi, phase);
//        }
//    }

    public void setmList(List<IdentifyTagsSets> sets){
        if (sets!=null){
            for (IdentifyTagsSets tagsSets: sets){
                mList.add(tagsSets);
            }
        }
    }


//    public void addTag(String tag, String tid, float rssi, float phase) {
//        TagListItem item = null;
//
//        if (null == (item = mMap.get(tag))) {
//            item = new TagListItem(tag, tid, rssi, phase);
//            mList.add(item);
//            mMap.put(tag, item);
//        } else {
//            if (item.getTid().equals(tid))
//                item.updateItem(rssi, phase);
//            else {
//                item = new TagListItem(tag, tid, rssi, phase);
//                mList.add(item);
//                mMap.put(tag, item);
//            }
//        }
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position).getName();
    }

    public String getItem(int position, boolean displayPc) {
        return mList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TagListViewHolder holder = null;

        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_tag_list, parent,
                    false);
            holder = new TagListViewHolder(convertView);
        } else {
            holder = (TagListViewHolder) convertView.getTag();
        }
        holder.setItem(mList.get(position).getName(), mIsDisplayPc);
        return convertView;
    }

    // ------------------------------------------------------------------------
    // Internal TagListItem class
    // ------------------------------------------------------------------------


    // ------------------------------------------------------------------------
    // Internal TagListViewHodler class
    // ------------------------------------------------------------------------
    private class TagListViewHolder {
        private TextView txtTag;
        private TextView txtTid;
        private TextView txtRssi;
        private TextView txtPhase;
        private TextView txtCount;
        private LinearLayout layoutSubItems;

        public TagListViewHolder(View parent) {
            txtTag = (TextView) parent.findViewById(R.id.tag_value);
            txtTid = (TextView) parent.findViewById(R.id.tid_value);
            txtTid.setVisibility(mIsReportTID ? View.VISIBLE : View.GONE);
            txtRssi = (TextView) parent.findViewById(R.id.tag_rssi);
            txtPhase = (TextView) parent.findViewById(R.id.tag_phase);
            txtCount = (TextView) parent.findViewById(R.id.tag_count);
            txtCount.setVisibility(mIsShowCount ? View.VISIBLE : View.GONE);
            layoutSubItems = (LinearLayout) parent.findViewById(R.id.sub_items);
            layoutSubItems.setVisibility(mIsReportRSSI ? View.VISIBLE : View.GONE);
            parent.setTag(this);
        }

        public void setItem(String item, boolean displayPc) {
            txtTag.setText(item);
        //    txtTag.setText(item.getTag(displayPc));
//            txtTid.setText(item.getTid());
//            txtRssi.setText(String.format(Locale.US, "%.1f dB", item.getRSSI()));
//            txtPhase.setText(String.format(Locale.US, "%.1f˚", item.getPhase()));
//            txtCount.setText("" + item.getCount());
//            layoutSubItems.setVisibility(mIsReportRSSI ? View.VISIBLE : View.GONE);
        }
    }

    // ------------------------------------------------------------------------
    // Internal UpdateThread class
    // ------------------------------------------------------------------------
    private class UpdateThread extends Thread {

        private boolean mIsAlived;

        private UpdateThread() {
            mIsAlived = false;
        }

        @Override
        public void run() {
            mIsAlived = true;

            while (mIsAlived) {
                synchronized (TagListAdapter.this) {
                    try {
                        TagListAdapter.this.wait(UPDATE_TIME);
                    } catch (InterruptedException e) {
                    }
                }

                if (mIsAlived) {
                    mHandler.post(procUpdate);
                }
            }
        }

        private Runnable procUpdate = new Runnable() {

            @Override
            public void run() {
                TagListAdapter.this.notifyDataSetChanged();
            }

        };

        public void cancel() {
            synchronized (TagListAdapter.this) {
                mIsAlived = false;
                TagListAdapter.this.notify();
            }
        }
    }
}
