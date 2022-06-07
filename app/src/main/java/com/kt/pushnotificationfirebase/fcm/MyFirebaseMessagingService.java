package com.kt.pushnotificationfirebase.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kt.pushnotificationfirebase.MainActivity;
import com.kt.pushnotificationfirebase.MyApplication;
import com.kt.pushnotificationfirebase.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        //Nhận data
        RemoteMessage.Notification notification = message.getNotification();    //get data từ message
        Log.d("day","a");
        if (notification == null){
            return;
        }
        String strTitle = notification.getTitle();     //title của push message
        String strMessage = notification.getBody(); //nội dung của push message
        //send firebase
        Log.d("message1",strMessage);

        sendNotification(strTitle,strMessage);
    }

    private void sendNotification(String strTitle, String strMessage) {
        //
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.
                Builder(this, MyApplication.CHANNEL_ID)    //Builder : nhận context và id
                .setContentTitle(strTitle)
                .setContentText(strMessage)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            notificationManager.notify(1,notification);
        }
    }
}
