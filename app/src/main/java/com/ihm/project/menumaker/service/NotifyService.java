package com.ihm.project.menumaker.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ihm.project.menumaker.R;

public class NotifyService extends Service {
    public static final String CHANNEL_ID = "channel";
    public static final int NOTIFICATION_ID = 888888;
    final static String ACTION = "registerReceiver";

    final static String SERVICE_BROADCAST_KEY = "ingToBuyListService";
    final static int RQS_STOP_SERVICE = 1;
    final static int RQS_SEND_SERVICE = 2;
    NotifyServiceReceiver notifyServiceReceiver;

    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, ACTION, importance);
            channel.setDescription("Poulet");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();
    }

    private final Binder binder=new LocalBinder();

    public class LocalBinder extends Binder {
        /**
         * @return the service you want to bind to : i.e. this
         */
        NotifyService getService() {
            return (NotifyService.this);
        }
    }

    @Override
    public void onDestroy() {

        this.unregisterReceiver(notifyServiceReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }

    public void sendNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.fromage)
                .setContentTitle("Test")
                .setContentText("this is a test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    class NotifyServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            int rqs = intent.getIntExtra(SERVICE_BROADCAST_KEY, 0);
            if (rqs == RQS_STOP_SERVICE) {
                stopSelf();
            }
            if(rqs == RQS_SEND_SERVICE) {
                sendNotification();
            }
        }
    }

}
