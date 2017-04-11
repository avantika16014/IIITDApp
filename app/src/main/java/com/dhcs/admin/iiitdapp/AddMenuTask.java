package com.dhcs.admin.iiitdapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Admin on 4/7/2017.
 */

public class AddMenuTask extends AppCompatActivity {

    private EditText titleText;
    private EditText detailText;
    private int todoTaskId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        titleText = (EditText) findViewById(R.id.input_title);
        detailText = (EditText) findViewById(R.id.input_detail);

    }

    public void addItem(View view){
        if(titleText.getText().toString().equals("")){
            titleText.setError("Enter Title");
            return;
        }
        if(detailText.getText().toString().equals("")){
            detailText.setError("Enter Details");
            return;
        }
        todoTaskId++;
        MenuTask menuTask = new MenuTask(titleText.getText().toString(), detailText.getText().toString(),todoTaskId);
        FoodhubActivity.menuTaskList.add(menuTask);
        finish();
    }
}
