package com.ourincheon.app_center.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;
import com.ourincheon.app_center.model.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    LoginData loginData;
    TextView textView;
    EditText id_Info;
    EditText pw_Info;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_Info = (EditText) findViewById(R.id.idInfo);
        pw_Info = (EditText) findViewById(R.id.pwInfo);
        button = (Button) findViewById(R.id.Blogin);
        textView = (TextView) findViewById(R.id.resultF);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = id_Info.getText().toString();
                String pw = pw_Info.getText().toString();

                loginData= new LoginData(id, pw);

                Call<String> call = NetworkController.getInstance().getNetworkInterface().getLoginInfo(loginData);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(Login.this, Viewpager_main.class);
                            startActivity(intent);
                            finish();

                        }
                        else{
                            textView.setText("학번 또는 비밀번호를 다시 확인해 주세요");
                            textView.setTextColor(getResources().getColor(R.color.loginfale));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });
    }
}
