package com.example.choremates;


public class NavigationDrawer {

    /*
   Nicole you need to copy paste these next two methods into all ur activities that need the navigation
   drawer. I just added it into another java class so its easier to copy paste. Dont forget to call navigation()
   in your OnCreat method.
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
                        startActivity(new Intent(getApplicationContext(), ChoresActivity.class));
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
