package com.example.demouser.bond;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;

/**
 * Created by demouser on 1/21/18.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private final Uri original = Uri.parse("android.resource://com.example.demouser.bond/drawable/octopus");

    @Override
    public void onReceive(Context context, Intent intent) {

        // Create a notification manager that manages different channels
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // The id of the channel.
        String id = "my_channel_01";

        //Now build notification. For Android 8.0 (API level 26) and higher, a Notification channel is required.
        //But we don't need it for our app since its minimum API level is 21
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(context, id)
                        .setSmallIcon(R.drawable.octopus)
                        .setContentTitle("You have a new reminder today")
                        .setContentText("Keep in touch");

        // When user clicks on the notification, come back to the program
        Intent resultIntent = new Intent(context, MainContactScreen.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of the app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainContactScreen.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        // Lead user to the program content and clear the notification when user clicks on it
        nBuilder.setContentIntent(resultPendingIntent);

        // Optional, but set the priority of the notification to be maximum when user sees it
        nBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        // Set large icon such as profile picture in the notification
        Bitmap largeIcon = null;
        try {
            largeIcon = MediaStore.Images.Media.getBitmap(context.getContentResolver(), original);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nBuilder.setLargeIcon(largeIcon);

        // mNotificationId is a unique integer the app uses to identify the notification
        // Use this to identify which notification to submit to the manager
        int mNotificationId = 001;
        mNotificationManager.notify(mNotificationId, nBuilder.build());
    }
}
