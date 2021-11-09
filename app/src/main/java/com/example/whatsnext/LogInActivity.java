package com.example.whatsnext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //this allows user to stay logged in even after closing the app
        if(ParseUser.getCurrentUser() != null) {
            //goViewList(); NOT FOR THIS APP
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin=findViewById(R.id.btnLogin);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignup();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mybutton(v);
            }

            private void mybutton(View v) {
                Toast.makeText(LogInActivity.this, "Logging In!", Toast.LENGTH_SHORT).show();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }
    //This is how the user is logging in
    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to log in " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!= null) {
                    Toast.makeText(LogInActivity.this, "Failed to log in", Toast.LENGTH_SHORT).show();
                    return;
                }
                //goViewList(); NOT FOR THIS APP
            }
        });
    }

    //sends user to the view list page
   /* public void goViewList() {
        Intent i = new Intent(this, ViewListActivity.class);
        startActivity(i);
        finish();

    }*/
    public void goToSignup() {
        Intent i = new Intent (this, SignUpActivity.class);
        startActivity(i);
    }

}