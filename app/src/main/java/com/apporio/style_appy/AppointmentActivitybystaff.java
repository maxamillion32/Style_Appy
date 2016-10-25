package com.apporio.style_appy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Setter_Getter.Accountinfoshow_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_Staffservicebystaff;
import com.apporio.style_appy.Setter_Getter.Inner_location_service;
import com.apporio.style_appy.Setter_Getter.Inner_timeservicebydate;
import com.apporio.style_appy.Setter_Getter.Loc_service_outter;
import com.apporio.style_appy.Setter_Getter.Staffservicebystaff_outter;
import com.apporio.style_appy.Setter_Getter.Timeservicebydate_outter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import views.ProgressWheel;

public class AppointmentActivitybystaff extends Activity implements DatePickerDialog.OnDateSetListener {
    LinearLayout back;
    LinearLayout llforreview;
    TextView addnote, textloc, textdate, texttime, textstaff,name,descp,rates,duration;
    public static String noteadd;
    String radiobutton = "";
    String radiobutton2 = "";
    String radiobutton3 = "";
    String locationlat = "";
    String locationlong = "";
    String staffimgs = "";
    ArrayList<String> heads22;
    ArrayList<String> heads222;
    ArrayList<String> heads2222;
    private ArrayList<String> timeid22;
    private int myear;
    private int mmonth;
    private int mday;

    String locationidvalue, staffidvalue, timeidvalue,datevalue;

    private static final String[] bankHolidays = {"24/07/2016", "27/07/2016", "02/08/2016", "20/08/2016"};
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Set<Calendar> holidayDates;
    public LinearLayout llforlocation, llfordate, llforradiobuttonadding, llfrtime, llfrstaff;
    //    String data[] = {"Location 1", "Location 2", "Location 3", "Location 4"};
    String data2[] = {"Staff 1", "Staff 2", "Staff 3", "Staff 4"};
    String data3[] = {"8:00 am", "8:15 am", "8:30 am", "8:45 am"};
    public static ArrayList<Boolean> checkedss = new ArrayList<>();
    public static ArrayList<Boolean> checkedss2 = new ArrayList<>();
    public static ArrayList<Boolean> checkedss22 = new ArrayList<>();
    public static AppointmentActivitybystaff apc;
    public static ProgressWheel pb;
    public  String productid, productname, productdescp, productrate,productduration;


    /////////////////
    public static StringRequest sr;
    public static SharedPreferences prefs2;
    public static RequestQueue queue;
    public List<Inner_location_service> locationdetails = new ArrayList<>();
    public  ArrayList<String> location_id = new ArrayList<String>();
    public  ArrayList<String> location_name = new ArrayList<String>();
    public ArrayList<String> location_address = new ArrayList<String>();
    public  ArrayList<String> location_lat = new ArrayList<String>();
    public ArrayList<String> location_long = new ArrayList<String>();
    ///////////////
    public  List<Inner_Staffservicebystaff> Staffnames = new ArrayList<>();
    public  ArrayList<String> staffname = new ArrayList<String>();
    public  ArrayList<String> staffid = new ArrayList<String>();
    public  ArrayList<String> staffimg = new ArrayList<String>();
    private ArrayList<String> location_id22;
    private ArrayList<String> staffid22;
    /////////////
    String locationaddress = "";
    public List<Inner_timeservicebydate> timedetails = new ArrayList<>();
    public  ArrayList<String> timeid = new ArrayList<String>();
    public  ArrayList<String> timestrt = new ArrayList<String>();
    public  ArrayList<String> timefinish = new ArrayList<String>();
    //////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(AppointmentActivitybystaff.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        String type =   ""+ getIntent().getStringExtra("bookby");
        Log.e("appoint", "" + getIntent().getStringExtra("bookby"));
        setContentView(R.layout.appointmentactivitystaff);

        apc = AppointmentActivitybystaff.this;
        back = (LinearLayout) findViewById(R.id.back22);
        llforreview = (LinearLayout) findViewById(R.id.revwll);
        addnote = (TextView) findViewById(R.id.sr);
        textloc = (TextView) findViewById(R.id.baseloc);
        textdate = (TextView) findViewById(R.id.basedate);
        texttime = (TextView) findViewById(R.id.basetime);
        textstaff = (TextView) findViewById(R.id.basestaff);
        name = (TextView) findViewById(R.id.P_name);
        rates = (TextView) findViewById(R.id.P_rate);
        duration = (TextView) findViewById(R.id.P_duration);
        descp = (TextView) findViewById(R.id.P_descp);
        llforlocation = (LinearLayout) findViewById(R.id.llloc);
        llfordate = (LinearLayout) findViewById(R.id.lldate);
        llfrtime = (LinearLayout) findViewById(R.id.lltime);
        llfrstaff = (LinearLayout) findViewById(R.id.llstaff);
        pb=(ProgressWheel)findViewById(R.id.pb11);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(AppointmentActivitybystaff.this);
        try {
            productid = getIntent().getStringExtra("product_id");
            productname = getIntent().getStringExtra("product_name");
            productdescp = getIntent().getStringExtra("product_descp");
            productrate = getIntent().getStringExtra("product_rate");
            productduration = getIntent().getStringExtra("duration");
        } catch (Exception e) {
            e.printStackTrace();
        }
        parsingloc(AppointmentActivitybystaff.this, productid);
        name.setText(""+productname);
        descp.setText(""+productdescp);
        rates.setText("£ " + productrate);
        duration.setText("Duration - "+productduration);

        llforlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               showselectbase();
                for (int i = 0; i < location_name.size(); i++) {
                    checkedss.add(i, false);
                }
                showlocationdialog(location_name, textloc,location_id);
            }
        });
        llfrtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               showselectbase();
                if (textdate.getText().toString().trim().equals("")) {
                    Toast.makeText(AppointmentActivitybystaff.this, "Please select date first !!!", Toast.LENGTH_SHORT).show();
                } else {
                    parsingtime(AppointmentActivitybystaff.this, productid, locationidvalue, datevalue);
                }
            }
        });

        llfrstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textloc.getText().toString().trim().equals("")) {
                    Toast.makeText(AppointmentActivitybystaff.this, "Please select location first !!!", Toast.LENGTH_SHORT).show();
                } else {
                    parsingstaff(AppointmentActivitybystaff.this, productid, locationidvalue);
                }
            }
        });
        llforreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textloc.getText().toString().trim().equals("")||
                        textstaff.getText().toString().trim().equals("")||
                        textdate.getText().toString().trim().equals("")||
                        texttime.getText().toString().trim().equals("")){
                    Toast.makeText(AppointmentActivitybystaff.this, "Please fill required fields !!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent in = new Intent(AppointmentActivitybystaff.this, ReviewActivity.class);
                    in.putExtra("fragment", "service");
                    in.putExtra("activity", "appoint2");
                    in.putExtra("user_id",prefs2.getString("userid",""));
                    in.putExtra("client_id", Apis_url.Company_id);
                    in.putExtra("category_id",getIntent().getStringExtra("category_id"));
                    in.putExtra("trt_id", productid);
                    in.putExtra("loc_id",locationidvalue);
                    in.putExtra("date",datevalue);
                    in.putExtra("time",timeidvalue);
                    in.putExtra("staffid",staffidvalue);
                    in.putExtra("orderprice",productrate);
                    in.putExtra("ordertype","service");
                    in.putExtra("ordernote",""+noteadd);

                    in.putExtra("title",productname);
                    in.putExtra("descp",productdescp);
                    in.putExtra("duration",productduration);
                    in.putExtra("loc_name",textloc.getText().toString().trim());
                    in.putExtra("loc_address",locationaddress);
                    in.putExtra("staff_name",textstaff.getText().toString().trim());
                    in.putExtra("staff_img",staffimgs);
                    in.putExtra("loc_lat",locationlat);
                    in.putExtra("loc_long",locationlong);
                    startActivity(in);
                }
            }
        });
        llfordate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// if (holidayDates == null) {
//                holidayDates = new HashSet<Calendar>();
//                for (int i = 0; i < bankHolidays.length; i++) {
//                    Calendar c = Calendar.getInstance();
//                    Date date = null;
//                    try {
//                        date = sdf.parse(bankHolidays[i]);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    c.setTime(date);
//                    holidayDates.add(c);
//                }
//                Calendar[] vcvcvc = holidayDates.toArray(new Calendar[holidayDates.size()]);

                if (textstaff.getText().toString().trim().equals("")) {
                    Toast.makeText(AppointmentActivitybystaff.this, "Please select staff first !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            AppointmentActivitybystaff.this,
                            now.get(now.YEAR),
                            now.get(now.MONTH),
                            now.get(now.DAY_OF_MONTH)

                    );


                    Calendar c = Calendar.getInstance();
                    dpd.setMinDate(c);
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 60);
//
//                dpd.setMaxDate(cal);


                    //dpd.setSelectableDays(vcvcvc);
                    dpd.setAccentColor(Color.parseColor("#696969"));

                    dpd.show(getFragmentManager(), "Datepickerdialog");
                }
            }
        });

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AppointmentActivitybystaff.this, NoteActivity.class);
                in.putExtra("activity","appoint2");
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

    private void showlocationdialog(ArrayList<String> data, final TextView textloc, ArrayList<String> location_id) {

        heads22= new ArrayList<>();
            location_id22= new ArrayList<>();
        heads22 = data;
        location_id22 = location_id;
        final Dialog dialog = new Dialog(AppointmentActivitybystaff.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogformultipleselect);
        llforradiobuttonadding = (LinearLayout) dialog.findViewById(R.id.llforrd);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);
        TextView head = (TextView) dialog.findViewById(R.id.titless);
        head.setText("Location");
        for (int i = 0; i < heads22.size(); i++) {

            llforradiobuttonadding.addView(addviewradiobutton(R.layout.layouttoaddinradiogroup, heads22.get(i), i, checkedss.get(i), location_id22));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        textloc.setText("" + radiobutton);
                        textstaff.setText("");
                        textdate.setText("");
                        texttime.setText("");

                        dialog.dismiss();

                    }
                }
                        , 500);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        dialog.dismiss();
                    }
                }
                        , 500);
            }
        });


        dialog.show();


    }

    private void showlocationdialog2(ArrayList<String> data2, final TextView textloc2, ArrayList<String> staffid) {

            heads222= new ArrayList<>();
            staffid22= new ArrayList<>();
        heads222 = data2;
        staffid22 = staffid;
        final Dialog dialog = new Dialog(AppointmentActivitybystaff.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogformultipleselect);
        llforradiobuttonadding = (LinearLayout) dialog.findViewById(R.id.llforrd);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);
        TextView head = (TextView) dialog.findViewById(R.id.titless);
        head.setText("Staff");
        for (int i = 0; i < heads222.size(); i++) {

            llforradiobuttonadding.addView(addviewradiobutton2(R.layout.layouttoaddinradiogroup, heads222.get(i), i, checkedss2.get(i), staffid22));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        textloc2.setText("" + radiobutton2);
                        textdate.setText("");
                        texttime.setText("");

                        dialog.dismiss();

                    }
                }
                        , 500);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        dialog.dismiss();
                    }
                }
                        , 500);
            }
        });


        dialog.show();


    }

    private void showlocationdialog3(ArrayList<String> data3, final TextView texttime, ArrayList<String> timeid) {

        heads2222= new ArrayList<>();
        timeid22= new ArrayList<>();
        heads2222 = data3;
        timeid22 = timeid;
        final Dialog dialog = new Dialog(AppointmentActivitybystaff.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogformultipleselect);
        llforradiobuttonadding = (LinearLayout) dialog.findViewById(R.id.llforrd);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);
        TextView head = (TextView) dialog.findViewById(R.id.titless);
        head.setText("Time");
        for (int i = 0; i < heads2222.size(); i++) {

            llforradiobuttonadding.addView(addviewradiobutton3(R.layout.layouttoaddinradiogroup, heads2222.get(i), i, checkedss22.get(i), timeid22));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        texttime.setText("" + radiobutton3);


                        dialog.dismiss();

                    }
                }
                        , 500);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        dialog.dismiss();
                    }
                }
                        , 500);
            }
        });


        dialog.show();


    }


    private View addviewradiobutton(int layout_name, String s1, int i, boolean checkedss2, final ArrayList<String> locationid) {

        LayoutInflater layoutInflater =
                (LayoutInflater) AppointmentActivitybystaff.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final RadioButton cb = (RadioButton) addView.findViewById(R.id.rdb);

        cb.setText("" + s1);

        cb.setChecked(checkedss2);
        cb.setTag(i);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = (int) buttonView.getTag();
                if (cb.getText().toString().equals("")) {

                } else {
                    radiobutton = cb.getText().toString();
                    locationidvalue = locationid.get(pos);
                    locationaddress = location_address.get(pos);
                    locationlat = location_lat.get(pos);
                    locationlong = location_long.get(pos);

                }
//                size_idd = sizeid.get(pos);
                for (int i = 0; i < heads22.size(); i++) {
                    checkedss.add(i, false);
                }
                checkedss.add(pos, isChecked);
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding.removeAllViews();
                for (int i = 0; i < heads22.size(); i++) {

                    llforradiobuttonadding.addView(addviewradiobutton(R.layout.layouttoaddinradiogroup, heads22.get(i), i, checkedss.get(i), location_id));
                }

            }
        });


        return addView;
    }

    private View addviewradiobutton2(int layout_name, String s21, int i, boolean checkedss22, final ArrayList<String> staffid22) {

        LayoutInflater layoutInflater =
                (LayoutInflater) AppointmentActivitybystaff.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final RadioButton cb = (RadioButton) addView.findViewById(R.id.rdb);

        cb.setText("" + s21);

        cb.setChecked(checkedss22);
        cb.setTag(i);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = (int) buttonView.getTag();
                if (cb.getText().toString().equals("")) {

                } else {
                    radiobutton2 = cb.getText().toString();
                    staffidvalue = staffid.get(pos);
                    staffimgs=staffimg.get(pos);
                }
//                size_idd = sizeid.get(pos);
                for (int i = 0; i < heads222.size(); i++) {
                    checkedss2.add(i, false);
                }
                checkedss2.add(pos, isChecked);
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding.removeAllViews();
                for (int i = 0; i < heads222.size(); i++) {

                    llforradiobuttonadding.addView(addviewradiobutton2(R.layout.layouttoaddinradiogroup, heads222.get(i), i, checkedss2.get(i), staffid22));
                }
            }
        });
        return addView;
    }



    private View addviewradiobutton3(int layout_name, String s213, int i, boolean checkedss222, final ArrayList<String> timeid22) {

        LayoutInflater layoutInflater =
                (LayoutInflater) AppointmentActivitybystaff.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final RadioButton cb = (RadioButton) addView.findViewById(R.id.rdb);

        cb.setText("" + s213);

        cb.setChecked(checkedss222);
        cb.setTag(i);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = (int) buttonView.getTag();
                if (cb.getText().toString().equals("")) {

                } else {
                    radiobutton3 = cb.getText().toString();
                    timeidvalue = cb.getText().toString();
                }
//                size_idd = sizeid.get(pos);
                for (int i = 0; i < heads2222.size(); i++) {
                    checkedss22.add(i, false);
                }
                checkedss22.add(pos, isChecked);
                //Toast.makeText(Add_to_CartActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                llforradiobuttonadding.removeAllViews();
                for (int i = 0; i < heads2222.size(); i++) {

                    llforradiobuttonadding.addView(addviewradiobutton3(R.layout.layouttoaddinradiogroup, heads2222.get(i), i, checkedss22.get(i), timeid22));
                }
            }
        });
        return addView;
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        myear = year;
        mmonth = monthOfYear + 1;
        mday = dayOfMonth;
        texttime.setText("");
        datevalue = "" +myear  + "-" + (mmonth) + "-" + mday;
        textdate.setText("" + mday + "/" + (mmonth) + "/" + myear);


    }
    public void parsingloc(final Context c,String serviceid) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Locationservice+serviceid;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                pd.dismiss();
               pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    location_id.clear();
                    location_name.clear();
                    location_address.clear();
                    location_lat.clear();
                    location_long.clear();



                    Accountinfoshow_Outter received2 = new Accountinfoshow_Outter();
                    received2 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received2.status.equals("1")) {
                        Loc_service_outter received3 = new Loc_service_outter();
                        received3 = gson.fromJson(response, Loc_service_outter.class);

                        locationdetails = received3.data_location;
                        for(int i=0;i<locationdetails.size();i++){
                            location_id.add(locationdetails.get(i).location_id);
                            location_name.add(locationdetails.get(i).location_name);
                            location_address.add(locationdetails.get(i).location_address);
                            location_lat.add(locationdetails.get(i).location_lat);
                            location_long.add(locationdetails.get(i).location_long);
                        }




                    } else {
                        Toast.makeText(AppointmentActivitybystaff.this, "No location found !!!", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error",""+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                //Opening_Hours.pb.setVisibility(View.GONE);
                pb.setVisibility(View.GONE);
                Log.e("errrr", "" + error.toString());
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
//        pd.show();
        pb.setVisibility(View.VISIBLE);


    }
    public  void parsingstaff(final Context c, String serviceid, String locationid) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Staffservicebystaff+serviceid+"/"+locationid;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

              pb.setVisibility(View.GONE);


                staffname.clear();
                staffid.clear();
                staffimg.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    Accountinfoshow_Outter received22 = new Accountinfoshow_Outter();
                    received22 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received22.status.equals("1")) {
                    Staffservicebystaff_outter received2 = new Staffservicebystaff_outter();
                    received2 = gson.fromJson(response, Staffservicebystaff_outter.class);

                    if (received2.status.equals("1")) {
                        Staffnames=received2.data_staff;

                        for (int i = 0; i < Staffnames.size(); i++)
                        {

                            staffname.add(Staffnames.get(i).staff_name);
                            staffid.add(Staffnames.get(i).staff_id);
                            staffimg.add(Staffnames.get(i).staff_img);

                        }
                        for (int i = 0; i < staffname.size(); i++) {
                            checkedss2.add(i, false);
                        }
                        showlocationdialog2(staffname, textstaff,staffid);

                    } else {

                    }
                    } else {
                        Toast.makeText(AppointmentActivitybystaff.this, "No staff members found !!!", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("err",""+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
             pb.setVisibility(View.GONE);
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);

        pb.setVisibility(View.VISIBLE);
    }
    public  void parsingtime(final Context c, String serviceid, String locationid,String datevalue) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Timeslotservicebystaff+serviceid+"/"+locationid+"/"+staffidvalue+"/"+datevalue;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                pb.setVisibility(View.GONE);

                timeid.clear();
                timestrt.clear();
                timefinish.clear();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    Accountinfoshow_Outter received22 = new Accountinfoshow_Outter();
                    received22 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received22.status.equals("1")) {

                    Timeservicebydate_outter received2 = new Timeservicebydate_outter();
                    received2 = gson.fromJson(response, Timeservicebydate_outter.class);

                    if (received2.status.equals("1")) {
                        timedetails=received2.data_time;

                        for (int i = 0; i < timedetails.size(); i++)
                        {

                            timeid.add(timedetails.get(i).availability_id);
                            if(!timestrt.contains(timedetails.get(i).availability_start)) {
                                timestrt.add(timedetails.get(i).availability_start);
                            }
//                            timestrt.add(timedetails.get(i).availability_start);
                            timefinish.add(timedetails.get(i).availability_finish);
                        }
                        for (int i = 0; i < timeid.size(); i++) {
                            checkedss22.add(i, false);
                        }
                        showlocationdialog3(timestrt, texttime, timeid);

                    } else {

                    }
                } else {
                    Toast.makeText(AppointmentActivitybystaff.this, "No time slots found !!!", Toast.LENGTH_SHORT).show();

                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                pb.setVisibility(View.GONE);
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                Log.e("Sucess", "" + error.toString());
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);

        pb.setVisibility(View.VISIBLE);
    }
}