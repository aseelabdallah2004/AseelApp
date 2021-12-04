package com.example.signuplogin;

import android.app.AppComponentFactory;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
enum ErrorResult{
    True,Flase,INCORERECT_ENAIL,INCORRECT_PASSWORD,EMPTY_FIELDS
}
public class Utilities {

    private static Utilities instance;

    public Utilities() {
    }

    public static Utilities getInstance() {
        if (instance == null) {
            instance = new Utilities();
        }

        return instance;
    }

    public boolean verifyEmail(AppCompatActivity activity, String email) {
        int count1 = 0;
        String[] splitstring = email.split("@");
        if (splitstring.length != 2) {
            Toast.makeText(activity, "Username or password are incorrect", Toast.LENGTH_SHORT).show();
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

    public boolean CheckPassword(AppCompatActivity activity, String password) {
        int countcap = 0, countsmall = 0, countnum = 0, countwildcard = 0, countchars = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
                countcap++;
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
                countsmall++;
            for (int j = 0; j < 9; j++) {
                if (password.charAt(i) == i)
                    countnum++;
            }
            for (int j = 0; j < 9; j++) {
                if (password.charAt(i) != i) {
                    if (password.charAt(i) < 'A' && password.charAt(i) > 'Z')
                        countwildcard++;
                }
            }
        }
        countchars = countcap + countsmall;
        if (countchars >= 8 && countchars <= 30) {
            if (countcap < 1) {
                Toast.makeText(activity, "it must be at least one captal letter!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (countsmall < 1) {
                Toast.makeText(activity, "it must be at least one captal letter!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (countnum < 1) {
                Toast.makeText(activity, "it must be at least one number from 0-9!", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (countwildcard < 1) {
                Toast.makeText(activity, "it must be at least one  wild card (@, #, $, ….)!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


}
