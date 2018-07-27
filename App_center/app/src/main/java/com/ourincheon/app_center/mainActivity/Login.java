package com.ourincheon.app_center.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;
import com.ourincheon.app_center.model.LoginData;
import com.ourincheon.app_center.network.NetworkInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView textView;
    EditText id_Info;
    EditText pw_Info;
    Button button;

    LinearLayout layout;

    NetworkInterface networkInterface;
    LoginData loginData;


    private void initNetwork(){
        this.networkInterface = NetworkController.getInstance().getNetworkInterface();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_Info = (EditText) findViewById(R.id.idInfo);
        pw_Info = (EditText) findViewById(R.id.pwInfo);
        button = (Button) findViewById(R.id.Blogin);
        textView = (TextView) findViewById(R.id.resultF);
        layout = (LinearLayout) findViewById(R.id.LoginPage);

        initNetwork();
        keybordControl();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = id_Info.getText().toString();
                String pw = pw_Info.getText().toString();

                Intent intentInformation = new Intent();
                intentInformation.putExtra("userID", id );
                intentInformation.putExtra("userPW", pw);

                Login.this.loginData = new LoginData(id, pw);


                Call<String> call = NetworkController.getInstance().getNetworkInterface().getLoginInfo(Login.this.loginData);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(Login.this, Viewpager_main.class);
                            intent.putExtra("clubIdNumber", response.body().toString() );
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

    public void keybordControl(){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(layout.getWindowToken(),0);
            }
        });
    }
}
