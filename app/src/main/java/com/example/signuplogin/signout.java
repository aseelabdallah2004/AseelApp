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

public class signout extends AppCompatActivity {
    private EditText etUsername,etPassword,etConfirmPassword;
    private FirebaceAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);
        etUsername=findViewById(R.id.erUsernameSignout);
        etPassword=findViewById(R.id.etpasswordSignup);
        etConfirmPassword=findViewById(R.id.etconfimpasswordSignup);
    }

    public void signout(View view) {
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        String confirmPassword=etConfirmPassword.getText().toString();
        if (username.trim().isEmpty() || password.trim().isEmpty()
                || confirmPassword.trim().isEmpty())
        {
            Toast.makeText(this, "Username or password is missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword))
        {
            Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 3- Check username and password with Firebase Authentication
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // TODO: commands if successful
                        } else {


                            Toast.makeText(this, "Username or password is empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }
    public boolean VerifyEmail(String email) {
        int count1 = 0;
        String[] splitstring = email.split("@");
        if (splitstring.length != 2) {
            Toast.makeText(signout.this, "Username or password are incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }

        String username = splitstring[0].toLowerCase();
        String domain = splitstring[1].toLowerCase();

        if (username.trim().charAt(0) < 'A' && username.trim().charAt(0) > 'Z') {

            if (username.trim().charAt(0) == '_' && username.trim().length() < 50) {
                if (domain.trim().charAt(0) < 'A' && domain.trim().charAt(0) > 'Z') {
                    if (domain.trim().charAt(0) == '_' && domain.trim().length() < 50) {
                        for (int i = 0; i < domain.length(); i++) {
                            if (domain.charAt(i) == '.')
                                count1++;
                        }
                        if (count1 > 1 && count1 < 3) {
                            if (domain.charAt(domain.length() - 1) >= 'A' && domain.charAt(domain.length() - 1) <= 'Z')
                                return true;

                        }
                    }
                }
            }
            return false;
        }
        if (domain.trim().charAt(0) < 'A' && domain.trim().charAt(0) > 'Z') {
            if (domain.trim().charAt(0) == '_' && domain.trim().length() < 50) {
                for (int i = 0; i < domain.length(); i++) {
                    if (domain.charAt(i) == '.')
                        count1++;
                }
                if (count1 > 1 && count1 < 3) {
                    if (domain.charAt(domain.length() - 1) >= 'A' && domain.charAt(domain.length() - 1) <= 'Z')
                        return true;

                }
            }
        }
        return false;
    }
    public boolean CheckPassword(String password){
        int countcap=0 , countsmall=0,countnum=0,countwildcard=0,countchars=0;
        for(int i=0; i<password.length();i++){
            if (password.charAt(i)>='A'&&password.charAt(i)<='Z')
                countcap++;
            if (password.charAt(i)>='a'&&password.charAt(i)<='z')
                countsmall++;
            for(int j=0;j<9;j++){
                if (password.charAt(i)==i)
                    countnum++;}
            for( int j=0;j<9;j++){
                if (password.charAt(i)!=i){
                    if (password.charAt(i)<'A'&&password.charAt(i)>'Z')
                        countwildcard++;}
            }
        }
        countchars=countcap+countsmall;
        if (countchars>=8&&countchars<=30){
            if (countcap<1){
                Toast.makeText(signout.this, "it must be at least one captal letter!", Toast.LENGTH_SHORT).show();
                return false;}
            if (countsmall<1) {
                Toast.makeText(signout.this, "it must be at least one small letter!", Toast.LENGTH_SHORT).show();
                return false;}
            if (countnum<1){
                Toast.makeText(signout.this, "it must be at least one number from 0-9!", Toast.LENGTH_SHORT).show();
                return false;}
            if (countwildcard<1){
                Toast.makeText(signout.this, "it must be at least one  wild card (@, #, $, ….)!", Toast.LENGTH_SHORT).show();
                return false;}
        }
        return true;
    }

}