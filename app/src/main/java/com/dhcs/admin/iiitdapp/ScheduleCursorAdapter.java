package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

/**
 * Created by Admin on 4/7/2017.
 */

public class ScheduleCursorAdapter extends CursorAdapter {

    private String courseName;
    public ScheduleCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.schedule_list_item, parent, false);
    }

    private String format(int time){
        String finalTime=Integer.toString(time);
        if(time<1000)
            finalTime="0"+finalTime;
        return finalTime;
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
/*
        TextView courseTime = (TextView) view.findViewById(R.id.li_courseTime);
        TextView courseType = (TextView) view.findViewById(R.id.li_courseType);
        TextView courseRoom = (TextView) view.findViewById(R.id.li_courseRoom);
        CheckBox courseCheck = (CheckBox) view.findViewById(R.id.li_checkBox);

        // Populate fields with extracted properties
        //int id=cursor.getColumnIndexOrThrow("_id");
        //System.out.println("***********************************************************"+id);
        courseRoom.setText(cursor.getString(cursor.getColumnIndexOrThrow("RoomNumber")));
        courseTime.setText(cursor.getString(cursor.getColumnIndexOrThrow("ClassDay"))+" "+format(cursor.getInt(2))+" hours");
        courseType.setText(cursor.getString(cursor.getColumnIndexOrThrow("ClassType")));
        courseCheck.setChecked(false);
        */
        /*******************************trying checkedtextview ******************************/
        String result="";
        CheckedTextView timeDetails=(CheckedTextView)view.findViewById(R.id.checked_tv_schedule_item);
        result+=cursor.getString(cursor.getColumnIndexOrThrow("ClassDay"))+" "
                +format(cursor.getInt(2))+"Hours \n\n"
                +cursor.getString(cursor.getColumnIndexOrThrow("RoomNumber"))+" "
                +cursor.getString(cursor.getColumnIndexOrThrow("ClassType"));
        timeDetails.setText(result);

    }
}
