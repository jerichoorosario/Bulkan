package com.team.bulkan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.bulkan.R;

/**
 * Created by Kira on 1/20/2018.
 */

public class TypographyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_views, container, false);

        return rootView;
    }
}
