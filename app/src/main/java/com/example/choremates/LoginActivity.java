package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    ImageView back_arrow;
    TextView forgot_password;
    TextView help;
    TextView register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back_arrow = findViewById(R.id.back_arrow);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forgot_password = findViewById(R.id.forgot_password_Text);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpDialog.openDialog("Help", "Log in using the email and password that you signed up with. If you forgot your password, then click forgot password.", LoginActivity.this, LoginActivity.this);
            }
        });

        register = findViewById(R.id.register2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        login = findViewById(R.id.loginButton2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, ChoresActivity.class);
                startActivity(intent);
            }
        });
    }


}
