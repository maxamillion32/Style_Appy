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

import com.apporio.style_appy.Parsing.parsingforForgotPassword;

import org.w3c.dom.Text;

import views.ProgressWheel;

public class ForgotPasswordActivity extends Activity {
    LinearLayout back;
    TextView send;
    EditText email;
    public static ProgressWheel pb;
    public static ForgotPasswordActivity fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(ForgotPasswordActivity.this,R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        back = (LinearLayout)findViewById(R.id.back22);
        send = (TextView)findViewById(R.id.send);
        email = (EditText)findViewById(R.id.email);
        pb = (ProgressWheel)findViewById(R.id.pb11);
        fp=ForgotPasswordActivity.this;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)ForgotPasswordActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                if(!email.getText().toString().trim().equals("")) {
                    parsingforForgotPassword.parsingforgot(ForgotPasswordActivity.this,email.getText().toString().trim());
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "Please fill E-mail Address", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)ForgotPasswordActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                finish();
            }
        });
    }
}
