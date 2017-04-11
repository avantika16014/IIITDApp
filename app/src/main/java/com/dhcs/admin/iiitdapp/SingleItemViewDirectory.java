package com.dhcs.admin.iiitdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Admin on 4/8/2017.
 */

public class SingleItemViewDirectory extends Activity {

    class SingleItemView extends Activity {
        // Declare Variables
        TextView txtrank;
        TextView txtname;
        TextView txtroomno;
        TextView txtextension;
        TextView txtemail;
        String rank;
        String name;
        String email;
        String roomno;
        String extension;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.singleitemview);
            // Retrieve data from MainActivity on item click event
            Intent i = getIntent();
            // Get the results of rank
            rank = i.getStringExtra("rank");

            name = i.getStringExtra("name");
            roomno = i.getStringExtra("roomno");
            extension = i.getStringExtra("extension");

            email = i.getStringExtra("email");

            // Locate the TextViews in singleitemview.xml
            //txtrank = (TextView) findViewById(R.id.rank);
            txtname = (TextView) findViewById(R.id.name);
            txtroomno = (TextView) findViewById(R.id.roomno);
            txtextension = (TextView) findViewById(R.id.extension);
            txtemail = (TextView) findViewById(R.id.email);

            // Load the results into the TextViews
            //txtrank.setText(rank);
            txtname.setText(name);
            txtroomno.setText(roomno);
            txtextension.setText(extension);
            txtemail.setText(email);
        }
    }
}
