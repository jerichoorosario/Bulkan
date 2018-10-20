package com.team.bulkan.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.bulkan.R;

public class DuringFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;
    private LinearLayout layout;
    private LinearLayout llOnboarding;
    private TextView icon;

    private TextView tvOnboardText;

    public static DuringFragment newInstance(int position) {
        DuringFragment f = new DuringFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_during,
                container, false);
        layout = (LinearLayout) rootView.findViewById(R.id.fragment_wizard_during_layout);
        llOnboarding = (LinearLayout) rootView.findViewById(R.id.ll_onboarding);
        llOnboarding.setBackground(getActivity().getResources().getDrawable(R.drawable.onboard_during));
        llOnboarding.invalidate();
        ViewCompat.setElevation(rootView, 50);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View v){

    }
}
