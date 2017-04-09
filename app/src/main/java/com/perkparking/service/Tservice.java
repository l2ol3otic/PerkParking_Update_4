package com.perkparking.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.perkparking.DBhelper;
import com.perkparking.R;
import com.perkparking.model.place2Cal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */

public class Tservice extends Service {

    public final static String SENT_TIME = "SENT_TIME";
    public static final String TAG = "MyServiceTag";
    Timer timer;
    public String time,time2;
    int hourDiff;
    int minuteDiff;
    int secondDiff;
    public String start,start2;
    private DBhelper mHelper;
    String out,m,h;
    private int ID = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
    @Override
    public void onCreate() {
        Log.i("Service : ", "Service Start");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm:ss");
        start2 = formatter.format(calendar.getTime());
        Log.i("Time", start2);
        Log.i("Service : ", "Service Start");
        final Timer T = new Timer();
        mHelper = new DBhelper(this);
        place2Cal mPlace2Call = mHelper.getresult("18");
        h = mPlace2Call.getPlace();
        m = mPlace2Call.getFloor();
        String times = m+h;
        Log.i("All",mPlace2Call.getFloor()+mPlace2Call.getPlace()+mPlace2Call.getTimeH()+mPlace2Call.getTimeM());
        Log.i("h+m",h+m);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm:ss a");
        try {
            Date date = dateFormat.parse(times);
            Log.e("Timeeeeeeeeeeeee", String.valueOf(date));
            out = dateFormat2.format(date);
            Log.e("Timeeeeeeeeeeeee", out);
        } catch (ParseException e) {

        }
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss a");
                time = formatter1.format(calendar1.getTime());
                //DateFormat df = new SimpleDateFormat("h:mm:ss");
                try {

                    DateFormat df = new SimpleDateFormat("h:mm:ss a");
                    Date start = df.parse(out);
                    Date end = df.parse(time);

                    long diff = end.getTime() - start.getTime();

                    //int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
                    hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
                    minuteDiff = (int) (diff / (60 * 1000) % 60);
                    secondDiff = (int) (diff / 1000 % 60);
                    if(10-secondDiff>0){
                        if(10-minuteDiff>0){
                            if(10-hourDiff>0){
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", "0"+String.valueOf(hourDiff)+":"+"0"+String.valueOf(minuteDiff)+":"+"0"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                            else{
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", String.valueOf(hourDiff)+":"+"0"+String.valueOf(minuteDiff)+":"+"0"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                        }
                        else  if(10-minuteDiff<0){
                            if(10-hourDiff>0){
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", "0"+String.valueOf(hourDiff)+":"+String.valueOf(minuteDiff)+":"+"0"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                            else{
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", String.valueOf(hourDiff)+":"+String.valueOf(minuteDiff)+":"+"0"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                        }
                    }
                    else{
                        if(10-minuteDiff>0){
                            if(10-hourDiff>0){
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", "0"+String.valueOf(hourDiff)+":"+"0"+String.valueOf(minuteDiff)+":"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                            else{
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", String.valueOf(hourDiff)+":"+"0"+String.valueOf(minuteDiff)+":"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                        }
                        else  if(10-minuteDiff<0){
                            if(10-hourDiff>0){
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", "0"+String.valueOf(hourDiff)+":"+String.valueOf(minuteDiff)+":"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                            else{
                                Intent intent = new Intent();
                                intent.setAction(SENT_TIME);
                                intent.putExtra("Time", String.valueOf(hourDiff)+":"+String.valueOf(minuteDiff)+":"+String.valueOf(secondDiff));
                                sendBroadcast(intent);
                            }
                        }
                    }


                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();

                                }

                            }

        }, 1000, 1000);

    }

    private void preDestroy() {
        this.stopService(new Intent(this, Tservice.class));
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service : ","Service Destroy");
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss");
        String stop = formatter1.format(calendar1.getTime());
        Log.i("Time",stop);


    }

    private void Difftime(String Tstart, String Tstop) {

        DateFormat df = new SimpleDateFormat("h:mm:ss");
        try {
            Date start = df.parse(Tstart);
            Date end = df.parse(Tstop);

            long diff = end.getTime() - start.getTime();

            //int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
            int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
            int minuteDiff = (int) (diff / (60 * 1000) % 60);
            int secondDiff = (int) (diff / 1000 % 60);

            //System.out.println("Day Diff = " + dayDiff);
            System.out.println("Hour Diff = " + hourDiff);
            System.out.println("Minute Diff = " + minuteDiff);
            System.out.println("Second Diff = " + secondDiff);



        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void shownotification() {
        Notification notification =
                new NotificationCompat.Builder(this) // this is context
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("PerkParking")
                        .setContentText("จอดดรถเกิน 1 ชั่วโมง")
                        .setAutoCancel(true)
                        .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);


    }

}
