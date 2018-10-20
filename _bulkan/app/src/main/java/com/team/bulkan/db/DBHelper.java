package com.team.bulkan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.team.bulkan.model.Volcano;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "_bulkan";
    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LAWYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_LAWYER_TABLE);
        this.onCreate(db);
    }

    static String TBL_VOLCANOES = "tbl_volcanoes";

    static String CREATE_LAWYER_TABLE=String.format("CREATE TABLE %s " +
                    "(%s TEXT PRIMARY KEY" +
                    ",%s TEXT" +
                    ",%s TEXT" +
                    ",%s TEXT" +
                    ",%s TEXT" +
                    ",%s TEXT" +
                    ",%s TEXT)"

            ,TBL_VOLCANOES
            ,Volcano.KEY_ID
            ,Volcano.KEY_VOLCANO_ID
            ,Volcano.KEY_VOLCANO_NAME
            ,Volcano.KEY_VOLCANO_INFO
            ,Volcano.KEY_GEO_LAT
            ,Volcano.KEY_GEO_LONG
            ,Volcano.KEY_VOLCANO_IMAGE);


}
