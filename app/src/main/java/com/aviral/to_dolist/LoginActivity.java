package com.aviral.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView closeLogin, goToRegisterByImg;
    TextView gotoRegisterText, gotoRegister;
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gotoRegisterText = findViewById(R.id.goToRegisterText);
        gotoRegister = findViewById(R.id.goToRegister);
        closeLogin = findViewById(R.id.closeLogin);
        goToRegisterByImg = findViewById(R.id.goToRegisterByImg);
        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        login = findViewById(R.id.login);

        gotoRegisterText.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        goToRegisterByImg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        gotoRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        closeLogin.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(view -> {

            String usernameText = String.valueOf(username.getText());
            String passwordText = String.valueOf(password.getText());

            if (!usernameText.equals("") && !passwordText.equals("")) {
                login(usernameText, passwordText);
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void login(String username, String password) {

        SharedPreferences loginPreference = getSharedPreferences("loginDetails", MODE_PRIVATE);
        SharedPreferences.Editor loginEditor = loginPreference.edit();

        String getUsername = loginPreference.getString("username", "Invalid");
        String getPassword = loginPreference.getString("password", "Invalid");

        if (username.equals(getUsername) && password.equals(getPassword)) {
            Log.d("User", "Sign In Successfully");
            loginEditor.putBoolean("isLogin", true);
            loginEditor.apply();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            Log.d("User", getUsername);
            Log.d("User", getPassword);
        }

    }
}