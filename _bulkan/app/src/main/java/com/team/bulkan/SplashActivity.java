package com.team.bulkan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.team.bulkan.model.Volcano;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private View decorView;
    private ImageView splashLogo;
    private boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashLogo = findViewById(R.id.logo);
        initAnimation();
    }

    List<Volcano> volcanoList;
    private void loadVolcanoes(){
        volcanoList = new ArrayList<>();
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return readJson();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try{
                    JSONObject jObj = new JSONObject(result);
                    JSONArray data = jObj.getJSONArray("data");
                    for(int x = 0; x < data.length(); x++){
                        JSONObject dObj = data.getJSONObject(x);
                        int volcano_id = dObj.getInt(Volcano.KEY_ID);
                        String volcano_name = dObj.getString(Volcano.KEY_VOLCANO_NAME);
                        String volcano_info = dObj.getString(Volcano.KEY_VOLCANO_INFO);
                        double geo_lat = dObj.getDouble(Volcano.KEY_GEO_LAT);
                        double geo_long =dObj.getDouble(Volcano.KEY_GEO_LONG);

                        String volcano_image = "";
                        if(!dObj.isNull(dObj.getString(Volcano.KEY_VOLCANO_IMAGE))){
                            volcano_image = dObj.getString(Volcano.KEY_VOLCANO_IMAGE);
                        }

                        Volcano v = new Volcano(x, volcano_id, volcano_name, volcano_info, geo_lat, geo_long, volcano_image);
                        volcanoList.add(v);
                    }

                    for(Volcano v: volcanoList){
                        AppController.getInstance().getDbAccess().storeVolcano(v);
                    }
                    success = true;
                }catch(Exception e){
                    //EAT
                    e.printStackTrace();
                }

                if(success){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                    },3000);
                }
            }
        }.execute();
    }

    public String readJson() {
        String json = null;
        try{
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(getAssets().open(
                        "volcanoes.json")));
                String temp;
                while ((temp = br.readLine()) != null)
                    sb.append(temp);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close(); // stop reading
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            json = sb.toString();
            //System.out.println("JSON: " + json);
        }catch (Exception e){

        }
        return json;
    }

    private void initAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(splashLogo, "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(splashLogo, "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(splashLogo, "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        alphaAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                List<Volcano> retVolcanoes = AppController.getInstance().getDbAccess().getVolcanoes();
                if(retVolcanoes.size()>0){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }else{
                    loadVolcanoes();
                }

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        decorView = getWindow().getDecorView();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (hasFocus) {
                decorView
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}
