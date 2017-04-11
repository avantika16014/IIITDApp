package com.dhcs.admin.iiitdapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hitomi.cmlibrary.CircleMenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.graphics.Bitmap.createScaledBitmap;

public class MenuActivity extends AppCompatActivity {


    private static final String TAG = "MENU ACTIVITY";
    private SwipeRefreshLayout srl;
    private Toolbar toolbar;
    private TextView username;
    private TextView uid;
    private TextView email;
    public static Drawable profilePhoto;
    private ImageView foodhub;
    private ImageView directory;
    private ImageView health;
    private ImageView help;
    private ImageView classes;
    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl_menu);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new RetrieveImageTask().execute(MainActivity.photoURL);
                setSupportActionBar(toolbar);
            }
        });

        //custom toolbar having image from google account
        /*   ****************used earlier********************
        profilePhoto=(ImageView)findViewById(R.id.profileImage);
        Picasso.with(MenuActivity.this).load(url).into(profilePhoto);
        toolbar.setOverflowIcon(profilePhoto.getDrawable());
        image=getResources().getDrawable(R.drawable.user);


        *************user's profile information*******************
        username=(TextView)findViewById(R.id.textview_username);
        email=(TextView)findViewById(R.id.textview_email);
        classes=(Button)findViewById(R.id.button_class);
        profilePhoto=(CircleImageView)findViewById(R.id.profilePhoto);
        username.setText(getIntent().getStringExtra("displayName"));
        email.setText(getIntent().getStringExtra("Email"));
        System.out.println("URL PHOTO "+url);
        */

        /**************************copying toolbar ***********************/
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        new RetrieveImageTask().execute(MainActivity.photoURL);
        setSupportActionBar(toolbar);

        //******************* referencing menu*********************

        classes=(ImageView) findViewById(R.id.ic_class);
        foodhub=(ImageView)findViewById(R.id.ic_foodhub);
        help=(ImageView)findViewById(R.id.ic_help);
        directory=(ImageView)findViewById(R.id.ic_directory);
        health=(ImageView)findViewById(R.id.ic_health);

        //******************* opening corresponding intents ***************

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,ClassroomActivity.class));
            }
        });
        directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,DirectoryActivity.class));
            }
        });
        foodhub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,FoodhubActivity.class));
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,HelpActivity.class));
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,HealthActivity.class));
            }
        });

        //to assign user's profile image to circleImageView

        /*profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openOptionsMenu();
            }
        });
        Glide.with(getApplicationContext()).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profilePhoto);
        */



        /********************* Circle Menu *********************/
        /*
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#ff4081"), R.drawable.logo, R.drawable.ic_cancel_white_24dp);
        circleMenu.addSubMenu(Color.parseColor("#1565c0"), R.drawable.classroom_final)
                .addSubMenu(Color.parseColor("#1565c0"), R.drawable.restaurant_final2)
                .addSubMenu(Color.parseColor("#1565c0"), R.drawable.directory_final)
                .addSubMenu(Color.parseColor("#1565c0"), R.drawable.help_final)
                .addSubMenu(Color.parseColor("#1565c0"), R.drawable.fitness_final);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

            Handler myHandler = new Handler();
                 @Override
                 public void onMenuSelected(int index) {
                     switch (index) {
                         case 0:

                             myHandler.postDelayed(classRunnable, 300);
                             break;
                         case 1:
                             myHandler.postDelayed(foodRunnable, 300);
                             break;
                         case 2:
                             myHandler.postDelayed(directoryRunnable, 300);
                             break;
                         case 3:
                             myHandler.postDelayed(helpRunnable, 300);
                             break;
                         case 4:
                             myHandler.postDelayed(healthRunnable, 300);
                             break;
                     }
                 }
             }

        );*/
    }
    /*
    private Runnable classRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
            startActivity(new Intent(MenuActivity.this,ClassroomActivity.class));
        }
    };
    private Runnable healthRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
            startActivity(new Intent(MenuActivity.this,HealthActivity.class));
        }
    };
    private Runnable directoryRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
            startActivity(new Intent(MenuActivity.this,DirectoryActivity.class));
        }
    };
    private Runnable foodRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
            startActivity(new Intent(MenuActivity.this,FoodhubActivity.class));
        }
    };
    private Runnable helpRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
            startActivity(new Intent(MenuActivity.this,HelpActivity.class));
        }
    };
    */
    //async task to load user's image on toolbar

    class RetrieveImageTask extends AsyncTask<String, Void, Drawable> {

        protected Drawable doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                final int width = myBitmap.getWidth();
                final int height = myBitmap.getHeight();
                Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                final Path path = new Path();
                path.addCircle(
                        (float) (width / 2)
                        , (float) (height / 2)
                        , (float) Math.min(width, (height / 2))
                        , Path.Direction.CCW);

                final Canvas canvas = new Canvas(outputBitmap);
                canvas.clipPath(path);
                canvas.drawBitmap(myBitmap, 0, 0, null);
                outputBitmap = createScaledBitmap(outputBitmap, 400, 400, false);
                Drawable d = new BitmapDrawable(outputBitmap);
                return d;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        protected void onPostExecute(Drawable d) {
            // TODO: check this.exception
            // TODO: do something with the feed
            profilePhoto = d;
            toolbar.setOverflowIcon(d);
            if (srl.isRefreshing())
                srl.setRefreshing(false);
            //setSupportActionBar(toolbar);
        }
    }

    //displaying menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_signout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MenuActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MenuActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(this, "No Internet Connection available", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()" + MainActivity.photoURL);
        if (checkConnection())
            new RetrieveImageTask().execute(MainActivity.photoURL);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "onStart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }
    @Override
    public void onBackPressed(){
            super.onBackPressed();
    }
}
