package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarSpecificPage extends AppCompatActivity {

    private TextView day;
    private TextView chores;
    private DatabaseHelper db;
    private String[] users;
    private String textDisplay;
    String startDay = "02/10/2020", endDay = "02/20/2020", currentDay = "02/12/2020";
    String freq = "biweekly", dayOfWeek = "monday wednesday friday ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_specific_page);

        DisplayDate();
        //DisplayChores();
    }

    public void DisplayDate(){
        String date;
        Intent intent = getIntent();
        date = intent.getStringExtra("DATE");
        day = findViewById(R.id.date);
        day.setText(date);
    }// end method DisplayDate

    public void DisplayChores(){
        chores = findViewById(R.id.no_chores);

        //For Nivetha:
        //First for loop will be for the users
        //Length of loop is num users so range from 1 to 4
        //At the end of the loop it'll go to the next user

        //Second loop nested inside for loop is for the chores of user
        //the length is the number of chores the user has
        //Each time the startDay, endDay, freq, dayOfWeek should be initialized
            //String startDay = "02/10/2020", endDay = "02/20/2020", currentDay = "02/12/2020";
            //String freq = "biweekly", dayOfWeek = "monday wednesday friday ";
        //Then use the isChore function to check if the chore is on the date
        //If isChore returns true then you add the chore to the variable textDisplay with \n
        //Then search the next variable

        //When the for loops are done you display the textDisplay in no_current (the whole find view by id thingy)

        //So bascially when you do the demo don't test monthly or biweekly because I didn't do biweekly and I didnt test monthly
        //so it's prob not that good tbh


    }// end method DisplayChore

    public boolean isChore(){
        if(isBefore() == true || isAfter() == true){
            //Toast.makeText(getApplicationContext(),"Current day not in the date",Toast.LENGTH_SHORT).show();
            return false;
        } // end if
        else {
            try {
                if(isWeekDay() == false){
                    //Toast.makeText(getApplicationContext(),"Current day not in the date",Toast.LENGTH_SHORT).show();
                    return false;
                }// end if
                else{
                    Toast.makeText(getApplicationContext(),"Weekday is equal",Toast.LENGTH_SHORT).show();
                    if(isFreq() == true){
                        return true;
                    }// end if
                    //then test freq
                }// end else
            } // end try
            catch (ParseException e) {
                e.printStackTrace();
            }// end cacth
        } // end else
        return false;
    }// end method is chore

    public boolean isBefore(){
        if(Integer.parseInt(startDay.substring(6,10)) > Integer.parseInt(currentDay.substring(6,10))){
            return true;
        } // end if
        else if(Integer.parseInt(startDay.substring(6,10)) == Integer.parseInt(currentDay.substring(6,10))){
            if(Integer.parseInt(startDay.substring(0,2)) > Integer.parseInt(currentDay.substring(0,2))){
                return true;
            }// end if
            else if (Integer.parseInt(startDay.substring(0,2)) == Integer.parseInt(currentDay.substring(0,2))){
                if(Integer.parseInt(startDay.substring(3,5)) > Integer.parseInt(currentDay.substring(3,5))){
                    return true;
                }// end if
            }// end else if
        }// end else if
        return false;
    }// end method checkBefore

    public boolean isAfter(){
        if(endDay == "never"){
            return false;
        }// end if
        else if(Integer.parseInt(endDay.substring(6,10)) < Integer.parseInt(currentDay.substring(6,10))){
            return true;
        } // end else if
        else if(Integer.parseInt(endDay.substring(6,10)) == Integer.parseInt(currentDay.substring(6,10))){
            if(Integer.parseInt(endDay.substring(0,2)) < Integer.parseInt(currentDay.substring(0,2))){
                return true;
            }// end if
            else if (Integer.parseInt(endDay.substring(0,2)) == Integer.parseInt(currentDay.substring(0,2))){
                if(Integer.parseInt(endDay.substring(3,5)) < Integer.parseInt(currentDay.substring(3,5))){
                    return true;
                }// end if
            }// end else if
        }// end else if
        return false;
    }// end method isAfter

    public boolean isWeekDay() throws ParseException {

        int dayWeek = getWeekday(currentDay);

        int length = 0;
        String d = dayOfWeek;
        for(int i = 0; i < d.length(); i++){
            d = d.substring(d.indexOf(" ") + 1);
            length++;
        } // end for

        for(int i = 0; i < length; i++){
            String day = dayOfWeek.substring(0, dayOfWeek.indexOf(" "));
            int date = 0;
            switch (day) {
                case "monday":
                    date = 2;
                    break;
                case "tuesday":
                    date = 3;
                    break;
                case "wednesday":
                    date = 4;
                    break;
                case "thursday":
                    date = 5;
                    break;
                case "friday":
                    date = 6;
                    break;
                case "saturday":
                    date = 7;
                    break;
                case "sunday":
                    date = 1;
                    break;
            } // end switch
            if(dayWeek == date){
                return true;
            }// end if
            dayOfWeek = dayOfWeek.substring(dayOfWeek.indexOf(" ") + 1);
        } // end for
        return false;
    } // end method isWeekDay

    public boolean isFreq() throws ParseException {
        if (freq == "weekly"){
            return true;
        }// end else if
        else if (freq == "biweekly"){
            int cW = getWeekday(currentDay);
            int sD = getWeekday(startDay);
            int dif = cW - sD;

        }// end else if
        else if (freq == "monthly"){
            int cW = getWeekday(currentDay);
            int sD = getWeekday(startDay);
            int dif = cW - sD;

            startDay = startDay.substring(0,2) + "/" + (Integer.parseInt(startDay.substring(3,5)) + dif) + "/" + startDay.substring(6,10);
            // fix bc int can be 9 instead of 09 same with months

            for(int i = 0; i <= Integer.parseInt(endDay.substring(6,10)) - Integer.parseInt(startDay.substring(6,10)); i++){
                startDay = startDay.substring(0,2) + "/" + startDay.substring(3,5) + "/" + (Integer.parseInt(startDay.substring(6,10)) + i);

                for(int j = 0; j < 12; j++){
                    startDay = (Integer.parseInt(startDay.substring(0,2)) + j) + "/" + startDay.substring(3,5) + "/" + startDay.substring(6,10);
                    if(startDay == currentDay){
                        return true;
                    }// end if
                }// end for

            }// end for
        }// end else if
        else if(freq == "just once"){
            return true;
        }// end else if
        else if(freq == "daily"){
            return true;
        }// end else is
        else if(freq == "weekdays"){
            return true;
        }// end else if
        else if(freq == "weekends"){
            return true;
        }// end else if
        return false;
    }// end method isFreq

    public int getWeekday(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat curFormater = new SimpleDateFormat("MM/dd/yyyy");
        Date dateObj = curFormater.parse(date);
        c.setTime(dateObj);
        int d = c.get(Calendar.DAY_OF_WEEK);

        return d;
    }// end method getWeekday


} // end CalendarSpecificPage

