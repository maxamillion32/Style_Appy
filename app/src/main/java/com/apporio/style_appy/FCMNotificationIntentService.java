package com.apporio.style_appy;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;



/**
 * Created by admin on 2/15/2016.
 */
public class FCMNotificationIntentService extends FirebaseMessagingService {

    public static final int notifyID = 9001;
    String Rideid;
    String r;
    Intent resultIntent;

    public static double driverlat,driverlang;

    public static String RideID,cartype_id,cityid,driverImage,DriverID,amount,dist,rtime,drnmae,address1,address2;



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e("notification -- ","" + remoteMessage.getNotification().getBody());

        // String msg=data.getString(""+GCMConstants.MSG_KEY);
        sendNotification(remoteMessage.getNotification().getBody()+"");





        super.onMessageReceived(remoteMessage);
    }





    public void sendNotification(String message) {


//        else {
//            resultIntent = new Intent(this, MyRidesActivity.class);
//        }  if (r.equals("Your Ride Completed !!")){
//            resultIntent = new Intent(this, BillActivity.class);
//        }


        Intent resultIntent = new Intent(this, SplashActivity.class);
        resultIntent.putExtra("msg", message);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_ONE_SHOT);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        NotificationCompat.Builder mNotifyBuilder;
        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Style Appy ")
                .setContentText("" + message)
                .setSmallIcon(R.drawable.lnp);

        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        mNotifyBuilder.setContentText(""+message);
        mNotifyBuilder.setAutoCancel(true);
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());

    }




}
