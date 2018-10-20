package com.team.bulkan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.team.bulkan.model.Volcano;

import java.util.ArrayList;
import java.util.List;

public class DBAccess extends DBWrapper{

    public DBAccess(Context context) {
        super(context);
    }

    /**
     * STORE VOLCANOES
     */
    public long storeVolcano(Volcano item){
        ContentValues values = new ContentValues();
        values.put(Volcano.KEY_VOLCANO_ID, item.getVolcanoId());
        values.put(Volcano.KEY_VOLCANO_NAME, item.getVolcanoName());
        values.put(Volcano.KEY_VOLCANO_INFO, item.getVolcanoInfo());
        values.put(Volcano.KEY_GEO_LAT, item.getGeoLat());
        values.put(Volcano.KEY_GEO_LONG, item.getGeoLong());
        values.put(Volcano.KEY_VOLCANO_IMAGE, item.getVolcanoImage());
        long stored = database.insert(DBHelper.TBL_VOLCANOES,null, values);
        System.out.println("STORED: " + item.getVolcanoId() +"~"+ item.getVolcanoName());
        return stored;
    }

    public List<Volcano> getVolcanoes(){
        List items = new ArrayList();
        String RAW_QUERY = "SELECT id, volcano_id, volcano_name, volcano_info, geo_lat, geo_long, image FROM " + DBHelper.TBL_VOLCANOES;

        try{
            Cursor cursor = database.rawQuery(RAW_QUERY, null);
            while (cursor.moveToNext()) {
                Volcano retItem = new Volcano(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getString(6));
                items.add(retItem);
            }
        }catch (SQLException e){
            //EAT IT OR NOT
            e.printStackTrace();
        }

        return items;
    }
}
