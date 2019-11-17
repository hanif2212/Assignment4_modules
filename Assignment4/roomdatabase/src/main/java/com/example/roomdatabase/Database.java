package com.example.roomdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    private CreateTable_places_visits places_visits_helper;
    private CreateTable_location_tracking location_tracking_helper;
    private CreateTable_screentime_used screenTime_used_helper;
    public Database(Context context)
    {
        places_visits_helper = new CreateTable_places_visits(context);
        location_tracking_helper = new CreateTable_location_tracking(context);
        screenTime_used_helper =  new CreateTable_screentime_used(context);
    }

    public long insert_places_visits(String places_name, String date,String time,String checkIn,String customMessage)
    {
        SQLiteDatabase dbb = places_visits_helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(places_visits_helper.PLACE_NAME, places_name);
        contentValues.put(places_visits_helper.DATE, date);
        contentValues.put(places_visits_helper.TIME, time);
        contentValues.put(places_visits_helper.CHECKIN_CHECKOUT, checkIn);
        contentValues.put(places_visits_helper.CUSTOM_MESSAGE, customMessage);
        long id = dbb.insert(places_visits_helper.PLACES_VISITS_TABLE, null , contentValues);
        return id;
    }

    public long insert_location_tracking(double latitude, double longitude, String date, String time, float accuracy)
    {
        SQLiteDatabase dbb = location_tracking_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(location_tracking_helper.LATITUDE, latitude);
        contentValues.put(location_tracking_helper.LONGITUDE, longitude);
        contentValues.put(location_tracking_helper.DATE, date);
        contentValues.put(location_tracking_helper.TIME, time);
        contentValues.put(location_tracking_helper.ACCURACY, accuracy);
        long id = dbb.insert(location_tracking_helper.LOCATION_TRACKING_TABLE, null , contentValues);
        return id;
    }
    public long insert_screenTime_used(String screen_event, int count, String date, String time)
    {
        SQLiteDatabase dbb = screenTime_used_helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(screenTime_used_helper.SCREEN_EVENT, screen_event);
        contentValues.put(screenTime_used_helper.COUNT, count);
        contentValues.put(screenTime_used_helper.DATE, date);
        contentValues.put(screenTime_used_helper.TIME, time);

        long id = dbb.insert(screenTime_used_helper.SCREENTIME_USED_TABLE, null , contentValues);
        return id;
    }
    public String get_places_visits()
    {
        SQLiteDatabase db = places_visits_helper.getWritableDatabase();
        String[] columns = {CreateTable_places_visits.PLACE_ID, CreateTable_places_visits.PLACE_NAME, CreateTable_places_visits.DATE, CreateTable_places_visits.TIME, CreateTable_places_visits.CHECKIN_CHECKOUT, CreateTable_places_visits.CUSTOM_MESSAGE};
        Cursor cursor =db.query(CreateTable_places_visits.PLACES_VISITS_TABLE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(CreateTable_places_visits.PLACE_ID));
            String name =cursor.getString(cursor.getColumnIndex(CreateTable_places_visits.PLACE_NAME));
            String  password =cursor.getString(cursor.getColumnIndex(CreateTable_places_visits.DATE));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }
    public String get_location_tracking()
    {
        SQLiteDatabase db = location_tracking_helper.getWritableDatabase();
        //db.execSQL(CreateLocationTrackingTable.DROP_location_trackingTABLE);
        //db.execSQL(CreateLocationTrackingTable.CREATE_location_trackingTABLE);
        String[] columns = {CreateTable_location_tracking.LOCATION_ID, CreateTable_location_tracking.LATITUDE, CreateTable_location_tracking.LONGITUDE, CreateTable_location_tracking.DATE, CreateTable_location_tracking.TIME, CreateTable_location_tracking.ACCURACY};
        Cursor cursor =db.query(CreateTable_location_tracking.LOCATION_TRACKING_TABLE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        if (cursor.getCount()!=0) {
            cursor.moveToLast();
            float latitude = cursor.getFloat(cursor.getColumnIndex(CreateTable_location_tracking.LATITUDE));
            float longitude = cursor.getFloat(cursor.getColumnIndex(CreateTable_location_tracking.LONGITUDE));
            float accuracy = cursor.getFloat(cursor.getColumnIndex(CreateTable_location_tracking.ACCURACY));
            buffer.append(latitude + "  \n " + longitude + " \n  " + accuracy + " \n");
        }
        return buffer.toString();
    }
    public String get_screenTime_used()
    {
        SQLiteDatabase db = screenTime_used_helper.getWritableDatabase();
        //db.execSQL(CreateScreenTimeUsedTable.DROP_screentime_usedTABLE);
        //db.execSQL(CreateScreenTimeUsedTable.CREATE_screentime_usedTABLE);
        String[] columns = {CreateTable_screentime_used.SCREEN_EVENT, CreateTable_screentime_used.COUNT, CreateTable_screentime_used.DATE, CreateTable_screentime_used.TIME};
        Cursor cursor =db.query(CreateTable_screentime_used.SCREENTIME_USED_TABLE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String event =cursor.getString(cursor.getColumnIndex(CreateTable_screentime_used.SCREEN_EVENT));
            String Count =cursor.getString(cursor.getColumnIndex(CreateTable_screentime_used.COUNT));
            String  Date =cursor.getString(cursor.getColumnIndex(CreateTable_screentime_used.DATE));
            String  Time =cursor.getString(cursor.getColumnIndex(CreateTable_screentime_used.TIME));
            buffer.append(event+ "   " + Count + "   " + Date +"   "+Time+"\n");

        }

        return buffer.toString();
    }

    static class CreateTable_places_visits extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Database";    // Database Name

        private static final String PLACES_VISITS_TABLE = "places_visits";   // Table Name
        private static final int DATABASE_VERSION = 1;    // Database Version
        private static final String PLACE_ID = "id";     // Column I (Primary Key)
        private static final String PLACE_NAME = "place_name";    //Column II
        private static final String DATE = "date";    // Column III
        private static final String TIME = "time";    // Column III
        private static final String CHECKIN_CHECKOUT = "checkin_checkout";    // Column IV
        private static final String CUSTOM_MESSAGE = "custom_message";    // Column V
        private static final String CREATE_PLACE_VISITS_TABLE = "CREATE TABLE " + PLACES_VISITS_TABLE +
                " (" + PLACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLACE_NAME + " VARCHAR(50) ," + DATE + " VARCHAR(20), " + TIME + " VARCHAR(20)," + CHECKIN_CHECKOUT + " VARCHAR(50)," + CUSTOM_MESSAGE + " VARCHAR(100));";
        private static final String DROP_place_visitsTABLE = "DROP TABLE IF EXISTS " + PLACES_VISITS_TABLE;




        private Context context;

        public CreateTable_places_visits(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_PLACE_VISITS_TABLE);
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    static class CreateTable_location_tracking extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Database";    // Database Name
        private static final int DATABASE_VERSION = 1;    // Database Version
        private static final String LOCATION_TRACKING_TABLE = "location_tracking";   // Table Name
        private static final String LOCATION_ID = "id";     // Column I (Primary Key)
        private static final String LATITUDE = "latitude";    //Column II
        private static final String LONGITUDE = "longitude";    // Column III
        private static final String DATE = "date";    // Column III
        private static final String TIME = "time";    // Column III
        private static final String ACCURACY = "accuracy";    // Column IV
        private static final String CREATE_LOCATION_TRACKING_TABLE = "CREATE TABLE " + LOCATION_TRACKING_TABLE +
                " (" + LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LATITUDE + " FLOAT ," + LONGITUDE + " FLOAT, " + DATE + " VARCHAR(20)," + TIME + " VARCHAR(20)," + ACCURACY + " FLOAT);";
        private static final String DROP_location_trackingTABLE = "DROP TABLE IF EXISTS " + LOCATION_TRACKING_TABLE;

        private Context context;

        public CreateTable_location_tracking(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_LOCATION_TRACKING_TABLE);
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    static class CreateTable_screentime_used extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Database";    // Database Name
        private static final int DATABASE_VERSION = 1;    // Database Version
        private static final String SCREENTIME_USED_TABLE = "screentime_used";   // Table Name
        private static final String SCREEN_ID = "id";     // Column I (Primary Key)
        private static final String SCREEN_EVENT = "screen_event";    //Column II
        private static final String DATE = "date";    // Column III
        private static final String TIME = "time";    // Column III
        private static final String COUNT = "count";    // Column III
        private static final String CREATE_SCREENTIME_USED_TABLE = "CREATE TABLE " + SCREENTIME_USED_TABLE +
                " (" + SCREEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SCREEN_EVENT + " BOOLEAN ," + COUNT + " INTEGER, " + DATE + " VARCHAR(20)," + TIME + " VARCHAR(20));";
        private static final String DROP_screentime_usedTABLE = "DROP TABLE IF EXISTS " + SCREENTIME_USED_TABLE;


        private Context context;

        public CreateTable_screentime_used(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_SCREENTIME_USED_TABLE);
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}

