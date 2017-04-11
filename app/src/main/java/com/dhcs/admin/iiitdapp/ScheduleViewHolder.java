package com.dhcs.admin.iiitdapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Admin on 4/11/2017.
 */

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    TextView classTime, classRoom, classType;
    CheckBox class_selected;
    public ScheduleViewHolder(View itemView) {
        super(itemView);
        classTime=(TextView)itemView.findViewById(R.id.li_courseTime);
        classType=(TextView)itemView.findViewById(R.id.li_courseType);
        classRoom=(TextView)itemView.findViewById(R.id.li_courseRoom);
        class_selected=(CheckBox) itemView.findViewById(R.id.li_checkBox);
    }
}
