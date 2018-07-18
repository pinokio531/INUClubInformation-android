package com.ourincheon.app_center.list_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroduceClub extends AppCompatActivity{

    private TextView introduce;
    private TextView location;
    private ImageView picture;
    private TextView represent;
    private TextView ph_number;
    private TextView club_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        introduce = (TextView) findViewById(R.id.introduceClub);
        location = (TextView) findViewById(R.id.place);
        picture = (ImageView) findViewById(R.id.clubImageView2);
        represent = (TextView) findViewById(R.id.representName);
        ph_number = (TextView) findViewById(R.id.representNumber);
        club_name = (TextView) findViewById(R.id.clubName2);

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
}
