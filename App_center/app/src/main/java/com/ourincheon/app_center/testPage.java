package com.ourincheon.app_center;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.application.NetworkController;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testPage extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testretrofit);

        final TextView textView = (TextView) findViewById(R.id.test);

        Intent intent =getIntent();
        final String clickCategory = intent.getStringExtra("category");

        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getInformation();
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                int a = response.body().size();
                String b = response.body().iterator().next().getAsJsonObject().get("category").toString();
                String content = response.body().get(i).getAsJsonObject().get("category").toString();
                String contentResult = content.replace("\"", " ");
                if(clickCategory == contentResult){

                }
                    textView.setText(b);
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });
    }
}
