package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */

public class ExpandableCourseListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeader;
    private static final String TAG = "expandable";
    private HashMap<String, List<String>> listHashMap;
    public ExpandableCourseListAdapter(Context context, List<String> listHeader,HashMap<String,List<String>> listHashMap){
        this.context=context;
        this.listHashMap=listHashMap;
        System.out.println(this.listHashMap);
        this.listHeader=listHeader;
    }
    @Override
    public int getGroupCount() {
        Log.v(TAG, "in groupCount "+listHeader.size());
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Log.v(TAG, "in getChildrenCount "+listHashMap.get(listHeader.get(i)).size());
        return listHashMap.get(listHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        Log.v(TAG, "in getGroup "+listHeader.get(i));
        return listHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        //System.out.println(listHashMap.get(listHeader.get(i)));
        //Log.v(TAG, "in getChild "+listHashMap.get(listHeader.get(i+1)).get(i1));
        return listHashMap.get(listHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        Log.v(TAG, "in getGroupID "+i);
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {

        Log.v(TAG, "in getChildID "+i1);
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle=(String)getGroup(i);
        System.out.println("Header title "+headerTitle);
        if(view==null){
            LayoutInflater inflater= (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
            view=inflater.inflate(R.layout.class_group,null);
        }
        TextView headerText=(TextView)view.findViewById(R.id.elg_courseName);
        Toast.makeText(context, "view", Toast.LENGTH_SHORT).show();
        headerText.setTypeface(null, Typeface.BOLD);
        headerText.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        System.out.println("i "+i+" i1 "+i1);
        String childText=(String)getChild(i,i1);
        if(view==null){
            LayoutInflater inflater= (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.class_item,null);
            Toast.makeText(context, "null child", Toast.LENGTH_SHORT).show();
        }
        TextView childString=(TextView)view.findViewById(R.id.eli_courseType);
        childString.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
