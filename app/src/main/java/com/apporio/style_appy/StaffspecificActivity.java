package com.apporio.style_appy;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apporio.style_appy.Parsing.parsingforStaffdetail;

import views.ProgressWheel;

public class StaffspecificActivity extends Activity {
    public static  LinearLayout llfrservice;
    String [] catname= {"Service 1","Service 2","Service 3","Service 4","Service 5",
            "Service 6","Service 7",};
    LinearLayout back;
    public static ImageView dpimage;
    public static TextView header, descp,position;
    public  static ProgressWheel pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(StaffspecificActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffspecific);
        llfrservice = (LinearLayout) findViewById(R.id.llfrservice);
        back = (LinearLayout) findViewById(R.id.back22);
        pb = (ProgressWheel)findViewById(R.id.pb11);
        dpimage = (ImageView)findViewById(R.id.dpimage);
        header = (TextView)findViewById(R.id.staffname);
        descp = (TextView)findViewById(R.id.descp);
        position = (TextView)findViewById(R.id.staffposition);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        parsingforStaffdetail.parsing(StaffspecificActivity.this,getIntent().getStringExtra("staffid"));




    }
    public static View serviceview(Context c, int layout_name, String s1) {

        LayoutInflater layoutInflater =
                (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final TextView tb = (TextView) addView.findViewById(R.id.desz);


        tb.setText("" + s1);



        return addView;
    }

}

