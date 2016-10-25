package com.apporio.style_appy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.style_appy.Parsing.parsingforLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import views.ProgressWheel;

public class LoginActivity extends Activity {
    LinearLayout signin;
    TextView signup, frgtpw;
    EditText email, pass;
    public static LoginActivity login;
    public static ProgressWheel pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(LoginActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = (LinearLayout) findViewById(R.id.llforsignin);
        signup = (TextView) findViewById(R.id.signup);
        frgtpw = (TextView) findViewById(R.id.frgtpw);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pw);
        pb = (ProgressWheel) findViewById(R.id.pb11);
        login = LoginActivity.this;

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                if (!email.getText().toString().trim().equals("") && !pass.getText().toString().trim().equals("")) {
                   if(isValidMail(email.getText().toString().trim())) {
                       parsingforLogin.parsingEmail(LoginActivity.this, email.getText().toString().trim(), pass.getText().toString().trim());
                   }
                    else {
                        parsingforLogin.parsingPhone(LoginActivity.this,email.getText().toString().trim(),pass.getText().toString().trim());
                   }
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill required fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
            }
        });
        frgtpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(in);
            }
        });
    }


    private boolean isValidMail(String email2) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email2);
        check = m.matches();

        return check;
    }
}