package com.example.choremates;

import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class SignUpActivity extends AppCompatActivity {

    TextView login, help;
    ImageView back_arrow;
    Button create;
    DatabaseHelper db;
    EditText email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //login button bring to login page
        login = findViewById(R.id.loginT);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //back button brings them back to the main menu
        back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //open alert dialog with the instructions for the page
        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpDialog.openDialog(SignUpActivity.this,
                                      SignUpActivity.this,
                                      (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                      getResources().getString(R.string.signup_message),
                                        "Help");
            }
        });

        //database to store the registration information
        db = new DatabaseHelper(this);
        email = findViewById(R.id.emailSign);
        password = findViewById(R.id.passwordSign);
        confirmPassword = findViewById(R.id.confirmPasswordSign);
        create = findViewById(R.id.createButtonSign);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                String confirmPasswordStr = confirmPassword.getText().toString();
                if (emailStr.equals("")){
                    HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                            (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                            "Please enter an email");
                } else if (passwordStr.equals("")){
                    HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                            (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                            "Please enter a password");
                } else if (confirmPasswordStr.equals("")){
                    HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                            (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                            "Please confirm your password");
                } else {
                    if (passwordStr.equals(confirmPasswordStr)) {
                        boolean checkEmail = db.checkEmail(emailStr);
                        if (checkEmail) {
                            db.insert(emailStr, passwordStr);
                            boolean insert = db.insert(emailStr, passwordStr);
                            if (insert) {
                                HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                                        (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                        "Registered Successfully!");
                                Intent intent = new Intent (SignUpActivity.this, ChoresActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                                    (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                    "There is already an\n account with that email.");                         }
                    } else {
                        HelpDialog.openDialogNoTitle(SignUpActivity.this, SignUpActivity.this,
                                (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                "Passwords do not match");
                    }
                }
            }
        });

    }
}
