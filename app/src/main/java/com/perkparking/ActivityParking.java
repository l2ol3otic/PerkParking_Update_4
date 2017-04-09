package com.perkparking;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.perkparking.model.place2Cal;
import com.perkparking.service.Tservice;

public class ActivityParking extends AppCompatActivity {

    TimePicker timeParking;
    private DBhelper mHelper;

    private int ID = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        timeParking = (TimePicker) findViewById(R.id.timeParking);
        String[] floor = {"B3","B2","B1","M","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"}; //etc
        final NumberPicker floornb = (NumberPicker)findViewById(R.id.floornb);
        floornb.setMaxValue(0);
        floornb.setMaxValue(53);
        floornb.setDisplayedValues(floor);

        final NumberPicker alphabetfloor1 = (NumberPicker) findViewById(R.id.alphabetFloor1);
        String[] alphabet1 = {" ","½"}; //etc
        alphabetfloor1.setMaxValue(0);
        alphabetfloor1.setMaxValue(1);
        alphabetfloor1.setDisplayedValues(alphabet1);

        final NumberPicker alphabetfloor2 = (NumberPicker) findViewById(R.id.alphabetFloor2);
        String[] alphabet2 = {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; //etc
        alphabetfloor2.setMaxValue(0);
        alphabetfloor2.setMaxValue(26);
        alphabetfloor2.setDisplayedValues(alphabet2);

        final NumberPicker placenb = (NumberPicker)findViewById(R.id.placenb);
        placenb.setMaxValue(100);
        placenb.setMinValue(0);
        Button bt = (Button)findViewById(R.id.bt);

        final NumberPicker alphabetplace = (NumberPicker) findViewById(R.id.alphabetPlace);
        String[] alphabet3 = {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; //etc
        alphabetplace.setMaxValue(0);
        alphabetplace.setMaxValue(26);
        alphabetplace.setDisplayedValues(alphabet3);

        timeParking.setIs24HourView(true);
        mHelper = new DBhelper(this);

        bt.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int a = timeParking.getHour();
                int b = timeParking.getMinute();
                int c = floornb.getValue();
                int d = placenb.getValue();
                Log.e("a ",String.valueOf(a));
                Log.e("b ",String.valueOf(b));
                Log.e("c ",String.valueOf(c));
                Log.e("d ",String.valueOf(d));

                place2Cal place2 = new place2Cal();
                if( a-10 < 0){
                    place2.setTimeH(String.valueOf("0"+a));
                }else{
                    place2.setTimeH(String.valueOf(a));
                }
                if( b-10 < 0){
                    place2.setTimeM(String.valueOf("0"+b));
                }else{
                    place2.setTimeM(String.valueOf(b));
                }

                place2.setFloor(String.valueOf(c));
                place2.setPlace(String.valueOf(d));
                if (ID == -1) {
                    mHelper.addPlace(place2);
                } else {
                    place2.setId(ID);
                    //mHelper.updateFriend(friend);
                }
                processStartService(Tservice.TAG);
                startActivity(new Intent(ActivityParking.this, ActivityResult.class));
            }
        });

    }
    private void processStartService(final String tag) {
        Intent intent = new Intent(getApplicationContext(), Tservice.class);
        intent.addCategory(tag);
        startService(intent);
    }
}
