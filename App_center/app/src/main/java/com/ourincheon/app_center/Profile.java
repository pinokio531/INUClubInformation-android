package com.ourincheon.app_center;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {
    ImageButton tab1;
    ImageButton tab2;

    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tab1=(ImageButton)findViewById(R.id.tab2);
        tab1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        tab2=(ImageButton)findViewById(R.id.tab3);

        b1=(Button)findViewById(R.id.event);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Event.class);
                startActivity(intent);
            }
        });

        //b2는 미정>
        b3=(Button)findViewById(R.id.modify_text);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ModifyText.class);
                startActivity(intent);
            }
        });

        b4=(Button)findViewById(R.id.modify_photo);
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ModifyPhoto.class);
                startActivity(intent);
            }
        });
    }


}
