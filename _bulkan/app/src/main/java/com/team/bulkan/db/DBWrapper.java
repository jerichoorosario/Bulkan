package com.team.bulkan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBWrapper {

    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public DBWrapper(Context context){
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();
    }

    public void open(){
        if(dbHelper==null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
}
