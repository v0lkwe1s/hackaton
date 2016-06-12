package br.com.hackaton.up.gcm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import br.com.hackaton.up.R;
import br.com.hackaton.up.activity.AcompanharActivity;

public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler handler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
        handler = new Handler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String title = extras.getString("title");

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent notificationIntent = new Intent(getApplicationContext(), AcompanharActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),0,notificationIntent,0);

        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.ic_menu_follow_white);
        builder.setContentTitle("Up Pato Branco");
        builder.setContentText(title);
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);

        Notification notification = builder.build();
        nm.notify((int) System.currentTimeMillis(), notification);

        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
