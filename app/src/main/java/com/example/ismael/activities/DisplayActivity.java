package com.example.ismael.activities;



import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ismael.fragments.MainFragment;
import com.example.ismael.fragments.ProfileFragment;
import com.example.ismael.R;
import com.example.ismael.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DisplayActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        relativeLayout = findViewById(R.id.relativelayout);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelectedListener);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new MainFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.users); // Set the "Home" button as default

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.dashbord:
                            selectedFragment = new ProfileFragment();
                            //If the dashboard item is selected, set selectedFragment to ProfileFragment.
                            break;
                        case R.id.users:
                            selectedFragment = new MainFragment();
                            //If the users item is selected, set selectedFragment to MainFragment.
                            break;
                        case R.id.profile:
                            selectedFragment = new SearchFragment();
                            //If the profile item is selected, set selectedFragment to SearchFragment.
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, selectedFragment).commit();
                    return true;
                    //Replaces the current fragment in the RelativeLayout with the selected fragment.
                }
            };
}















