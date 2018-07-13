package com.ourincheon.app_center;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ourincheon.app_center.mainActivity.Login;

/**
 * Created by doyeon on 2018-02-07.
 */

public class SplashActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        WaitingTime();
    }

    private void WaitingTime() {
        Thread SplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int wait = 0;
                    while (wait < 1000) {
                        sleep(100);
                        wait += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {

                }
            }

        };
        SplashThread.start();
    }
}