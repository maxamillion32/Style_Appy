package com.apporio.style_appy;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity {
    LinearLayout back;
    TextView addtoitem;
    EditText valuefill;
    String b = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(NoteActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        back = (LinearLayout) findViewById(R.id.back22);
        addtoitem = (TextView)findViewById(R.id.addtoitem);
        valuefill = (EditText)findViewById(R.id.edittexttoadd);
        if(getIntent().getStringExtra("activity").equals("appoint")) {
            valuefill.setText(AppointmentActivity.noteadd);
        }
        else {
            valuefill.setText(AppointmentActivitybystaff.noteadd);
        }


        addtoitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)NoteActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                if(getIntent().getStringExtra("activity").equals("appoint")) {
                    AppointmentActivity.noteadd = valuefill.getText().toString();
                }
                else {
                    AppointmentActivitybystaff.noteadd = valuefill.getText().toString();
                }

                Toast.makeText(NoteActivity.this, "Note added..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)NoteActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                finish();
            }
        });
    }
}
