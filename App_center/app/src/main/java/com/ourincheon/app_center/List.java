package com.ourincheon.app_center;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ourincheon.app_center.application.ApplicationController;
import com.ourincheon.app_center.model.club_info;
import com.ourincheon.app_center.network.NetworkService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by doyeon on 2018-01-16.
 */

public class List extends AppCompatActivity{

    ApplicationController applicationController;
    NetworkService networkService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        applicationController = new ApplicationController();
        networkService = ApplicationController.getInstance().getNetworkService();
        club_info clubInformation = new club_info();

        Call<java.util.List<club_info>> call = networkService.getClubInfo(clubInformation);
        call.enqueue(new Callback<java.util.List<club_info>>() {
            @Override
            public void onResponse(Call<java.util.List<club_info>> call, Response<java.util.List<club_info>> response) {

            }

            @Override
            public void onFailure(Call<java.util.List<club_info>> call, Throwable t) {

            }
        });

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATE");
        //getClubList(categoryName);

        final EditText editText = (EditText) findViewById(R.id.SearchEdit);
        LinearLayout background = (LinearLayout) findViewById(R.id.anhalle);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(editText.getWindowToken(),0);
            }
        });


//        TextView name=(TextView)findViewById(R.id.clubName);
//        TextView place = (TextView)findViewById(R.id.place);
//        TextView explain=(TextView)findViewById(R.id.clubExplain);
//
//        name.setText();
//
//        String str = tv.getText().toString();
//        //SpannableStringBuilder ssb = new SpannableStringBuilder(str);
//        //ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#7474df")), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv.setText(ssb);


        TextView tv1 = (TextView)findViewById(R.id.place1);
        String str1 = tv1.getText().toString();
        SpannableStringBuilder ssb1 = new SpannableStringBuilder(str1);
        ssb1.setSpan(new ForegroundColorSpan(Color.parseColor("#7474df")), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv1.setText(ssb1);

        final ViewGroup layout = (ViewGroup) findViewById(R.id.perfectButton);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(List.this,Apply.class);
                startActivity(intent);
            }
        });




    }

    NetworkService service;
    private void getClubList(String categoryName) {

        // API 인터페이스 생성
        service = ApplicationController.getInstance().getNetworkService();

        // 인터페이스에 구현한 메소드인 contributors에 param 값을 넘기는 요청 만ㄷ름
        String encodedString = "";
        try {
            encodedString = URLEncoder.encode(categoryName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "/club/category/" + encodedString;

        ListViewItem listViewItem = new ListViewItem(String clubImageURL, encodedString,String clubExpStr,String clubPlaceStr);

        Log.e("mylog", "url : " + url);

        Call<java.util.List<club_info>> call = service.getClubList(listViewItem);

        // 앞서만든 요청을 수행
        call.enqueue(new Callback<java.util.List<club_info>>() {
            @Override
            // 성공시
            public void onResponse(Call<java.util.List<club_info>> call, Response<java.util.List<club_info>> response) {

                //if(response.isSuccessful()) {

                java.util.List<club_info> mDatas;

                mDatas = response.body();
                Log.e("mylog", "size : " + mDatas.get(0).num );


                for (club_info c : mDatas) {

                    Log.e("mylog", "c.clubname : " + c.clubname);
                }




                // }
            }

            @Override
            // 실패시
            public void onFailure(Call<java.util.List<club_info>> call, Throwable t) {
                Toast.makeText(List.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
