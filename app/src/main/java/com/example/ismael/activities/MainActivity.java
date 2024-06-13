package com.example.ismael.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ismael.database.DBHelper;
import com.example.ismael.R;


public class MainActivity extends AppCompatActivity {

    private static final String ADMIN_USERNAME = "ismael";
    private static final String ADMIN_PASSWORD = "1234";
    private static final String PREF_USERNAME = "newuser";
    private static final String PREF_PASSWORD = "newpass";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.etnm);
        passwordEditText = findViewById(R.id.etps);
        loginButton = findViewById(R.id.log);
        signupButton = findViewById(R.id.sign);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                    saveCredentials(username, password);
                    openAdminActivity();
                } else {
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    boolean isUserValid = dbHelper.checkUser(username, password);
                    if (isUserValid) {
                        saveCredentials(username, password);
                        openDisplayActivity();
                    } else {
                        Toast.makeText(MainActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainActivity.this, Signup.class);
                startActivity(signup);
            }
        });
          // if you get out of the app page it will automatically launch in the main activity and not go back to login page
        checkCredentials();
    }

    private void saveCredentials(String username, String password) {

        // save user in app files, shared preferences!
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_USERNAME, username);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }

    private void checkCredentials() {
        //Checks saved credentials to determine if the user is already logged in.
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String savedUsername = preferences.getString(PREF_USERNAME, "");
        String savedPassword = preferences.getString(PREF_PASSWORD, "");

        // TODO: trim check all places
        if (!savedUsername.trim().isEmpty() && !savedPassword.trim().isEmpty()) {
            //It checks if both the saved username and password are not empty
            if (savedUsername.equals(ADMIN_USERNAME) && savedPassword.equals(ADMIN_PASSWORD)) {
                //If the saved username and password match the predefined admin credentials, it opens the admin activity.
                openAdminActivity();
            } else {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                boolean isUserValid = dbHelper.checkUser(savedUsername, savedPassword);
                if (isUserValid) {
                    openDisplayActivity();
                }
            }
            //It calls the checkUser method of DBHelper to check if the user exists in the database with the saved credentials.
        }
        // TODO: what should IO give message if user credentials are incorrect
    }

    private void openAdminActivity() {
        //Opens the AdminActivity for admin users.
        Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
        startActivity(adminIntent);
        finish();
    }

    private void openDisplayActivity() {
        //Opens the DisplayActivity for regular users.
        Intent displayIntent = new Intent(MainActivity.this, DisplayActivity.class);
        startActivity(displayIntent);
        finish();
    }
}


