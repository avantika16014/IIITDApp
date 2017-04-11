package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 4/8/2017.
 */

public class ListViewAdapter_Directory extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Directory> directorylist = null;
    private ArrayList<Directory> arraylist;

    public ListViewAdapter_Directory(Context context, List<Directory> directorylist) {
        mContext = context;
        this.directorylist = directorylist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Directory>();
        this.arraylist.addAll(directorylist);
    }

    public class ViewHolder {
        TextView rank;
        TextView name;
        TextView roomno;
        TextView extension;
        TextView email;
    }

    @Override
    public int getCount() {
        return directorylist.size();
    }

    @Override
    public Directory getItem(int position) {
        return directorylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            //holder.rank = (TextView) view.findViewById(R.id.rank);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.roomno = (TextView) view.findViewById(R.id.roomno);
            holder.extension = (TextView) view.findViewById(R.id.extension);
            holder.email = (TextView) view.findViewById(R.id.email);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        //holder.rank.setText(directorylist.get(position).getRank());
        holder.name.setText(directorylist.get(position).getname());
        holder.roomno.setText(directorylist.get(position).getroomno());
        holder.extension.setText(directorylist.get(position).getextension());
        holder.email.setText(directorylist.get(position).getemail());

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemViewDirectory.class);
                // Pass all data rank
                //intent.putExtra("rank",(directorylist.get(position).getRank()));
                // Pass all data country
                intent.putExtra("name",(directorylist.get(position).getname()));
                // Pass all data population
                intent.putExtra("roomno",(directorylist.get(position).getroomno()));
                intent.putExtra("extension",(directorylist.get(position).getextension()));
                intent.putExtra("email",(directorylist.get(position).getemail()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        directorylist.clear();
        if (charText.length() == 0) {
            directorylist.addAll(arraylist);
        }
        else
        {
            for (Directory wp : arraylist)
            {
                if (wp.getname().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    directorylist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
