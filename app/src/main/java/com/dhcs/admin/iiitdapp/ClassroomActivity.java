package com.dhcs.admin.iiitdapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassroomActivity extends AppCompatActivity {

    /**************************** variables used throughout *********************************/
    //custom toolbar
    private Toolbar toolbar;
    //adding courses fab
    private FloatingActionButton fab;
    //displaying courses and selecting
    private View bottomSheet;
    //to search for course
    private SearchView searchQuery;
    //stores the list of all available courses
    private ListView list_courses;
    //stores the list of the schedule of selected course
    private ListView list_schedule;
    //stores the course selected to be added
    private TextView courseName_selected;
    //stores name of selected course
    private String selected_course;
    private BottomSheetBehavior bottomSheetBehavior;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("message");
    SQLiteDatabase alarmDatabase;
    DBHelper myDbHelper;
    //toggle for hosteler
    private TextView hosteler_textview;
    //textview for viewing timetable
    private TextView timetable_textview;
    //toggle for hosteler
    private Switch hosteler_switch;
    //cursor which stores the schedule of selected course
    private Cursor cursor_schedule;
    //stores all course names
    private ArrayList<String> courseNames;
    private static ArrayAdapter adapter_courseNames;
    //adapter for showing schedule of course
    private ScheduleCursorAdapter adapter_schedule;
    private Button button_submit_schedule;
    //between course name and schedule in bottom sheet
    private View divider2;
    private TextView add_course_hint;
    //selected schedule for a course
    private ExpandableListView selectedCourseListView;
    private ExpandableCourseListAdapter selectedCourseAdapter;
    private List<String> selectedScheduleList;
    private List<String> selectedCourseNamesList;
    private HashMap<String,List<String>> scheduleHash;
    private View schedule_li_view;
    int flag=0;

    /**************************************************onCreate**************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        /*****************************************set custom app bar********************************/
        //custom toolbar having image from google account
        setupToolbar();
        /************************************************bottom sheet**************************************/
        init();
        //initialize and open the database file
        createDatabase();
        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    //Toast.makeText(ClassroomActivity.this, "expanded", Toast.LENGTH_SHORT).show();
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                */
                list_courses.setVisibility(View.VISIBLE);
                list_schedule.setVisibility(View.GONE);
                searchQuery.setVisibility(View.VISIBLE);
                courseName_selected.setVisibility(View.GONE);
                divider2.setVisibility(View.GONE);
                button_submit_schedule.setVisibility(View.GONE);
                if(flag==0)
                {
                    courseNames=myDbHelper.retrieveCourses();
                    flag=1;
                }
                System.out.println(""+courseNames.size());
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    //Toast.makeText(ClassroomActivity.this, "expanded from fab", Toast.LENGTH_SHORT).show();
                }
                adapter_courseNames=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,courseNames);
                list_courses.setAdapter(adapter_courseNames);
            }
        });

        //*** setOnQueryTextListener ***
        searchQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                adapter_courseNames.getFilter().filter(newText);
                return false;
            }
        });

        list_courses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selected_course=list_courses.getItemAtPosition(position).toString();
                setScheduleCursorAdapter();
                selectedScheduleList=new ArrayList<>();
                changeVisibility1();
            }
        });

        list_schedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //final CheckBox cb=(CheckBox) view.findViewById(R.id.li_checkBox);
                final CheckedTextView cb=(CheckedTextView)view.findViewById(R.id.checked_tv_schedule_item);
                if(cb.isChecked())
                {
                    cb.setChecked(false);
                    selectedScheduleList.remove(cb.getText().toString());
                }
                else
                {
                    cb.setChecked(true);
                    selectedScheduleList.add(cb.getText().toString());
                }
            }
        });
        button_submit_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedScheduleList.size()==0)
                {
                    Toast.makeText(ClassroomActivity.this, "You haven't selected any class.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!selectedCourseNamesList.contains(selected_course))
                        selectedCourseNamesList.add(selected_course);
                    else
                        Toast.makeText(ClassroomActivity.this, "Updated.", Toast.LENGTH_SHORT).show();
                    scheduleHash.put(selected_course,selectedScheduleList);
                    add_course_hint.setVisibility(View.GONE);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    selectedCourseAdapter= new ExpandableCourseListAdapter(getApplicationContext(),selectedCourseNamesList,scheduleHash);
                    selectedCourseListView.setAdapter(selectedCourseAdapter);
                    //selectedCourseListView.expandGroup(0);
                    updateDatabase(selectedCourseNamesList,scheduleHash);
                }

            }
        });


    }

    private void init() {
        bottomSheet=findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        divider2=findViewById(R.id.divider2);
        courseNames=new ArrayList<>();
        searchQuery=(SearchView)findViewById(R.id.searchQuery);

        list_courses=(ListView)findViewById(R.id.list_all_courses);
        list_schedule=(ListView)findViewById(R.id.schedule_for_course);
        schedule_li_view=findViewById(R.id.item_schedule_selection);
        courseName_selected=(TextView)findViewById(R.id.courseName_selected);
        add_course_hint=(TextView)findViewById(R.id.add_course_hint);
        button_submit_schedule=(Button)findViewById(R.id.button_submit);
        button_submit_schedule.setVisibility(View.GONE);

        fab=(FloatingActionButton)findViewById(R.id.fab_add_course);
        timetable_textview=(TextView)findViewById(R.id.textview_timetable);
        selectedCourseListView=(ExpandableListView)findViewById(R.id.selected_schedule_list);
        timetable_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassroomActivity.this,TimeTableActivity.class);
                startActivity(intent);
            }
        });
        selectedScheduleList=new ArrayList<>();
        scheduleHash=new HashMap<>();
        selectedCourseNamesList=new ArrayList<>();
    }

    private void setupToolbar() {
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        toolbar.setOverflowIcon(MenuActivity.profilePhoto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addCourseToAlarmSet() {


    }

    private void setScheduleCursorAdapter() {

        cursor_schedule=myDbHelper.retrieveScheduleByCourseName(selected_course);
        adapter_schedule = new ScheduleCursorAdapter(getApplicationContext(),cursor_schedule);
        list_schedule.setAdapter(adapter_schedule);
        courseName_selected.setText(selected_course);
    }

    private void changeVisibility1() {

        list_schedule.setVisibility(View.VISIBLE);
        divider2.setVisibility(View.VISIBLE);
        button_submit_schedule.setVisibility(View.VISIBLE);
        list_courses.setVisibility(View.GONE);
        searchQuery.setVisibility(View.INVISIBLE);
        courseName_selected.setVisibility(View.VISIBLE);

    }

    private void createDatabase(){
        myDbHelper = new DBHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }
    public void updateDatabase(List<String> courseNameList, HashMap<String,List<String>> courseDetailList){
        myDbHelper.deleteRecords();
        //for(String key:courseDetailList.keySet()){
        //    String[] temp=courseDetailList.get(key);
       // }
        System.out.print("hello "+ courseDetailList);
    }
    //displaying menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_signout){
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(ClassroomActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ClassroomActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(list_schedule.getVisibility()==View.VISIBLE){
            Toast.makeText(this, "Your choices will not be saved.", Toast.LENGTH_SHORT).show();
            selectedScheduleList.clear();
            list_courses.setVisibility(View.VISIBLE);
            list_schedule.setVisibility(View.GONE);
            button_submit_schedule.setVisibility(View.GONE);
            courseName_selected.setVisibility(View.GONE);
            searchQuery.setVisibility(View.VISIBLE);
        }
        else
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        else super.onBackPressed();
    }
}
