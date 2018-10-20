package com.team.bulkan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.bulkan.activity.HazardsActivity;
import com.team.bulkan.activity.PrepareActivity;
import com.team.bulkan.font.HelveticaTextView;
import com.team.bulkan.font.RobotoTextView;
import com.team.bulkan.fragments.HomeFragment;
import com.team.bulkan.fragments.TypographyFragment;
import com.team.bulkan.helpers.CustomFragmentManager;
import com.team.bulkan.navigation.DrawerAdapter;

public class MainActivity extends AppCompatActivity {

    public static String LOG_TAG = MainActivity.class.getSimpleName();
    public static CustomFragmentManager _fragmentManager;
    private static MainActivity _instance;

    private static Toolbar toolbar;
    private LinearLayout llDrawerContent;
    private ListView drawerListView;
    private static DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence actionBarTitle;

    private DrawerAdapter drawerAdapter;
    private float _lastTranslate = 0.0f; //Variable to hold translation value when page is animating when drawer is opened

    public MainActivity() {
        super();
        MainActivity._instance = this;
    }

    public static MainActivity getInstance() {
        return MainActivity._instance;
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        actionBarTitle = title;
        getSupportActionBar().setTitle(actionBarTitle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bulkan");

        llDrawerContent = (LinearLayout) findViewById(R.id.drawer_content);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarTitle = drawerTitle = getTitle();
        drawerListView = (ListView) findViewById(R.id.lv_drawer_items);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        View navHeader = getLayoutInflater().inflate(R.layout.nav_header, drawerListView, false);
        HelveticaTextView tvUsername = (HelveticaTextView)  navHeader.findViewById(R.id.tv_username);
        ImageView imageProfile = (ImageView) navHeader.findViewById(R.id.navImage);

        /**
         * For Pre Kitkat, Add header before adapter
         */
        drawerListView.addHeaderView(navHeader);
        drawerAdapter = new DrawerAdapter(this);
        drawerListView.setAdapter(drawerAdapter);

        drawerListView.setOnItemClickListener(new NavigationDrawerItemClickListener());
        //drawerListView.setBackgroundResource(R.drawable.background_media);
        drawerListView.getLayoutParams().width = (int) getResources().getDimension(R.dimen.drawer_width_media);

        final LinearLayout page = (LinearLayout) findViewById(R.id.content_page);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(actionBarTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }

            @SuppressLint("NewApi")
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                /*float moveFactor = (drawerListView.getWidth() * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    page.setTranslationX(moveFactor);
                } else {
                    TranslateAnimation anim = new TranslateAnimation(_lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    page.startAnimation(anim);

                    _lastTranslate = moveFactor;
                }*/
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setHomeAsUpIndicator(R.drawable.icon_hamburger);
        drawerToggle.setToolbarNavigationClickListener(_defaultToggleToolbarNavigationClickListener);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        _fragmentManager = new CustomFragmentManager(this, R.id.view_container);
        _fragmentManager.switchTo(HomeFragment.class, null);

    }


    /**
     * Navigation Drawer Listener
     */
    private final int DRAWER_PREPAREDNESS = 0;
    private final int DRAWER_HAZARD = 1;
    private final int DRAWER_GET_HELP = 2;
    private final int DRAWER_SEND_FEEDBACK = 3;
    private class NavigationDrawerItemClickListener implements
            ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //RESET BACKGROUND COLORS
            for(int x=0;x<drawerListView.getCount();x++){
                if(drawerListView.getChildAt(x)!=null){
                    drawerListView.getChildAt(x).setBackgroundColor(Color.TRANSPARENT);
                }

            }
            switch (position){
                case 1:
                    Intent p = new Intent(MainActivity.this, PrepareActivity.class);
                    p.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(p);
                    setSelected(position,DRAWER_PREPAREDNESS);
                    break;
                case 2:
                    Intent h = new Intent(MainActivity.this, HazardsActivity.class);
                    h.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(h);
                    setSelected(position,DRAWER_HAZARD);
                    break;
                case 3:
                    Toast.makeText(MainActivity.this, "GET HELP", Toast.LENGTH_SHORT).show();
                    setSelected(position,DRAWER_GET_HELP);
                    break;
                case 4:
                    Toast.makeText(MainActivity.this, "SEND FEEDBACK", Toast.LENGTH_SHORT).show();
                    setSelected(position,DRAWER_SEND_FEEDBACK);
                    break;
                default:
                    toggleNavigation();
                    break;
            }

        }
    }

    /**
     * Avoid Lag on Closing the drawer
     * @param position
     */
    private void setSelected(final int position, final int drawerTitle){

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                setTitle(drawerAdapter.getTitle(drawerTitle));
                drawerListView.setItemChecked(position,true);
                //drawerLayout.closeDrawer(drawerListView);
                drawerLayout.closeDrawer(llDrawerContent);
            }
        });

    }

    /**
     * Toggles the navigation drawer.
     */
    public void toggleNavigation() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeNavigation();
        } else {
            openNavigation();
        }
    }

    /**
     * Opens the navigation drawer.
     */
    public void openNavigation() {
        //drawerLayout.openDrawer(drawerListView);
        drawerLayout.openDrawer(llDrawerContent);
    }

    /**
     * Closes the navigation drawer.
     */
    public void closeNavigation() {
        //drawerLayout.closeDrawer(drawerListView);
        drawerLayout.closeDrawer(llDrawerContent);
    }

    /**
     * Toggle Listener / Icon Hamburger action
     */
    private View.OnClickListener _defaultToggleToolbarNavigationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*if (drawerLayout.isDrawerVisible(drawerListView)) {
                drawerLayout.closeDrawer(drawerListView);
            } else {
                drawerLayout.openDrawer(drawerListView);
            }*/
            if (drawerLayout.isDrawerVisible(llDrawerContent)) {
                drawerLayout.closeDrawer(llDrawerContent);
            } else {
                drawerLayout.openDrawer(llDrawerContent);
            }
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private boolean _doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (_fragmentManager.getFragmentManager().getFragments().size() > 0) {
            if (_doubleBackToExitPressedOnce) {
                finish();
            }

            this._doubleBackToExitPressedOnce = true;
            toggleNavigation();
            Toast.makeText(this, "Double press BACK to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    _doubleBackToExitPressedOnce = false;
                }
            }, 500);
        }
    }

}
