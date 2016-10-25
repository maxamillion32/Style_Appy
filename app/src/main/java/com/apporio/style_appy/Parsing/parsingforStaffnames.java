package com.apporio.style_appy.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
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
import com.apporio.style_appy.AppointmentActivitybystaff;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.Inner_Staff_detail;
import com.apporio.style_appy.Setter_Getter.Inner_Staffservicebystaff;
import com.apporio.style_appy.Setter_Getter.Staff_detail_Outter;
import com.apporio.style_appy.Setter_Getter.Staffservicebystaff_outter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.apporio.style_appy.StaffspecificActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 23-08-2016.
 */
public class parsingforStaffnames {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static List<Inner_Staffservicebystaff> Staffnames = new ArrayList<>();
    public static ArrayList<String> staffname = new ArrayList<String>();
    public static ArrayList<String> staffid = new ArrayList<String>();
    public static ArrayList<String> staffdescp = new ArrayList<String>();
    public static ArrayList<String> staffimage = new ArrayList<String>();
    public static ArrayList<String> staffposition = new ArrayList<String>();



}
