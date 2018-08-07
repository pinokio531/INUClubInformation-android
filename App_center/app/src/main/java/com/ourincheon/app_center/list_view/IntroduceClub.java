package com.ourincheon.app_center.list_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;
import com.ourincheon.app_center.list_view.Images.Image1;
import com.ourincheon.app_center.list_view.Images.Image2;
import com.ourincheon.app_center.list_view.Images.Image3;
import com.ourincheon.app_center.list_view.Images.Image4;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroduceClub extends FragmentActivity{

    private TextView introduce;
    private TextView location;
    private TextView represent;
    private TextView ph_number;
    private TextView club_name;

    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        introduce = (TextView) findViewById(R.id.introduceClub);
        location = (TextView) findViewById(R.id.place);
        represent = (TextView) findViewById(R.id.representName);
        ph_number = (TextView) findViewById(R.id.representNumber);
        club_name = (TextView) findViewById(R.id.clubName2);

        viewPager = (ViewPager) findViewById(R.id.clubImages);
        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);

        Intent intent = getIntent();
        int getNB = intent.getIntExtra("club_number", 0);

        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getDetailInformation(getNB);
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {

                String CLname = response.body().get(0).getAsJsonObject().get("clubname").toString().replace("\"", "").replace("\\n", "</br>");
                String CLrepresent = response.body().get(0).getAsJsonObject().get("representative").toString().replace("\"", "").replace("\\n", "</br>");
                String CLphone = response.body().get(0).getAsJsonObject().get("phone").toString().replace("\"", "").replace("\\n", "</br>");
                String CLlocation = response.body().get(0).getAsJsonObject().get("location").toString().replace("\"", "").replace("\\n", "</br>");
                String CLintro = response.body().get(0).getAsJsonObject().get("contents").toString().replace("\"", "").replace("\\n", "</br>");

                club_name.setText(CLname.replace("</br>", "\n"));
                represent.setText(CLrepresent.replace("</br>", "\n"));
                ph_number.setText(CLphone.replace("</br>", "\n"));
                location.setText(CLlocation.replace("</br>", "\n"));
                introduce.setText(CLintro.replace("</br>", "\n"));
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });
    }

    private class pageAdapter extends FragmentPagerAdapter {
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Image1();
                case 1:
                    return new Image2();
                case 2:
                    return new Image3();
                case 3:
                    return new Image4();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
