package com.dhcs.admin.iiitdapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 4/7/2017.
 */
public class TabFragment1 extends Fragment {
    private static final String TAG = "Tab1Fragment";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,container,false);
        //ImageView img=(ImageView)view.findViewById(R.id.image_gym);
        //img.setImageResource(R.drawable.gym_photo);

        return view;
    }
}