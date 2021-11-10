package com.example.whatsnext;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SingUpActivity";
    EditText etSignupUsername, etSignupEmail, etSignupPassword, etSignupPasswordConfirm;
    Button btnSignup;
    TextView tvSignInLinkPart2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        etSignupPasswordConfirm = findViewById(R.id.etSignupPasswordConfirm);
        btnSignup = findViewById(R.id.btnSignup);
        tvSignInLinkPart2 = findViewById(R.id.tvSignInLinkPart2);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signUp button");
                String username = etSignupUsername.getText().toString();
                String password = etSignupPassword.getText().toString();
                signupUser(username, password);
            }
        });

        tvSignInLinkPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "Opening SignUp page", Toast.LENGTH_LONG).show(); // For debugging
                //goLoginActivity();
                Intent i = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(i);
            }
        });

        /* // Removed when Floating Action Button was removed in XML file
        // This code is kept in case of future code reference
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    private void signupUser(String username, String password) {
        Log.i(TAG, "Attempting to signUp user" + username);
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    loginUser(username, password);
                    return;
                } else {
                    // Sign up didn't succeed. Look at the ParseException to troubleshoot
                    Log.e(TAG, "Issue with signUp", e);
                    Toast.makeText(SignUpActivity.this, "Issue with signUp!", Toast.LENGTH_SHORT);
                    return;
                }
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    // TODO: better error handling so user that their password entry is invalid
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(SignUpActivity.this, "Issue with login!", Toast.LENGTH_SHORT);
                    return;
                }
                // TODO: navigate to the main activity if the user has signed in properly
                //TODO: after login, goes to movie selection
                //goCreateListActivity(); NOT FOR THIS APP
                Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT);
            }
        });
    }

    //DON'T NEED THIS FOR THIS APP
    /*
    private void goCreateListActivity() {
        Intent i = new Intent(this, CreateList.class);
        startActivity(i);
        finish(); // Finish SignUpActivity so back button doesn't lead to last SignUpActivity session
    } */

    private void goLoginActivity() {
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
        finish(); // Finish SignUpActivity so back button doesn't lead to last SignUpActivity session

    }
}