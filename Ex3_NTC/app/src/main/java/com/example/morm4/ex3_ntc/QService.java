package com.example.morm4.ex3_ntc;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class QService extends IntentService {

    public static final String ACTION = "com.example.morm4.ex3_ntc.action";
    public static final long TIME_SEC = 1000 * 15;
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
    public static NotificationManagerCompat ntcManager;
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

        Intent intent = new Intent(this, QReceiver.class);
        AManager =(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        AManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), TIME_SEC, PendingIntent.getBroadcast(this, 0, intent, 0));

        String display = DATA_QUOTES[(id % DATA_QUOTES.length)];
        NotificationCompat.Builder builder = new NotificationCompat.Builder (this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(display)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        ntcManager = NotificationManagerCompat.from(this);
        ntcManager.notify(++id , builder.build());
    }
}
