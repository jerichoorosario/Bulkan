package com.team.bulkan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.team.bulkan.R;
import com.team.bulkan.font.MaterialDesignIconsTextView;
import com.team.bulkan.fragments.AfterFragment;
import com.team.bulkan.fragments.BeforeFragment;
import com.team.bulkan.fragments.DuringFragment;

public class PrepareActivity extends AppCompatActivity {

    private WizardPagerAdapter wizardPagerAdapter;
    private ViewPager pager;
    //private TextView previousButton;
    private MaterialDesignIconsTextView nextButton;
    private MaterialDesignIconsTextView navigator;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        //PrefManager.getInstance(WizardActivity.this).setIsFirstTimeLaunch(PrefManager.IS_FIRST_TIME_LAUNCH, false);

        currentItem = 0;

        pager = (ViewPager) findViewById(R.id.activity_wizard_universal_pager);
        //previousButton = (TextView) findViewById(R.id.activity_wizard_universal_previous);
        nextButton = (MaterialDesignIconsTextView) findViewById(R.id.activity_wizard_universal_next);
        navigator = (MaterialDesignIconsTextView) findViewById(R.id.activity_wizard_universal_possition);

        wizardPagerAdapter = new WizardPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(wizardPagerAdapter);
        pager.setCurrentItem(currentItem);

        setNavigator();

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub
                setNavigator();
            }
        });

        /*previousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });*/

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (pager.getCurrentItem() != (pager.getAdapter().getCount() - 1)) {
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                } else {

                }
                setNavigator();
            }
        });

    }

    public void setNavigator() {
        String navigation = "";
        for (int i = 0; i < wizardPagerAdapter.getCount(); i++) {
            if (i == pager.getCurrentItem()) {
                navigation += getString(R.string.material_icon_point_full)
                        + "  ";
            } else {
                navigation += getString(R.string.material_icon_point_empty)
                        + "  ";
            }
        }
        navigator.setText(navigation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class WizardPagerAdapter extends FragmentPagerAdapter {

        public WizardPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return BeforeFragment.newInstance(position);
            } else if (position == 1) {
                return DuringFragment.newInstance(position);
            }else{
                return AfterFragment.newInstance(position);
            }
        }
    }

}
