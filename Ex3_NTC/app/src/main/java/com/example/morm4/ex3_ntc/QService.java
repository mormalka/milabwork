package com.example.morm4.ex3_ntc;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class QService extends JobIntentService {

    public static final String ACTION_INIT = "com.example.morm4.ex3_ntc.action.ACTION_INIT";
    public static final String ACTION_START = "com.example.morm4.ex3_ntc.action.ACTION_START";
    public static final long TIME_SEC = 100*10; // every 10 seconds
    public static final int JOB_ID = 1;
    public static final String CHANNEL_ID = "1";
    public static final String[] DATA_QUOTES=
                    {"I used to think I was indecisive, but now I'm not too sure.",
                    "Doing nothing is hard, you never know when you're done.",
                    "If two wrongs don't make a right, try three.",
                    "I am not lazy, I am on energy saving mode.",
                    "Life is short, smile while you still have teeth.",
                    "A balanced diet means a cupcake in each hand.",
                    "Maybe you should eat some makeup so you can be pretty on the inside too.",
                    "I'm not shy, I'm holding back my awesomeness so I don't intimidate you.",
                    "Sorry for the mean, awful, accurate things I said.",
                    "I’m sorry, if you were right, I’d agree with you.",
                    "Your life can't fall apart if you never had it together!",
                    "People say nothing is impossible, but I do nothing every day.",
                    "A bank is a place that will lend you money if you can prove that you don't need it.",
                    "If you think nobody cares if you're alive, try missing a couple of payments.",
                    "Always remember that you're unique. Just like everyone else.",
                    "The answer you're looking for is inside of you, but it's wrong",
                    "One advantage of talking to yourself is that you know at least somebody's listening.",
                    "The elevator to success is out of order. You’ll have to use the stairs.",
                    "An apple a day keeps anyone away if you throw it hard enough.",
                    "The more you weight the harder you are to kidnap. Stay safe, eat cake."};


    public static AlarmManager AManager;
    private static int id = 0;


    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, QService.class, JOB_ID, work);
    }

    //initial the service, called by the mainActivity
    public static void initService(Context context) {
        Intent intent = new Intent(context, QService.class);
        intent.setAction(ACTION_INIT);
        enqueueWork(context, intent);
    }
    //called by the broadcast receiver
    public static void startService(Context context) {
        Intent intent = new Intent(context, QService.class);
        intent.setAction(ACTION_START);
        enqueueWork(context, intent);

    }
    //check if intent
    @Override
    protected void onHandleWork(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                    wakeAlarm();
                    handleAction();
            } else {
                    if (ACTION_START.equals(action)) {
                        handleAction();

                    }else
                        {throw new RuntimeException("Unknown action provided");}
            }
        }

    }
    private void wakeAlarm (){
        Intent intent = new Intent(this, QReceiver.class);
        AManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        AManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), TIME_SEC, PendingIntent.getBroadcast(this, 0, intent, 0));
        registerNotificationChannel();
    }

    private void handleAction() {
        String display = DATA_QUOTES[(id % DATA_QUOTES.length)];
        NotificationCompat.Builder builder = new NotificationCompat.Builder (this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(display)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat ntcManager = NotificationManagerCompat.from(this);
        ntcManager.notify(++id , builder.build());
    }
    protected void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_HIGH));
        }
    }
}