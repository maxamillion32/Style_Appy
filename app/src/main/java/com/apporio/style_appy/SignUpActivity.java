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
import android.widget.Toast;

import com.apporio.style_appy.Parsing.parsingforSignup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import views.ProgressWheel;

public class SignUpActivity extends Activity {
   LinearLayout back;
    LinearLayout Signup;
    EditText email,pass,name,number;
    public static SignUpActivity signup;
    public static ProgressWheel pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(SignUpActivity.this,R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        back = (LinearLayout)findViewById(R.id.back22);
        Signup = (LinearLayout)findViewById(R.id.signup);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        number = (EditText)findViewById(R.id.phnno);
        pb = (ProgressWheel) findViewById(R.id.pb11);
        signup = SignUpActivity.this;


        Signup.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                    InputMethodManager imm = (InputMethodManager)SignUpActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                    if(!email.getText().toString().trim().equals("")&&!pass.getText().toString().trim().equals("")&&
                            !name.getText().toString().trim().equals("")&&!number.getText().toString().trim().equals("")) {
                        if(isValidMail(email.getText().toString().trim())) {
                            if(isValidMobile(number.getText().toString().trim())) {
                                parsingforSignup.parsing(SignUpActivity.this, name.getText().toString().trim(),
                                        email.getText().toString().trim(), number.getText().toString().trim(),
                                        pass.getText().toString().trim());
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Please enter valid phone number.", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Please enter valid e-mail id.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Please fill required fields.", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
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
    private boolean isValidMobile(String phone2)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone2))
        {
            if(phone2.length() < 6 || phone2.length() > 13)
            {
                check = false;

            }
            else
            {
                check = true;
            }
        }
        else
        {
            check=false;
        }
        return check;
    }
}
