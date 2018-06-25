package com.demopushnotifycationandroidonly;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.facebook.react.HeadlessJsTaskService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import io.invertase.firebase.Utils;
import io.invertase.firebase.messaging.RNFirebaseBackgroundMessagingService;

/**
 * Created by nguyenvannghia on 6/25/18
 */
public class RNFirebaseMessagingServiceCustom extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
    }
}
