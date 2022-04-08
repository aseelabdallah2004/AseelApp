package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername,etPassword;
    private FirebaseServices fbs;
    private Utilities utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectComponents();
    }

    private void connectComponents() {
        etUsername = findViewById(R.id.etUsernameMain);
        etPassword = findViewById(R.id.etPasswordMain);
        utils = Utilities.getInstance();
        fbs = FirebaseServices.getInstance();

    }
    public void login(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty())
        {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utils.verifyEmail(this,username) || !utils.CheckPassword(this,password))
        {
            Toast.makeText(this, R.string.err_incorrect_user_password, Toast.LENGTH_SHORT).show();
            return;
        }

        fbs.getAuth().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, AllRecActivity.class);
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
        Intent i = new Intent(this, AddRecActivity.class);
        startActivity(i);
    }

    public void gotoAllRecs(View view) {
        Intent i = new Intent(this, AllRecActivity.class);
        startActivity(i);
    }
}

