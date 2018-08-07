package com.ourincheon.app_center.mainActivity.Setting.ModifyClubInformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;
import com.ourincheon.app_center.model.ErrorMsgResult;
import com.ourincheon.app_center.model.ModifyClubInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class ModifyText extends AppCompatActivity {

    private EditText mContents;
    private EditText mRepresent;
    private EditText mPhone;
    LinearLayout layout;

    ModifyClubInfo modifyClubInfo;
    String clubnum = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_text);

        mContents = (EditText) findViewById(R.id.writeContentsEdit);
        mRepresent = (EditText) findViewById(R.id.writeRepresentEdit);
        mPhone = (EditText) findViewById(R.id.writePhoneEdit);
        layout = (LinearLayout) findViewById(R.id.Modify_text_Page);

        Button bt_submit = (Button) findViewById(R.id.B_submit);

        keybordControl();

        Intent intent = getIntent();
        clubnum = intent.getStringExtra("clubIdNumber");

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String represent = mRepresent.getText().toString();
                String phone = mPhone.getText().toString();
                String contents = mContents.getText().toString();
                String application = "업데이트 예정";

                modifyClubInfo = new ModifyClubInfo(represent, phone, application, contents);

                Intent receieveData = getIntent();

                final String clubnum = receieveData.getStringExtra("clubIdNumber");

                Call<ErrorMsgResult> call = NetworkController.getInstance().getNetworkInterface().giveModifiedContents(clubnum, modifyClubInfo);
                call.enqueue(new Callback<ErrorMsgResult>() {
                    @Override
                    public void onResponse(Call<ErrorMsgResult> call, Response<ErrorMsgResult> response) {

                    }

                    @Override
                    public void onFailure(Call<ErrorMsgResult> call, Throwable t) {

                    }
                });

                finish();
                Toast.makeText(ModifyText.this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
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
