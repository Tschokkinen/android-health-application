package com.ericaskari.healthapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.ericaskari.healthapplication.modules.painhistory.NewPainLogActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Gavril Tschokkinen
 * NotificationService (service) is used to display a delayed notification after a pain has been reported.
 */

//Code taken from tutorial on https://www.tutorialspoint.com/send-a-notification-when-the-android-app-is-closed
//Code modified from start to finish to fit our use
public class NotificationService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String CHANNEL_ID = "default" ;
    final Handler handler = new Handler(); //we are going to use a handler to be able to run in our TimerTask
    Timer timer;
    TimerTask timerTask;
    int delayTime = 10; //Notification delay

    /**
     *
     * @param arg0
     * @return
     */
    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    //onStartCommand is ran when NotificationService is called in NewPainLogActivity
    @Override
    public int onStartCommand (Intent intent, int flags , int startId) {
        super .onStartCommand(intent, flags, startId);
        startTimer();
        return START_STICKY; //Recreate service with a null intent if memory runs out
    }

    /**
     * onDestroy function
     */
    //Stops timer onDestroy
    @Override
    public void onDestroy () {
        stopTimerTask(); //Call stopTimerTask() on destroy
        super .onDestroy();
    }

    /**
     * Start timer function
     */
    //Starts the timer
    public void startTimer () {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule( timerTask, 5000, delayTime * 1000);
    }

    /**
     * Used to stop the timer
     */
    //Stop timer
    public void stopTimerTask () {
        if ( timer != null ) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * Initialize timer
     */
    public void initializeTimerTask () {
        timerTask = new TimerTask() {
            public void run () {
                handler.post(new Runnable() {
                    public void run () {
                        createNotification();
                    }
                });
            }
        };
    }

    /**
     * Creates the notification displayed when the timer runs out
     */
    private void createNotification () {
        //Intent to show when notification is clicked
        Intent intent = new Intent(this, NewPainLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        //Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext() , CHANNEL_ID)
            .setContentTitle("Mihin sattuu?")
            .setContentText("Onko kipu hellittänyt?")
            .setTicker("Onko kipu hellittänyt?")
            .setSmallIcon(R.drawable.notification_icon)
                //Set intent for on notification click
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE) ;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}