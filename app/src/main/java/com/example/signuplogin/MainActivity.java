package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername,etPassword;
    private FirebaseServices fbs;
    private Utilities utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        etUsername=findViewById(R.id.etUsernameMain);
        etPassword=findViewById(R.id.etPasswordMain);
        fbs=FirebaseServices.getInstance();
        utils = Utilities.getInstance();
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fbs.getAuth().getCurrentUser();
        if (currentUser != null)
            gotoAllRecs(null);
    }

    public void Login(View view) {
        String username = etUsername.getText().toString().toLowerCase();
        String password = etPassword.getText().toString();
        // TODO: 2- Data validation
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(this, "Username or password is missing!", Toast.LENGTH_SHORT).show();
            return;

        }

        // TODO: check email and password from Utilities
        if (!utils.verifyEmail(this, username) || !utils.CheckPassword(this, password))
        {
            Toast.makeText(this, "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 3- Check username and password with Firebase Authentication
        fbs.getAuth().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, AllRecipesActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(MainActivity.this, R.string.err_incorrect_user_password, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void gotoSignup(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
    public void gotoAddRec(View view) {
        if (fbs.getAuth().getCurrentUser().getEmail().equals("aseelabd512004@gmail.com")) {
            Intent i = new Intent(this, AddRecActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(MainActivity.this, "You're not admin. Cannot add recipies!", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoAllRecs(View view) {
        Intent i = new Intent(this, AllRecipesActivity.class);
        startActivity(i);
    }
}
