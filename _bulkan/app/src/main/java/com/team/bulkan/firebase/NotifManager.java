package com.team.bulkan.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.team.bulkan.MainActivity;
import com.team.bulkan.R;
import com.team.bulkan.SplashActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class NotifManager {

    static class NotificationID {
        private final static AtomicInteger c = new AtomicInteger(0);
        public static int getID() {
            return c.incrementAndGet();
        }
    }

    public static void sendNotification(Context ctx, String title, String message){
        int requestID = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = null;
        if(MainActivity.getInstance()!=null){
            Intent intent = new Intent(ctx, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent= PendingIntent.getActivity(ctx, requestID, intent, PendingIntent.FLAG_ONE_SHOT);
        }else{
            Intent intent = new Intent(ctx, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent= PendingIntent.getActivity(ctx, requestID, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_launcher);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder;
        NotificationManager notificationManager;

        //OREO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID = "bulkan_channel";
            String CHANNEL_TITLE = title;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_TITLE, importance);

            notificationBuilder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notif_logo)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setLargeIcon(bitmap)
                    .setContentIntent(pendingIntent)
                    .setColor(ContextCompat.getColor(ctx, R.color.primary_dark))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                    .setChannelId(CHANNEL_ID);

            notificationManager =
                    (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);

        }else{
            notificationBuilder = new NotificationCompat.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_notif_logo)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setLargeIcon(bitmap)
                    .setContentIntent(pendingIntent)
                    .setColor(ContextCompat.getColor(ctx, R.color.primary_dark))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

            notificationManager =
                    (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        int notifId = NotificationID.getID();
        notificationManager.notify(notifId /* ID of notification */, notificationBuilder.build());

    }
}
