package com.ourincheon.app_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class Event extends Activity {
    EditText eventDate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventDate=(EditText) findViewById(R.id.edit_EventDate);

        eventDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(
                        getApplicationContext(), EventDate.class);
                startActivity(intent);
            }
        });
    }
}
