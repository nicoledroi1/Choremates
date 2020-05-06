package com.example.choremates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

public class CalendarViewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view_page);

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = Integer.toString(month) + "/" + Integer.toString(dayOfMonth) + "/" + Integer.toString(year);

                Intent intent = new Intent(view.getContext(), CalendarSpecificPage.class);
                intent.putExtra("DATE", date);
                startActivity(intent);
            }
        });

    }

}
