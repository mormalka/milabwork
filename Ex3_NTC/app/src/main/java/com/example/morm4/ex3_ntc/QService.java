package com.example.morm4.ex3_ntc;

import android.app.AlarmManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationManagerCompat;


public class QService extends IntentService {

    public static final String ACTION = "com.example.morm4.ex3_ntc.action";
    public static final long SECONDS = 1000 * 15;
    public static final String[] DATA_QUOTES={"A"
                                               ,"B"
                                               ,"C"};


    public static AlarmManager alarmManager;
    public static NotificationManagerCompat notificationManager;
    public static int id = 0;



    public QService() {
        super("qService");}

    public static void doAction(Context context) {
        Intent intent = new Intent(context, QService.class);
        intent.setAction(ACTION);
        context.startService(intent);

    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION.equals(action)) {
                handleAction();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }
    private void handleAction() {
    }
}
