package com.example.choremates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        openCalendarPage();
    }// end OnClick

    private void openCalendarPage() {
        Intent intent = new Intent(this, CalendarViewPage.class);
        startActivity(intent);
    }// end openCalendarPage methos
}
