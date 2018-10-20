package com.team.bulkan;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController appControllerInstance;
    private static OkHttpClient okHttpClient;

    public static synchronized AppController getInstance() {
        return appControllerInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appControllerInstance = this;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient(){
        return  okHttpClient;
    }

}
