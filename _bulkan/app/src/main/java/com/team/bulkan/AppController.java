package com.team.bulkan;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController appControllerInstance;
    private static OkHttpClient okHttpClient;

    private static DatabaseReference databaseReference;

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

        FirebaseApp.initializeApp(this);
        //Enable Offline capabilities
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //Get DB Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Keep Data fresh
        databaseReference.keepSynced(true);

    }

    public static DatabaseReference getFBDatabaseReference(){
        return databaseReference;
    }

    public OkHttpClient getOkHttpClient(){
        return  okHttpClient;
    }

}
