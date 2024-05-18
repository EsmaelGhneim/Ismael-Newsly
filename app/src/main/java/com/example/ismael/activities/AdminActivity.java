package com.example.ismael.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ismael.R;

public class AdminActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button logoutButton = findViewById(R.id.logoutButton);
        Button addNewsButton = findViewById(R.id.addNewsButton);
        Button delete = findViewById(R.id.Delete);
        Button users = findViewById(R.id.showUsersButton);

        sharedPreferences = getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewsActivity();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(AdminActivity.this, DeleteItemActivity.class);
                startActivity(loginIntent);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(AdminActivity.this, ShowUsersActivity.class);
                startActivity(loginIntent);
            }
        });

    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent loginIntent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void openAddNewsActivity() {
        Intent addNewsIntent = new Intent(AdminActivity.this, AddItemActivity.class);
        startActivity(addNewsIntent);
    }
}
