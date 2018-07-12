package com.ourincheon.app_center.list_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by doyeon on 2018-01-16.
 */

public class List extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_list);

        final ListView listView = (ListView) findViewById(R.id.listViewLayout);
        final ArrayList<ListViewItem> clubList = new ArrayList<>();
        final ListViewItem listViewItem = new ListViewItem();

        Intent intent = getIntent();
        final String clickCategory = intent.getStringExtra("category");

        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getInformation();
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {

                if(response.isSuccessful()){
                    for(int i = 0; i <= response.body().size(); i++){

                        String content = response.body().get(i).getAsJsonObject().get("category").toString();
                        String contentResult = content.replace("\"", " ");

                        listViewItem.clubCategory = contentResult;
                        listViewItem.clubName = response.body().get(i).getAsJsonObject().get("clubname").toString();
                        listViewItem.clubLocation = response.body().get(i).getAsJsonObject().get("location").toString();
                        clubList.add(listViewItem);
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });

        ListViewAdaptor adaptor = new ListViewAdaptor(clubList);
        listView.setAdapter(adaptor);
    }
}
