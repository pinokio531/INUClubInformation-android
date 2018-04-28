package com.ourincheon.app_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class PopUp extends Activity implements View.OnClickListener {

    private Button mConfirm, mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        setContent();
    }

    private void setContent() {
        mConfirm = (Button) findViewById(R.id.accept);
        mCancel = (Button) findViewById(R.id.cancel);

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                this.finish();
                break;
            case R.id.cancel:
                this.finish();
                break;
            default:
                break;
        }
    }
}
