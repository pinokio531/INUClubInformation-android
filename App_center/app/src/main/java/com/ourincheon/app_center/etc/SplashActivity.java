package com.ourincheon.app_center.etc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ourincheon.app_center.R;
import com.ourincheon.app_center.mainActivity.Login;

/**
 * Created by doyeon on 2018-02-07.
 */

public class SplashActivity extends AppCompatActivity {

    String existed_id;
    String existed_pw;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences savedToken = getSharedPreferences("loginToken", MODE_PRIVATE);
        existed_id = savedToken.getString("savedID", "noID");
        existed_pw = savedToken.getString("savedPW", "noPW");

        if(existed_id == "noID" && existed_pw =="noPW"){
            WaitingTime(0);
        }
        else{
            WaitingTime(1);
        }
    }

    private void WaitingTime(int whereWeGoing) {
        Thread SplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int wait = 0;
                    while (wait < 1000) {
                        sleep(100);
                        wait += 100;
                    }
                    WhichPage(whereWeGoing);
                    finish();
                } catch (InterruptedException e) {

                }
            }

        };
        SplashThread.start();
    }


    public void WhichPage(int checked){
        Intent intent;
        switch (checked){
            case 0:
                intent = new Intent(SplashActivity.this, Permission.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(SplashActivity.this, Login.class);
                intent.putExtra("s_id", existed_id);
                intent.putExtra("s_pw", existed_pw);
                startActivity(intent);
                break;
        }
        finish();

    }
}