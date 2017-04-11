package com.dhcs.admin.iiitdapp;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Admin on 4/7/2017.
 */
public class MenuTouchHelper extends ItemTouchHelper.SimpleCallback {
    private MenuTaskAdapter mMenuTaskAdapter;

    public MenuTouchHelper(MyPageAdapter adapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
//        this.mMenuTaskAdapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mMenuTaskAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mMenuTaskAdapter.remove(viewHolder.getAdapterPosition());
    }
}