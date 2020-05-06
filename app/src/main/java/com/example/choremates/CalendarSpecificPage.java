package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarSpecificPage extends AppCompatActivity {

    private TextView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_specific_page);

        new CalendarSpecificPage();
    }

    public CalendarSpecificPage(){
        String date;
        Intent intent = getIntent();
        date = intent.getStringExtra("DATE");
        day = findViewById(R.id.date);
        day.setText(date);
    }

}

