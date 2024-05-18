package com.example.ismael.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismael.database.DBHelper;
import com.example.ismael.R;
import com.example.ismael.adapters.UsersAdapter;

import java.util.List;

public class ShowUsersActivity extends AppCompatActivity {

    private DBHelper helper;
    RecyclerView rc;
    UsersAdapter adapter;
    List<String> users;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        rc = findViewById(R.id.rcUsers);

        // Retrieve all users from DBHelper Local Database!
        helper = new DBHelper(this);
        users = helper.getAllUsernames();
        helper.close();

        // Set up Users RecyclerView!
        rc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this, users);
        rc.setAdapter(adapter);

    }
}