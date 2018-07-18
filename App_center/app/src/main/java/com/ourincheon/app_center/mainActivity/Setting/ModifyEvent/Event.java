package com.ourincheon.app_center.mainActivity.Setting.ModifyEvent;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ourincheon.app_center.R;

import java.util.Calendar;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class Event extends AppCompatActivity {

    TextView calendar;
    TextView clock;

    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinutes;

    String M;
    String H;

    static final int DATEDIALOG = 1;
    static final int TIMEDIALOG = 2;

    Calendar sCalendar;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        calendar = (TextView) findViewById(R.id.dateContents);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCalendar = Calendar.getInstance();
                mYear = sCalendar.get(Calendar.YEAR);
                mMonth = sCalendar.get(Calendar.MONTH);
                mDay = sCalendar.get(Calendar.DAY_OF_MONTH);
                showDialog(DATEDIALOG);
            }
        });


        clock = (TextView) findViewById(R.id.timeContents);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCalendar = Calendar.getInstance();
                mHour = sCalendar.get(Calendar.HOUR_OF_DAY);
                mMinutes = sCalendar.get(Calendar.MINUTE);
                showDialog(TIMEDIALOG);

            }
        });

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mYear = year;
            mMonth = month;
            mDay = day;

            calendar.setText(mYear + "년" + " " + (mMonth + 1) + "월" + " " + mDay + "일");
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DATEDIALOG:

                datePickerDialog = new DatePickerDialog(Event.this, R.style.DatePiclerTheme, mDateSetListener, mYear, mMonth, mDay);

                return datePickerDialog;

            case TIMEDIALOG:

                timePickerDialog = new TimePickerDialog(Event.this, R.style.CunstomTime, mTimeSetListener, mHour, mMinutes, false);

                return timePickerDialog;

        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
            mHour = hour;
            mMinutes = minutes;

            if(mHour>=13){
                mHour = mHour - 12;
                clock.setText("오후" + " " + " " + hours() + " " + " :" + " " + minutes());
            }
            else if(mHour == 12){
                clock.setText("오후" + " " + " " + hours() + " " + " :" + " " + minutes());
            }
            else
            {
                clock.setText("오전" + " " + " " + hours() + " " + " :" + " " + minutes());
            }


        }

        public String minutes(){
            if(mMinutes <= 9){
                M = "0" + String.valueOf(mMinutes);
            }
            else{
                M = String.valueOf(mMinutes);
            }
            return M;
        }

        public String hours(){
            if(mHour <= 9){
                H = "0" + String.valueOf(mHour);
            }
            else if(mHour >= 13){
                H = "0" + String.valueOf(mHour);
            }
            else{
                H = String.valueOf(mHour);
            }
            return H;
        }

    };


}
