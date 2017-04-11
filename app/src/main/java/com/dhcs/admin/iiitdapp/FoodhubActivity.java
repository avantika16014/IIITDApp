package com.dhcs.admin.iiitdapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodhubActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static List<MenuTask> menuTaskList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuTaskAdapter mAdapter;

    private int menutaskid=0;
    private static boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodhub);
        toolbar=(Toolbar) findViewById(R.id.app_bar);
        toolbar.setOverflowIcon(MenuActivity.profilePhoto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.tv_recycler_view);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        if(flag == true)
        {
            if (day == Calendar.SUNDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST \nVada, Sambhar, Milk/Tea, Bread, Butter\n" +
                        "LUNCH\nVegetable Pulao, Dal Makhani, Mix Veg, Raita, Roti, Salad, Papad\n" +
                        "SNACKS \nBread Roll, Tea\n" +
                        "DINNER \nMatar Paneer, Veg Biryani, Chicken Biryani, Raita, Rice, Roti, Salad, Gulab Jamun",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }
            else if (day == Calendar.MONDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST\nCutlet, Sprouts, Bread Butter, Cornflakes, Milk/Tea\n" +
                        "LUNCH\nChole, Soyabean, Matar, Rice, Poori, Salad, Papad, Boondi Raita\n" +
                        "SNACKS\nFried Idli, Tea\n" +
                        "DINNER\nMoong Dal,Ghiya Chana Dry, Rice, Roti, Salad, Sewaiyan",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }

            else if(day==Calendar.TUESDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST\nPav Bhaji, Chutney, Bread Butter, Milk/Tea\n" +
                        "LUNCH\nRajma, Kachha Kela (Dry),Fried Rice, Roti, Salad, Papad, Raita\n" +
                        "SNACKS\nDahi Wada, Rasna\n" +
                        "DINNER\nArhar Dal Fry, Chana Sabzi, Rice, Roti, Salad, Moong Dal Halwa",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }
            else if(day==Calendar.WEDNESDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST \nAloo Parantha, Dhaniya Chutney, Bread Butter, Milk/Tea\n" +
                        "LUNCH\nChana Dal, Gobhi Aloo, Rice, Roti, Salad, Papad, Boondi Raita\n" +
                        "SNACKS \nVeg Sandwich, Coffee\n" +
                        "DINNER \nChicken Masala, Shahi Paneer, Dal, Rice, Roti, Salad, Gulab Jamun",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }
            else if(day==Calendar.FRIDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST \nPoori Aloo, Bread Butter, Milk/Tea\n" +
                        "LUNCH\nLobhia, Jeera Aloo, Rice, Roti, Salad, Papad, Boondi Raita\n" +
                        "SNACKS\nPakori, Coffee\n" +
                        "DINNER \nEgg Curry, Mix Dal, Palak Paneer, Rice, Roti, Salad, Kheer",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }
            else if(day==Calendar.THURSDAY)
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST \nIdli Sambhar, Cornflakes, Bread Butter, Milk/Tea\n" +
                        "LUNCH\nKadhi, Methi Aloo, Rice, Roti, Salad, Papad\n" +
                        "SNACKS \nSamosa, Tea\n" +
                        "DINNER \nMalai Kofta, Mix Veg, Jeera Rice, Roti, Ice Cream",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }
            else
            {
                MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST \nPoha, Chutney, Aloo Sandwich, Bread Butter, Milk/Tea\n" +
                        "LUNCH\nChole Kulche, Fried Rice, Roti, Salad, Papad, Pickle, Curd\n" +
                        "SNACKS\nDhokla, Shikanji\n" +
                        "DINNER\nArhar Dal, Gobhi Aloo, Rice, Roti, Salad, Kheer",menutaskid);
                menutaskid++;
                menuTaskList.add(menuTask1);
            }

            MenuTask menuTask2 = new MenuTask("Canteen","Veg/Plain Maggi\n" +
                    "Samosa\nPav Bhaji\n" +
                    "Aloo Parantha\n" +
                    "Pastry\n" +
                    "Veg. Momos\nChicken/Veg Roll\nVeg/Paneer Puff\nVeg Sandwich\nKathi Roll\nChana Rice/Roti\n" +
                    "Matar Paneer Rice/Roti\nChicken Rice/Roti",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask2);
            MenuTask menuTask3 = new MenuTask("Juice Corner","Orange Juice\nMixed Fruit Juice\nWatermelon Juice\nBanana Shake\nMango Shake\nChocolate Shake\nLassi\nHot Milk\nHot Coffee\nCold Coffee\nFruit Chat",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask3);
            MenuTask menuTask4 = new MenuTask("CDX","EATABLES:\nVeg. Puff\nTomato & Cheese Sandwich\nSpinach & Corn Sandwich\nTex Mex Sandwich\nSamosa\nCup Noodles(Veg/Non Veg)\nBEVERAGES:\nCold Coffee\n" +
                    "Orangeno" +
                    "\nCappuccino(Small/Large)\nEspresso(Small/Large)",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask4);
            MenuTask menuTask5 = new MenuTask("Chat Corner","Aloo Tikki\nGol Gappe\nBurger\nPav Bhaji\nChowmein\nPapdi Chat\nBhalla Papdi",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask5);
            flag=false;
        }
            /*switch (day) {
                case Calendar.SUNDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Vada, Sambhar, Milk/Tea, Bread, Butter\n" +
                            "LUNCH -> Vegetable Pulao, Dal Makhani, Mix Veg, Raita, Roti, Salad, Papad\n" +
                            "SNACKS -> Bread Roll, Tea\n" +
                            "DINNER -> Matar Paneer, Veg Biryani, Chicken Biryani, Raita, Rice, Roti, Salad, Gulab Jamun",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }

                case Calendar.MONDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Cutlet, Sprouts, Bread Butter, Cornflakes, Milk/Tea\n" +
                            "LUNCH -> Chole, Soyabean, Matar, Rice, Poori, Salad, Papad, Boondi Raita\n" +
                            "SNACKS -> Fried Idli, Tea\n" +
                            "DINNER -> Moong Dal,Ghiya Chana Dry, Rice, Roti, Salad, Sewaiyan",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }

                case Calendar.TUESDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Pav Bhaji, Chutney, Bread Butter, Milk/Tea\n" +
                            "LUNCH -> Rajma, Kachha Kela (Dry),Fried Rice, Roti, Salad, Papad, Raita\n" +
                            "SNACKS -> Dahi Wada, Rasna\n" +
                            "DINNER -> Arhar Dal Fry, Chana Sabzi, Rice, Roti, Salad, Moong Dal Halwa",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }
                case Calendar.WEDNESDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Aloo Parantha, Dhaniya Chutney, Bread Butter, Milk/Tea\n" +
                            "LUNCH -> Chana Dal, Gobhi Aloo, Rice, Roti, Salad, Papad, Boondi Raita\n" +
                            "SNACKS -> Veg Sandwich, Coffee\n" +
                            "DINNER -> Chicken Masala, Shahi Paneer, Dal, Rice, Roti, Salad, Gulab Jamun",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }
                case Calendar.FRIDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Poori Aloo, Bread Butter, Milk/Tea\n" +
                            "LUNCH -> Lobhia, Jeera Aloo, Rice, Roti, Salad, Papad, Boondi Raita\n" +
                            "SNACKS -> Pakori, Coffee\n" +
                            "DINNER -> Egg Curry, Mix Dal, Palak Paneer, Rice, Roti, Salad, Kheer",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }
                case Calendar.THURSDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Idli Sambhar, Cornflakes, Bread Butter, Milk/Tea\n" +
                        "LUNCH -> Kadhi, Methi Aloo, Rice, Roti, Salad, Papad\n" +
                        "SNACKS -> Samosa, Tea\n" +
                            "DINNER -> Malai Kofta, Mix Veg, Jeera Rice, Roti, Ice Cream",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }
                case Calendar.SATURDAY:
                {
                    MenuTask menuTask1 = new MenuTask("Mess","BREAKFAST -> Poha, Chutney, Aloo Sandwich, Bread Butter, Milk/Tea\n" +
                            "LUNCH -> Chole Kulche, Fried Rice, Roti, Salad, Papad, Pickle, Curd\n" +
                            "SNACKS -> Dhokla, Shikanji\n" +
                            "DINNER -> Arhar Dal, Gobhi Aloo, Rice, Roti, Salad, Kheer",menutaskid);
                    menutaskid++;
                    menuTaskList.add(menuTask1);
                }
            }
            MenuTask menuTask2 = new MenuTask("Canteen","Veg/Plain Maggi\n" +
                    "Samosa\nPav Bhaji\n" +
                    "Aloo Parantha\n" +
                    "Pastry\n" +
                    "Veg. Momos\nChicken/Veg Roll\nVeg/Paneer Puff\nVeg Sandwich\nKathi Roll\nChana Rice/Roti\n" +
                    "Matar Paneer Rice/Roti\nChicken Rice/Roti",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask2);
            MenuTask menuTask3 = new MenuTask("Juice Corner","Orange Juice\nMixed Fruit Juice\nWatermelon Juice\nBanana Shake\nMango Shake\nChocolate Shake\nLassi\nHot Milk\nHot Coffee\nCold Coffee\nFruit Chat",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask3);
            MenuTask menuTask4 = new MenuTask("CDX","EATABLES:\nVeg. Puff\nTomato & Cheese Sandwich\nSpinach & Corn Sandwich\nTex Mex Sandwich\nSamosa\nCup Noodles(Veg/Non Veg)\nBEVERAGES:\nCold Coffee\n" +
                    "Orangeno" +
                    "\nCappuccino(Small/Large)Espresso(Small/Large)",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask4);
            MenuTask menuTask5 = new MenuTask("Chat Corner","Aloo Tikki\nGol Gappe\nBurger\nPav Bhaji\nChowmein",menutaskid);
            menutaskid++;
            menuTaskList.add(menuTask5);
            flag=false;
        }*/


        mAdapter = new MenuTaskAdapter(this, menuTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


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
            Toast.makeText(FoodhubActivity.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FoodhubActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
