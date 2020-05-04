package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.Locale;

public class FrequencyActivity extends AppCompatActivity {

    private Button today, tomorrow, pickADayButton;
    private Button justOnce, daily, weekly, biweekly, monthly, weekdays, weekends;
    private Button finish;
    private Switch mon, tues, wed, thurs, fri, sat, sun;
    private RadioButton never, pickADayRadio;
    private Chore currentChore;
    private Spinner owner;
    String days = "";
    private DatabaseHelper db;
    ArrayAdapter<String> myAdapter;
    private ArrayList<String> roommates;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency);

        today = findViewById(R.id.today);
        tomorrow = findViewById(R.id.tomorrow);
        pickADayButton = findViewById(R.id.pickaday);

        justOnce = findViewById(R.id.just_once);
        daily = findViewById(R.id.daily);
        weekly = findViewById(R.id.weekly);
        biweekly = findViewById(R.id.biweekly);
        monthly = findViewById(R.id.monthly);
        weekdays = findViewById(R.id.weekdays);
        weekends = findViewById(R.id.weekends);

        finish = findViewById(R.id.finish);

        mon = findViewById(R.id.monday);
        tues = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thurs = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);
        sun = findViewById(R.id.sunday);

        never = findViewById(R.id.never);
        pickADayRadio = findViewById(R.id.on_day);

        db = new DatabaseHelper(this);

        owner = findViewById(R.id.spinner_owner);

        final Switch[] switches = new Switch[]{mon, tues, wed, thurs, fri, sat, sun};

        Intent intent = getIntent();
        final String activity = intent.getStringExtra("Activity");
        switch (activity) {
            case "Bathroom":
                currentChore = BathroomChoresActivity.getCurrentChore();
                break;
            case "Kitchen":
                currentChore = KitchenChoresActivity.getCurrentChore();
                break;
            case "Bedroom":
                currentChore = BedroomChoresActivity.getCurrentChore();
                break;
            case "General":
                currentChore = GeneralChoresActivity.getCurrentChore();
                break;
            case "LivingRoom":
                currentChore = LivingRoomChoresActivity.getCurrentChore();
                break;
//            case "Custom":
//                currentChore = BathroomChoresActivity.getCurrentChore();
//                break;
        }

        if (LoginActivity.getEmail() == null || LoginActivity.getEmail().equals("")) {
            email = SignUpActivity.getEmail();
        } else {
            email = LoginActivity.getEmail();
        }

        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setStartDate("Today");
            }
        });

        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setStartDate("Tomorrow");
            }
        });

        justOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(justOnce.getText().toString());
            }
        });

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(daily.getText().toString());
                CustomAlertDialog.openDialogNoTitle(FrequencyActivity.this, FrequencyActivity.this,
                        (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                        "You can skip the next section as\nthe days have been\npreset for you.");
                mon.setChecked(true);
                tues.setChecked(true);
                wed.setChecked(true);
                thurs.setChecked(true);
                fri.setChecked(true);
                sat.setChecked(true);
                sun.setChecked(true);
            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(weekly.getText().toString());
            }
        });

        biweekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(biweekly.getText().toString());
            }
        });

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(monthly.getText().toString());
            }
        });

        weekdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(weekdays.getText().toString());
                CustomAlertDialog.openDialogNoTitle(FrequencyActivity.this, FrequencyActivity.this,
                        (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                        "You can skip the next section as\nthe days have been\npreset for you.");
                mon.setChecked(true);
                tues.setChecked(true);
                wed.setChecked(true);
                thurs.setChecked(true);
                fri.setChecked(true);
            }
        });

        weekends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore.setFrequency(weekends.getText().toString());
                CustomAlertDialog.openDialogNoTitle(FrequencyActivity.this, FrequencyActivity.this,
                        (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                        "You can skip the next section as\nthe days have been\npreset for you.");
                sat.setChecked(true);
                sun.setChecked(true);
            }
        });

        roommates = new ArrayList<String>();
        final Cursor cursor = db.getRoommates(email);
        while (cursor.moveToNext()) {
            for (int i = 2; i < 6; i++) {
                if (cursor.getString(i) != null) {
                    roommates.add(cursor.getString(i));
                }
            }
        }

        myAdapter = new ArrayAdapter<String>(FrequencyActivity.this,
                android.R.layout.simple_list_item_1, roommates);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        owner.setAdapter(myAdapter);
        owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentChore.setOwner(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                CustomAlertDialog.openDialogNoTitle(FrequencyActivity.this, FrequencyActivity.this,
                        (ConstraintLayout) findViewById(R.id.layoutDialogContainer), "" +
                                "Please choose an option!");
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < switches.length; i++) {
                    if (switches[i].isChecked()) {
                        days = days.concat(switches[i].getText().toString() + " ");
                    }
                }
                currentChore.setDays(days);
                if (never.isChecked()) {
                    currentChore.setEndDate("Never");
                }

                boolean insert = db.insertChore(currentChore.getName(), currentChore.getType(), currentChore.getFrequency(), currentChore.getDays(),
                        currentChore.getOwner(), email);
                if (insert) {
                    CustomAlertDialog.openDialogNoTitleNoButton(FrequencyActivity.this, FrequencyActivity.this,
                            (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                            "Chore added\nsuccessfully!");

                }
                Intent intent = new Intent(FrequencyActivity.this, ChoresActivity.class);
                startActivity(intent);
            }
        });
    }
}
