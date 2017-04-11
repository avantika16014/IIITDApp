package com.dhcs.admin.iiitdapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;


public class DirectoryActivity extends AppCompatActivity {

    // Declare Variables
    ListView list;
    ListViewAdapter_Directory adapter;
    EditText editsearch;
    String[] rank;
    String[] name;
    String[] extension;
    String[] roomno;

    String[] email;
    ArrayList<Directory> arraylist = new ArrayList<Directory>();

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        toolbar.setOverflowIcon(MenuActivity.profilePhoto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Generate sample data
        rank = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61" };
        roomno = new String[]{"A-205", "B-308 (A)", "B-308", "B-101", "--", "A-207-2", "A-302", "A-303", "B-106", "A-206", "A-405", "A-207-1", "B-405", "B-204", "B-308(C)", "A-403", "A-401", "B-205", "B-308-A", "B-206", "B-503", "B-502", "A-208", "B-104", "A-502-2", "A-305", "B-506", "A-202", "--", "B-406", "B-305", "A-501", "A-207-1", "A-404", "A-301", "B-308-C", "B-304", "B-102", "B-403", "B-504", "A-201", "B-103", "B-501", "A-406", "B-303", "A-203", "B-306", "A-502(4)", "B-302", "A-402", "B-203", "B-201", "A-304", "B-105", "--", "B-402" + "B-202", "B-404", "B-301", "A-306", "A-204"};

        name = new String[]{"A V Subramanyam", "Aasim Khan", " Akshay Kumar", "Alexander Fell", "Amarjeet Singh", "Amrit Srinivasan", "Anand Srivastava", "Angshul Majumdar", "Anubha Gupta", "Anuradha Sharma", "Arun Balaji Buduru", "Brijesh Eshpuniyani", "Chetan Arora", "Debajyoti Bera", "Debarka Sengupta", "Dheeraj Sanghi", "Dong Hoon Chang", "Ganesh Bagler", "Gaurav Arora", "G.P.S. Raghava", "G.S. Visweswaran", "Hemant Kumar", "Indrani De Parker", "Jyoti Sinha", "K.K. Biswas", "M S Hashmi",
                "Manohar Khushalani", "Mayank Vatsa", "Naqueeb Warsi", "Ojaswa Sharma", "P B Sujit", "Pankaj Jalote", "Pankaj Vajpayee", "Ponnurangam K.", "Pravesh Biyani", "Priyank Narayan", "Pushpendra Singh", "Pydi Ganga Mamba Bahubalindruni", "Rahul Purandare", "Raj Ayyar", " Richa Singh", "Saket Anand", "Samaresh Chatterji", "Sambuddho Chakravarty", "Sanjit Kaul", "Sarthok Sircar", "Shobha Sundar Ram", "Shriram Venkatraman", "Sneh Saurabh", "Somitra K. Sanadhya", "Sriram K.", "Subhadip Raychaudhuri", "Sujay Deb", "Sumit J Darak", "Tavpritesh Sethi", "Venkata M. Viswanath Gunturi",
                "Vibhor Kumar", "Vikram Goyal", "Vinayak S. Naik", "Vivek Ashok Bohara", "Vivek Kumar"

        };

        extension = new String[]{"437", "463", "448", "585", "--", "543", "450", "451", "428", "534", "469", "521", "475", "442", "446", "467", "465", "443", "547", "444", "489", "488", "527", "573", "535", "453", "492", "434", "--", "476", "459", "481", "515", "468", "449", "445", "458", "424", "473", "490", "433", "425", "487", "478", "457", "435", "460", "536", "456", "466", "436", "438", "452", "427", "--", "472", "440", "474", "455", "454", "441"};


        email = new String[]{"subramanyam at iiitd.ac.in", "aasim at iiitd.ac.in", "akshay at iiitd.ac.in", "alex at iiitd.ac.in", "amarjeet at iiitd.ac.in", "amrit at iiitd.ac.in", "anand at iiitd.ac.in", "angshul at iiitd.ac.in", "anubha at iiitd.ac.in", "anuradha at iiitd.ac.in", "arunb at iiitd.ac.in", "beshpuniyani at iiitd.ac.in", "chetan at iiitd.ac.in", "dbera at iiitd.ac.in", "debarka at iiitd.ac.in", "dheeraj at iiitd.ac.in", "donghoon at iiitd.ac.in", "bagler at iiitd.ac.in", "gaurav at iiitd.ac.in", "raghava at iiitd.ac.in", "viswes at iiitd.ac.in", "hemant at iiitd.ac.in", "indrani at iiitd.ac.in", "jyotisinha at iiitd.ac.in",
                "kkb at iiitd.ac.in", "mshashmi at iiitd.ac.in", "manohar at iiitd.ac.in", "mayank at iiitd.ac.in", "naqueeb at iiitd.ac.in", "ojaswa at iiitd.ac.in", "sujit at iiitd.ac.in", "jalote at iiitd.ac.in", "pvajpayee at iiitd.ac.in", "pk at iiitd.ac.in", "praveshb at iiitd.ac.in", "priyank at iiitd.ac.in", "psingh at iiitd.ac.in", "bpganga at iiitd.ac.in", "purandare at iiitd.ac.in", "raj at iiitd.ac.in", "rsingh at iiitd.ac.in", "anands at iiitd.ac.in", "samaresh at iiitd.ac.in", "sambuddho at iiitd.ac.in", "skkaul at iiitd.ac.in", "sarthok at iiitd.ac.in", "shobha at iiitd.ac.in", "shriramv at iiitd.ac.in", "sneh at iiitd.ac.in",
                "somitra at iiitd.ac.in", "sriramk at iiitd.ac.in", "subhadip at iiitd.ac.in", "sdeb at iiitd.ac.in", "sumit at iiitd.ac.in", "tavpriteshsethi at iiitd.ac.in", "gunturi at iiitd.ac.in", "vibhor at iiitd.ac.in", "vikram at iiitd.ac.in", "naik at iiitd.ac.in", "vivek.b at iiitd.ac.in", "vivekk at iiitd.ac.in"};


        // Locate the ListView in activity_directory.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < rank.length-1; i++) {
            Directory wp = new Directory(rank[i], name[i],roomno[i],extension[i],email[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter_Directory(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in activity_directory.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
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
            Toast.makeText(DirectoryActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DirectoryActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
