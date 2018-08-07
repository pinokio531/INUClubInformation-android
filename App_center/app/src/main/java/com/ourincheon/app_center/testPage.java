package com.ourincheon.app_center;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.ourincheon.app_center.application.NetworkController;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testPage extends AppCompatActivity {

    String image1 = "asd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testretrofit);

        final ImageView imageview = (ImageView) findViewById(R.id.test);
        final TextView textView = (TextView) findViewById(R.id.test2);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getDetailInformation(11);
                call.enqueue(new Callback<ArrayList<JsonObject>>() {
                    @Override
                    public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                        image1 = response.body().get(0).getAsJsonObject().get("image1").toString().replace("\"", "");
                            if(image1.length() < 5){
                                imageview.setImageResource(R.drawable.photoplus);
                            }
                            else{
                                String url2 = "http://inuclub.us.to:3303/" + image1;
                                //http://inuclub.us.to:3303/club_img/club_11_1521721044212.png
                                Glide.with(imageview.getContext()).load(url2).placeholder(R.drawable.group_6)
                                        .dontAnimate()
                                        .into(imageview);
                                textView.setText(url2);
                            }
                        }


                    @Override
                    public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

                    }
                });
            }
        });
    }
}
