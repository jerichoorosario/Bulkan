package com.team.bulkan.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FbMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FbMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        String title = remoteMessage.getData().get("messageTitle");
        String message = remoteMessage.getData().get("body");
        NotifManager.sendNotification(this, title, message);

    }

}
