package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */

public class MyPageAdapter extends PagerAdapter {


    private List<MenuTask> menuTaskList;
    private Context context;


    public MyPageAdapter(Context context, List<MenuTask> menuTaskList){
        this.context = context;
        this.menuTaskList = menuTaskList;
    }

    @Override
    public int getCount() {
        return menuTaskList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MenuTask menuTask = menuTaskList.get(position);
        View layout = LayoutInflater.from(context).inflate(R.layout.activity_menu_task_view,container,false);
        TextView titleView = (TextView) layout.findViewById(R.id.tv_title);
        TextView detailsView = (TextView) layout.findViewById(R.id.tv_details);
        titleView.setText(menuTask.getTitle());
        detailsView.setText(menuTask.getDetail());
        container.addView(layout);
        return menuTask;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menuTaskList.get(position).getTitle();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        TextView titleView = (TextView) view.findViewById(R.id.tv_title);
        MenuTask menuTask = (MenuTask) object;
        return titleView.getText() == menuTask.getTitle();
    }
}
