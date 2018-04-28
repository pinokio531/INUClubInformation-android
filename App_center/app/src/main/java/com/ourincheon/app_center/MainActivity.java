package com.ourincheon.app_center;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Fragment {

    public MainActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_main, container, false);

        ImageButton bu_sports = (ImageButton) layout.findViewById(R.id.sports);
        ImageButton bu_religion = (ImageButton) layout.findViewById(R.id.religion);
        ImageButton bu_culture = (ImageButton) layout.findViewById(R.id.culture);
        ImageButton bu_bongsa = (ImageButton) layout.findViewById(R.id.bongsa);
        ImageButton bu_habit = (ImageButton) layout.findViewById(R.id.habit);
        ImageButton bu_study = (ImageButton) layout.findViewById(R.id.study);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(container.getWindowToken(),0);
            }
        });

        bu_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });

        bu_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });
        bu_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });
        bu_religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });
        bu_culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });
        bu_bongsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });

        return layout;

    }
}
