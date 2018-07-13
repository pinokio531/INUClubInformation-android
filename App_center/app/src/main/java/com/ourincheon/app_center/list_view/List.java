package com.ourincheon.app_center.list_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

    private ListView listView = null;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_list);

        listView = (ListView) findViewById(R.id.listViewLayout);
        TextView categoryTitle = (TextView) findViewById(R.id.categoryList);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.listBackground);

        Intent intent = getIntent();
        final String clickCategory = intent.getStringExtra("category");

        categoryTitle.setText(clickCategory);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(layout.getWindowToken(),0);
            }
        });

        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getInformation(clickCategory);
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                ArrayList<ListViewItem> clubList = new ArrayList<>();
                    for(int i = 0; i < response.body().size(); i++){

                        ListViewItem listViewItem = new ListViewItem();

                        String nameOfClub = response.body().get(i).getAsJsonObject().get("clubname").toString();
                        String locationOfClub = response.body().get(i).getAsJsonObject().get("location").toString();
                        String imageOfClub = response.body().get(i).getAsJsonObject().get("image1").toString();

                        listViewItem.clubImage = imageOfClub.replace("\"", "");
                        listViewItem.clubName = nameOfClub.replace("\"", "");
                        listViewItem.clubLocation = locationOfClub.replace("\"", "");
                        clubList.add(listViewItem);

                        ListViewAdaptor adaptor = new ListViewAdaptor(clubList);
                        listView.setAdapter(adaptor);
                    }
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });
    }
}
