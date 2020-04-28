package com.example.choremates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class AddChoresActivity extends AppCompatActivity {

    private Button kitchen;
    private Button bedroom;
    private Button bathroom;
//    private Button general;
//    private Button custom;
//    private Button livingRoom;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chores);

        kitchen = findViewById(R.id.kitchenButton);
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, KitchenChoresActivity.class);
                startActivity(intent);
            }
        });

        bedroom = findViewById(R.id.bedroomButton);
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, BedroomChoresActivity.class);
                startActivity(intent);
            }
        });

        bathroom = findViewById(R.id.bathroomButton);
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, BathroomChoresActivity.class);                startActivity(intent);
                startActivity(intent);
            }
        });

//        general = findViewById(R.id.generalButton);
//        general.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddChoresActivity.this, GeneralChoresActivity.class);
//                startActivity(intent);
//            }
//        });

//        livingRoom = findViewById(R.id.livingButton);
//        livingRoom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddChoresActivity.this, LivingroomChoresActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        custom = findViewById(R.id.customButton);
//        custom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddChoresActivity.this, CustomChoresActivity.class);
//                startActivity(intent);
//            }
//        });

        navigation();//don't forget to call it.
    }

    /*
    Nicole you need to copy paste these next two methods into all ur activities that need the navigation
    drawer.
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }//end onBackPressed



    private void navigation(){
        drawer = findViewById(R.id.chores_drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(false);
                switch (item.getItemId()){
                    case R.id.nav_my_chores:
                        startActivity(new Intent (getApplicationContext(), ChoresActivity.class));
                        break;
                    case R.id.nav_add_chores:
                        startActivity(new Intent (getApplicationContext(), AddChoresActivity.class));
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.red));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
}
