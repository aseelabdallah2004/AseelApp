package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private final Object AuthResult;
    private EditText etUsername,etPassword;
    private FirebaseServices fbs;
    private Utilities utils;

    public MainActivity(Object authResult) {
        AuthResult = authResult;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername=findViewById(R.id.etUsernameMain);
        etPassword=findViewById(R.id.etPasswordMain);
        fbs=FirebaseServices.getInstance();
        utils = Utilities.getInstance();
    }

    public void Login(View view) {
        String username = etUsername.getText().toString();
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
                            // TODO: commands if successful
                        } else {


                            Toast.makeText(MainActivity.this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });


    }
        }