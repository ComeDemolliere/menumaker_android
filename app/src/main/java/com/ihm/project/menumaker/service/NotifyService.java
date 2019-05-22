package com.ihm.project.menumaker.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.ihm.project.menumaker.R;

public class NotifyService extends Service {
    public static final String CHANNEL_ID = "channel";
    public static final int NOTIFICATION_ID = 888888;
    final static String ACTION = "registerReceiver";

    final static String SERVICE_BROADCAST_KEY = "ingToBuyListService";
    final static int RQS_STOP_SERVICE = 1;
    final static int RQS_SEND_SERVICE = 2;
    final static int RQS_SEND_TOAST = 3;
    private NotifyServiceReceiver notifyServiceReceiver;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, ACTION, importance);
            channel.setDescription("Poulet");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this

            notificationManager.createNotificationChannel(channel);
        }

        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(intent.getAction());
        registerReceiver(notifyServiceReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);

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
                .setSmallIcon(R.drawable.left_arrow)
                .setContentTitle("Test")
                .setContentText("this is a test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public void sendToast(String s){
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }


    class NotifyServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            int rqs = intent.getIntExtra(SERVICE_BROADCAST_KEY, 0);
            if (rqs == RQS_STOP_SERVICE) {
                stopSelf();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.deleteNotificationChannel(CHANNEL_ID);
                }

            }
            if(rqs == RQS_SEND_SERVICE) {
                sendNotification();
            }
            if(rqs == RQS_SEND_TOAST) {
                sendToast(intent.getStringExtra("ToastContent"));
            }
        }
    }

}
