package com.example.demouser.bond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalendarMainActivity extends AppCompatActivity {

    //private static final String TAG = CalendarMainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_main);
        CalendarCustomView customView = (CalendarCustomView)findViewById(R.id.customerCalnder);
    }


}