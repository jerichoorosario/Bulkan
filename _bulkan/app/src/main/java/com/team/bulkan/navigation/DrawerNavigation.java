package com.team.bulkan.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.team.bulkan.R;

import java.util.ArrayList;

/**
 * Created by Kira on 11/9/2017.
 */

public class DrawerNavigation {

    public static ArrayList<NavModel> getDrawerListIconsClient() {
        ArrayList<NavModel> list = new ArrayList<>();
        list.add(0,new NavModel(0, "", "Volcano Eruption Preparedness", R.drawable.ic_preparedness));
        list.add(1,new NavModel(1, "", "Volcano Hazards", R.drawable.ic_hazard));
        return list;
    }

    public static Drawable getNavDrawerDrawable(Context ctx, int whichDrawable) {
        //LAWYER
        switch (whichDrawable) {
            default:
            case 0:
                return ctx.getResources().getDrawable(R.drawable.ic_preparedness);
            case 1:
                return ctx.getResources().getDrawable(R.drawable.ic_hazard);
        }

    }
}
