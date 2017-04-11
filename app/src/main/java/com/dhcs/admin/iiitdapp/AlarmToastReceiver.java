package com.dhcs.admin.iiitdapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Admin on 4/8/2017.
 */

public class AlarmToastReceiver extends BroadcastReceiver {
    public AlarmToastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm set ", Toast.LENGTH_SHORT).show();
    }
}
