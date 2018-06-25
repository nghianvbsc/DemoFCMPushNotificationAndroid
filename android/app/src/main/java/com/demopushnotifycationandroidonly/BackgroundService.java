package com.demopushnotifycationandroidonly;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by nguyenvannghia on 6/25/18
 */
public class BackgroundService extends IntentService {

    private static final String TAG = "RNFMessagingService";
    public static final String MESSAGE_EVENT = "messaging-message";
    public static final String REMOTE_NOTIFICATION_EVENT = "notifications-remote-notification";

    public BackgroundService() {
        super("BackgroundService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent actionIntent = new Intent(getBaseContext(), MainActivity.class);

        NotificationCompat.Builder mBuilder;
        mBuilder = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("message"))
                .setAutoCancel(true);

        actionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0,
                actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Builds the notificationModel and issues it.
        mNotifyMgr.notify(1, mBuilder.build());

    }
}
