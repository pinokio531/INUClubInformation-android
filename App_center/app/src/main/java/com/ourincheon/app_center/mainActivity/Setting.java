package com.ourincheon.app_center.mainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.ourincheon.app_center.R;

public class Setting extends Fragment {

    public Setting(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_profile, container, false);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(container.getWindowToken(), 0);
            }
        });

        return layout;
    }
}
