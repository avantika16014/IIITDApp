package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    static Intent myIntent;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        toolbar.setOverflowIcon(MenuActivity.profilePhoto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView=(ListView)findViewById(R.id.contact_list);
        listView.setAdapter(new ViewAdapter(this));



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:01126907420"));
                    startActivity(myIntent);
                }

                if (position == 1) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:01126907400"));
                    startActivity(myIntent);
                }

                if (position == 2) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:9868244868"));
                    startActivity(myIntent);
                }

                if (position == 3) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:01126907576"));
                    startActivity(myIntent);
                }

                if (position == 4) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:7838732232"));
                    startActivity(myIntent);
                }

                if (position == 5) {
                    myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9650907449"));
                    startActivity(myIntent);
                }
                if (position == 6) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:9891314535"));
                    startActivity(myIntent);
                }
                if (position == 7) {
                    myIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:9911143723"));
                    startActivity(myIntent);
                }

            }

        });
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
            Toast.makeText(HelpActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HelpActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
class ViewAdapter extends BaseAdapter
{
    Context c;
    ArrayList<SingleRow> list;
    ViewAdapter(Context context)
    {
        c=context;
        list =new ArrayList<SingleRow>();
        Resources resources=context.getResources();
        String[] Titles=resources.getStringArray(R.array.contact_names);
        String[] Descriptions=resources.getStringArray(R.array.contact_numbers);
        int[] images2={R.drawable.facilities,R.drawable.admin,R.drawable.security,R.drawable.it,R.drawable.counsellor,R.drawable.nurse,R.drawable.doctor,R.drawable.ambulance};
        int[] images1={R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1};

        for(int i=0;i<8;i++)
        {
            list.add(new SingleRow(images2[i],Titles[i],Descriptions[i],images1[i]));
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.help_single_item, parent, false);

        ImageView img1 = (ImageView) row.findViewById(R.id.imageView2);
        ImageView img2 = (ImageView) row.findViewById(R.id.imageView);
        TextView txt1 = (TextView) row.findViewById(R.id.textView);
        TextView txt2 = (TextView) row.findViewById(R.id.textView2);
        SingleRow temp = list.get(position);
        img1.setImageResource(temp.Imag1);
        img2.setImageResource(temp.Imag2);
        txt1.setText(temp.Title);
        txt2.setText(temp.Desc);

        img1.setClickable(false);
        txt1.setClickable(false);
        txt2.setClickable(false);


        return row;
    }


}
class SingleRow
{

    String Title;
    String Desc;
    int Imag1;
    int Imag2;
    SingleRow(int img2,String str1,String str2,int img1)
    {
        Title=str1;
        Desc=str2;
        Imag1=img2;
        Imag2=img1;

    }
}