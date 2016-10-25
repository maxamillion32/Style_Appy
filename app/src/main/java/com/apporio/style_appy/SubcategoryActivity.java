package com.apporio.style_appy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.apporio.style_appy.Adapter.SubcategoryAdapter;
import com.apporio.style_appy.Parsing.parsingforAllServices;
//import com.apporio.style_appy.Parsing.parsingforSubcategory;

import views.ProgressWheel;

public class SubcategoryActivity extends Activity {
    public static  ListView lv;

    LinearLayout back;

    LinearLayout cartll;
    public static ProgressWheel pb;
    String category_idss;
    public static TextView header,totalprice;
    public static SubcategoryActivity spc;
    String [] catname= {"Arms","Chest","Back","Legs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(SubcategoryActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        lv=(ListView)findViewById(R.id.listforspecific);
        back=(LinearLayout)findViewById(R.id.back22);
        pb=(ProgressWheel)findViewById(R.id.pb11);
        header = (TextView) findViewById(R.id.header);
        spc=SubcategoryActivity.this;
        category_idss = getIntent().getStringExtra("category_id");
        header.setText("" + getIntent().getStringExtra("header").toUpperCase());
        //lv.setAdapter(new SubcategoryAdapter(SubcategoryActivity.this, catname));
//       parsingforSubcategory.parsing(spc, category_idss);
        parsingforAllServices.parsing(spc,category_idss);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in;
                if (getIntent().getStringExtra("bookby").equals("bookbydate")) {
                     in = new Intent(SubcategoryActivity.this, AppointmentActivity.class);
                } else {
                     in = new Intent(SubcategoryActivity.this, AppointmentActivitybystaff.class);
                }

                in.putExtra("bookby", getIntent().getStringExtra("bookby"));
                Log.e("tetetet2", "" + getIntent().getStringExtra("bookby"));
                in.putExtra("category_id",getIntent().getStringExtra("category_id")+"");
                in.putExtra("product_id", parsingforAllServices.service_id.get(position));
                in.putExtra("product_name", parsingforAllServices.servicename.get(position));
                in.putExtra("product_descp", parsingforAllServices.servicedescp.get(position));
                in.putExtra("product_rate", parsingforAllServices.serviceprice.get(position));
                in.putExtra("duration", parsingforAllServices.serviceduration.get(position));
                startActivity(in);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
