package com.aviral.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    ImageView gotoLoginImg;
    Button register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gotoLoginImg = findViewById(R.id.goToLoginImg);
        register = findViewById(R.id.registerUser);
        username = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);

        gotoLoginImg.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {

                String usernameText = String.valueOf(username.getText());
                String passwordText = String.valueOf(password.getText());

                if (!usernameText.equals("") && !passwordText.equals("")) {
                    checkAndRegisterUser(usernameText, passwordText);
                } else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
        });
    }

    private void checkAndRegisterUser(String username, String password) {

        SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
        SharedPreferences.Editor detailsEditor = loginDetails.edit();

        detailsEditor.putString("username", username);
        detailsEditor.putString("password", password);
        detailsEditor.putBoolean("isLogin", true);
        detailsEditor.apply();

        Log.d("User", "User Registered Successfully");

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

}