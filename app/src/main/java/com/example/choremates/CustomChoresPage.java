package com.example.choremates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CustomChoresPage extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText custChore;
    Button add;
    String chore;

    Spinner mySpinner;
    //String[] chores = {"Kitchen Chores", "Bathroom Chores", "Bedroom Chores", "General Chores", "Living Room Chores"};
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_chores_page);

        custChore = findViewById(R.id.editText);

        add = findViewById(R.id.addchore);
        add.setOnClickListener(this);

        mySpinner = (Spinner) findViewById(R.id.spinner);

        myAdapter = new ArrayAdapter<String>(CustomChoresPage.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.chores));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        chore = custChore.getText().toString();
        //Toast.makeText(this, chore, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            Toast.makeText(this, "This is not a type of chore chose another one!", Toast.LENGTH_SHORT).show();
        }//end if
        else{
            String chose = parent.getItemAtPosition(position).toString();
        }//end else

    }//end onItenSelected method

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
