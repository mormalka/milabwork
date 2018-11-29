package com.example.morm4.ex3_ntc;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class QReceiver extends BroadcastReceiver {

    private static final String TAG = "QReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Quotes display");
        QService.startService(context);
    }

}
