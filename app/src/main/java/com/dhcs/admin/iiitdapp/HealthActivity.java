package com.dhcs.admin.iiitdapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.dhcs.admin.iiitdapp.R.id.tabs;

public class HealthActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        toolbar.setOverflowIcon(MenuActivity.profilePhoto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(tabs);
        //tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);

        /*tabLayout.getTabAt(0).setCustomView(R.layout.title_tab_layout);
        tabLayout.getTabAt(0).setIcon(R.drawable.gym);
        tabLayout.getTabAt(0).setText("GYM");
        tabLayout.getTabAt(1).setCustomView(R.layout.title_tab_layout);
        tabLayout.getTabAt(1).setIcon(R.drawable.yoga);
        tabLayout.getTabAt(1).setText("YOGA");*/

        //tabLayout.getTabAt(0).setIcon(R.drawable.weightlifting);
        //tabLayout.getTabAt(1).setIcon(R.drawable.yoga1);
        //tab.setCustomLayout( R.layout.whate );

        /******************************************/
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.title_tab_layout, null);
        tabOne.setText("GYM");
        tabOne.setCompoundDrawablesWithIntrinsicBounds( R.drawable.weightlifting, 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.title_tab_layout, null);
        tabTwo.setText("YOGA");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.yoga1, 0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        //tabLayout.addTab(add);
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
            Toast.makeText(HealthActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HealthActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new TabFragment1(), "Gym");
        adapter.addFragment(new TabFragment2(), "Yoga");
        viewPager.setAdapter(adapter);
    }
}
