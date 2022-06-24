package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private FirebaseServices fbs;
    private Utilities utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        connectComponents();
    }

    private void connectComponents() {
        etUsername = findViewById(R.id.erUsernameSignup);
        etPassword = findViewById(R.id.etpasswordSignup);
        utils = Utilities.getInstance();
        fbs = FirebaseServices.getInstance();
    }
    public void signup(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty())
        {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utils.verifyEmail(this, username) || !utils.CheckPassword(this,password))
        {
            Toast.makeText(this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            return;
        }

        fbs.getAuth().createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(SignupActivity.this, AllRecipesActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(SignupActivity.this, R.string.err_firebase_general, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}