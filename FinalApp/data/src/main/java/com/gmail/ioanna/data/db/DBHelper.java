package com.gmail.ioanna.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "newTasks";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE newTasks ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'name' TEXT, " +
                "'percentOfCompletion' INTEGER, 'state' TEXT, 'estimatedTime' INTEGER, " +
                "'startDate' TEXT, 'dueDate' TEXT)");
        Log.e("DBHelper", "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("DBHelper", "onUpgrade()");
    }
}
