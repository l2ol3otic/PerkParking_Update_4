package com.perkparking;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.perkparking.model.place2Cal;



public class DBhelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public DBhelper(Context context) {
        super(context, place2Cal.DATABASE_NAME, null, place2Cal.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_PLACE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                place2Cal.TABLE,
                place2Cal.Column.ID,
                place2Cal.Column.timeH,
                place2Cal.Column.timeM,
                place2Cal.Column.floor,
                place2Cal.Column.place);


        Log.i(TAG, CREATE_PLACE_TABLE);

        // create place table
        sqLiteDatabase.execSQL(CREATE_PLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_PLACE_TABLE = "DROP TABLE IF EXISTS " + place2Cal.TABLE;

        sqLiteDatabase.execSQL(DROP_PLACE_TABLE);

        Log.i(TAG, "Upgrade Database from " + i + " to " + i1);

        onCreate(sqLiteDatabase);
    }

    public void addPlace(place2Cal place2){

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(place2Cal.Column.timeH, place2.getTimeH());
        values.put(place2Cal.Column.timeM, place2.getTimeM());
        values.put(place2Cal.Column.floor, place2.getFloor());
        values.put(place2Cal.Column.place, place2.getPlace());



        sqLiteDatabase.insert(place2Cal.TABLE, null, values);


        sqLiteDatabase.close();
    }
    public place2Cal getresult(String id) {

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor2 = sqLiteDatabase.query( place2Cal.TABLE,
                null,
                null,
                null,
                null,
                null,
                null,
                null);


        if (cursor2 != null) {
            cursor2.moveToFirst();
        }
        int coubnt = cursor2.getCount();
        Log.i("Data Count", String.valueOf(cursor2.getCount()));
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query( place2Cal.TABLE,
                null,
                place2Cal.Column.ID + " = ? ",
                new String[] {String.valueOf(coubnt)},
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        place2Cal place2CalResult = new place2Cal();

        place2CalResult.setId((int) cursor.getLong(0));
        place2CalResult.setFloor(cursor.getString(1));
        place2CalResult.setPlace(cursor.getString(2));
        place2CalResult.setTimeH(cursor.getString(3));
        place2CalResult.setTimeM(cursor.getString(4));


        return place2CalResult;
    }
}
