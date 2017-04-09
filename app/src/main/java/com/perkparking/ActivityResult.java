package com.perkparking;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perkparking.model.place2Cal;
import com.perkparking.service.Tservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by it02 on 4/4/2560.
 */

public class ActivityResult extends Activity {
    EditText timeetx;
    MyReceiver myReceiver;
    String out;
    int hourDiff;
    int minuteDiff;
    int secondDiff;
    Timer T;
    String time,m,h;
    private DBhelper mHelper;
    private int ID = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        EditText flooretx = (EditText)findViewById(R.id.flooretx);
        flooretx.setEnabled(false);
        EditText placeetx = (EditText)findViewById(R.id.placeetx);
        placeetx.setEnabled(false);
        timeetx = (EditText)findViewById(R.id.timeetx);
        timeetx.setEnabled(false);
        Button bt = (Button)findViewById(R.id.resultbt);
        mHelper = new DBhelper(this);
        place2Cal mPlace2Call = mHelper.getresult("18");
        flooretx.setText(mPlace2Call.getTimeH());
        placeetx.setText(mPlace2Call.getTimeM());
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Tservice.SENT_TIME);
        registerReceiver(myReceiver, intentFilter);

    }

    private void processStopService(final String tag) {
        Intent intent = new Intent(getApplicationContext(), Tservice.class);
        intent.addCategory(tag);
        stopService(intent);
    }
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            String x = "0";
            final String time16 = arg1.getStringExtra("Time");
            Log.i("Sec",time16);
            timeetx.setText(time16);

        }

    }

}
