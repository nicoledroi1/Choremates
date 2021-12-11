package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomChoresViewPage extends AppCompatActivity implements View.OnClickListener {

    Button create;
    TextView noChore;
    String[] custChore;
    TextView[] circles;
    int numArray = 0;
    int x = 55, y = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_chores_view_page);

        create = findViewById(R.id.createchore);
        create.setOnClickListener(this);
    }// end onCreate

    public CustomChoresViewPage(String chore){
       numArray = numArray++;
       custChore = new String[numArray];
       custChore[numArray] = chore;

       circles = new TextView[numArray];

       TextView newT = new TextView(this);
       newT.setText(chore);
       circles[numArray] = newT;

       String background;
       for(int i = 1; i <= custChore.length; i++){

           for(int j = 0; j < 2; j++){
               circles[i].setX(x);
               circles[i].setY(y);


           }//end for
       }//end for

    }//end constructor

    @Override
    public void onClick(View v) {
        noChore = findViewById(R.id.no_chores);
        noChore.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, CustomChoresPage.class);
        startActivity(intent);
    }// end method onClick
}
