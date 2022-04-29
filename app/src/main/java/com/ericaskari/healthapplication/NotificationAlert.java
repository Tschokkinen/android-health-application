package com.ericaskari.healthapplication;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ericaskari.healthapplication.modules.painhistory.NewPainLogActivity;

/**
 * @author Gavril Tschokkinen
 */
public class NotificationAlert {
    private static final String CHANNEL_ID = "Notification";
    private Context context;

    public NotificationAlert (Context context) {
        this.context = context;
    }

    //Based on this article: https://developer.android.com/training/notify-user/build-notification#java
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createNotification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(context, NewPainLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Mihin sattuu?")
                .setContentText("Onko kipu hellittänyt?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}
