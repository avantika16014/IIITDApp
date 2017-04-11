package com.dhcs.admin.iiitdapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */

public class MenuViewPager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view_pager);

        List<MenuTask> menuTaskList = FoodhubActivity.menuTaskList;
        ViewPager todoViewPager = (ViewPager) findViewById(R.id.tv_pager);

        todoViewPager.setAdapter(new MyPageAdapter(this, menuTaskList));
        todoViewPager.setCurrentItem(getIntent().getIntExtra("check",0));
    }
}
