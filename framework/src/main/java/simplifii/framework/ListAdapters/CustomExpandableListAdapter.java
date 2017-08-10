package simplifii.framework.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 3/31/17.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private int layoutIdParent;
    private int layoutIdChild;
    private int parentListSize;
    private ExpandableListener expandableListener;
    private LayoutInflater inflater;
    private List list;
    private Map map;

    public CustomExpandableListAdapter(Context context, int layoutIdParent, int layoutIdChild, List list, ExpandableListener expandableListener) {
        this.layoutIdParent = layoutIdParent;
        this.layoutIdChild = layoutIdChild;
        this.list=list;
        this.expandableListener = expandableListener;
        inflater=LayoutInflater.from(context);
    }
    public CustomExpandableListAdapter(Context context, int layoutIdParent, int layoutIdChild, Map map, ExpandableListener expandableListener) {
        this.layoutIdParent = layoutIdParent;
        this.layoutIdChild = layoutIdChild;
        this.map=map;
        this.expandableListener = expandableListener;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        if(list!=null){
            return list.size();
        }
        if(map!=null){
            return map.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableListener.getChildSize(groupPosition);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return expandableListener.getGroupView(groupPosition,isExpanded,convertView,parent,layoutIdParent,inflater);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return expandableListener.getChildView(groupPosition,childPosition,isLastChild,convertView,parent,layoutIdChild,inflater);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    public interface ExpandableListener{
        int getChildSize(int parentPosition);
        View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, int resourceId, LayoutInflater inflater);
        View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, int resourceId, LayoutInflater inflater);
    }
}
