package com.ourincheon.app_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class Apply extends Activity implements OnClickListener {

    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_apply);

        button=(ImageButton)findViewById(R.id.ApplyButton);
        button.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ApplyButton:
                startActivity(new Intent(this,PopUp.class));
                break;
            default:
                break;
        }
    }
}
