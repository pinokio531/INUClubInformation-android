package com.ourincheon.app_center.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ourincheon.app_center.R;
import com.ourincheon.app_center.list_view.List;

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

        LinearLayout sports = (LinearLayout) layout.findViewById(R.id.sportsLayout);
        LinearLayout religion = (LinearLayout) layout.findViewById(R.id.religionLayout);
        LinearLayout culture = (LinearLayout) layout.findViewById(R.id.cultureLayout);
        LinearLayout bongsa = (LinearLayout) layout.findViewById(R.id.bongsaLayout);
        LinearLayout habit = (LinearLayout) layout.findViewById(R.id.habitLayout);
        LinearLayout study = (LinearLayout) layout.findViewById(R.id.studyLayout);

        ImageView imageView = (ImageView) layout.findViewById(R.id.testImage);

        Glide.with(getContext()).load("http://inuclub.us.to:3303/main_img/untitleds.png").into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(container.getWindowToken(),0);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("스포츠");
            }
        });

        bu_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("스포츠");
            }
        });

        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("종교");
            }
        });

        bu_religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("종교");
            }
        });

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("문화");
            }
        });

        bu_culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("문화");
            }
        });

        bongsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("봉사");
            }
        });

        bu_bongsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("봉사");
            }
        });

        habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("취미전시");
            }
        });

        bu_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("취미전시");
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("교양학술");
            }
        });

        bu_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToList("교양학술");
            }
        });

        return layout;

    }

    public void intentToList(String str){
        Intent intent = new Intent(getActivity(), List.class);
        intent.putExtra("category", str);
        startActivity(intent);
    }
}
