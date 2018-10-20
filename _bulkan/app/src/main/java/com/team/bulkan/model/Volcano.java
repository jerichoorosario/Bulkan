package com.team.bulkan.model;

public class Volcano {

    public static final String KEY_ID = "id";
    public static final String KEY_VOLCANO_ID = "volcano_id";
    public static final String KEY_VOLCANO_NAME = "volcano_name";
    public static final String KEY_VOLCANO_INFO = "volcano_info";
    public static final String KEY_GEO_LAT = "geo_lat";
    public static final String KEY_GEO_LONG = "geo_long";
    public static final String KEY_VOLCANO_IMAGE = "image";

    private int id;
    private int volcano_id;
    private String volcano_name;
    private String volcano_info;
    private double geo_lat;
    private double geo_long;
    private String volcano_image;

    public Volcano(){}

    public Volcano(int id, int volcano_id, String volcano_name, String volcano_info, double geo_lat, double geo_long, String volcano_image){
        this.id = id;
        this.volcano_id = volcano_id;
        this.volcano_name = volcano_name;
        this.volcano_info = volcano_info;
        this.geo_lat = geo_lat;
        this.geo_long = geo_long;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolcanoId() {
        return volcano_id;
    }

    public void setVolcanoId(int volcano_id) {
        this.volcano_id = volcano_id;
    }

    public String getVolcanoName() {
        return volcano_name;
    }

    public void setVolcanoName(String volcano_name) {
        this.volcano_name = volcano_name;
    }

    public String getVolcanoInfo() {
        return volcano_info;
    }

    public void setVolcanoInfo(String volcano_info) {
        this.volcano_info = volcano_info;
    }

    public double getGeoLat() {
        return geo_lat;
    }

    public void setGeoLat(double geo_lat) {
        this.geo_lat = geo_lat;
    }

    public double getGeoLong() {
        return geo_long;
    }

    public void setGeoLong(double geo_long) {
        this.geo_long = geo_long;
    }

    public String getVolcanoImage() {
        return volcano_image;
    }

    public void setVolcanoImage(String volcano_image) {
        this.volcano_image = volcano_image;
    }
}
