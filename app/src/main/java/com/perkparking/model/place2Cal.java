package com.perkparking.model;

import android.provider.BaseColumns;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */

public class place2Cal {

    //Database
    public static final String DATABASE_NAME = "parking_place.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE = "place2Cal";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String timeH = "timeH";
        public static final String timeM = "timeM";
        public static final String floor = "floor";
        public static final String place = "place";
    }

    private int id;
    private String timeH;
    private String timeM;
    private String floor;
    private String place;

    public place2Cal(){

    }
    //Constructor
    public place2Cal(int id, String timeH, String timeM, String floor, String place) {

        this.id = id;
        this.timeH = timeH;
        this.timeM = timeM;
        this.floor = floor;
        this.place = place;
    }

    //Getter, Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTimeH() {
        return timeH;
    }

    public void setTimeH(String timeH) {
        this.timeH = timeH;
    }
    public String getTimeM() {
        return timeM;
    }

    public void setTimeM(String timeM) {
        this.timeM = timeM;
    }
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
