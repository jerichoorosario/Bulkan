package com.team.bulkan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.bulkan.R;

public class WizardFragment  extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;
    private LinearLayout layout;
    private LinearLayout llOnboarding;
    private TextView icon;

    private TextView tvOnboardText;

    public static WizardFragment newInstance(int position) {
        WizardFragment f = new WizardFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wizard,
                container, false);
        layout = (LinearLayout) rootView.findViewById(R.id.fragment_wizard_universal_layout);
        llOnboarding = (LinearLayout) rootView.findViewById(R.id.ll_onboarding);
        tvOnboardText = (TextView) llOnboarding.findViewById(R.id.tv_onboard_text);



        if (position == 0) {
            llOnboarding.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_logo));
            llOnboarding.invalidate();
            //tvOnboardText.setText(getActivity().getResources().getString(R.string.on_board_lawyer_1));
        } else if(position == 1){
            llOnboarding.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_logo));
            llOnboarding.invalidate();
            //tvOnboardText.setText(getActivity().getResources().getString(R.string.on_board_lawyer_2));
        }else if(position == 2){
            llOnboarding.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_logo));
            llOnboarding.invalidate();
            //tvOnboardText.setText(getActivity().getResources().getString(R.string.on_board_lawyer_3));
        }else{
            llOnboarding.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_logo));
            llOnboarding.invalidate();
            //tvOnboardText.setText(getActivity().getResources().getString(R.string.on_board_lawyer_5));
        }
        ViewCompat.setElevation(rootView, 50);
        return rootView;
    }

}
