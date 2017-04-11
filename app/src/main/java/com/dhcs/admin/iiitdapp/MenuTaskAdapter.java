package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
public class MenuTaskAdapter extends RecyclerView.Adapter<MenuTaskAdapter.TodoTaskViewHolder> {

    private List<MenuTask> menuTaskList;
    private Context context;
    public MenuTaskAdapter(Context context, List<MenuTask> menuTaskList) {
        this.menuTaskList = menuTaskList;
        this.context = context;
    }


    @Override
    public TodoTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoTaskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_menu_list_box, parent,false);
        return new TodoTaskViewHolder(todoTaskView);
    }

    @Override
    public void onBindViewHolder(TodoTaskViewHolder holder, int position) {
        MenuTask menuTask = menuTaskList.get(position);
        holder.titleListTextView.setText(menuTask.getTitle());
    }

    public void remove(int position) {
        menuTaskList.remove(position);
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition){
        Collections.swap(menuTaskList, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    @Override
    public int getItemCount() {
        return menuTaskList.size();
    }

    public class TodoTaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleListTextView;

        //  public ImageButton upButton;
        public TodoTaskViewHolder(View todoTaskView) {
            super(todoTaskView);
            // final MenuTask todoTask = menuTaskList.get(this.getAdapterPosition());
            titleListTextView = (TextView) todoTaskView.findViewById(R.id.todo_title);

            todoTaskView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,MenuViewPager.class);
            intent.putExtra("check",getAdapterPosition());
            context.startActivity(intent);
        }

    }
}