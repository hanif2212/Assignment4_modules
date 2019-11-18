package com.example.screentime;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.example.roomdatabase.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ScreenTimeBroadcastReceiver extends BroadcastReceiver {
    String TAG = "ScreenReceiver";
    private Database database;
    private static int count=0;

    public ScreenTimeBroadcastReceiver(Database database) {
        this.database = database;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() !=null) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                database.insert_screenTime_used("Screen Turned Off",String.valueOf(count), currentDate,currentTime);
                Log.i(TAG, "Screen is off now");
                Log.i(TAG, "Count"+count);
            }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                database.insert_screenTime_used("Screen Turned On",String.valueOf(count++),currentDate , currentTime);
                Log.i(TAG, "Count"+count);
            }
        }
    }
}
