package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Admin on 4/11/2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    Context context;
    ArrayList<ScheduleItem> schedule;

    public ScheduleAdapter(Context context, ArrayList<ScheduleItem> schedule){
        this.context=context;
        this.schedule=schedule;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item,null);
        ScheduleViewHolder holder= new ScheduleViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
