package com.apporio.style_appy.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.AccountsFragment;
import com.apporio.style_appy.Setter_Getter.Accountinfo_Outter;
import com.apporio.style_appy.Setter_Getter.Accountinfoshow2_Outter;
import com.apporio.style_appy.Setter_Getter.Accountinfoshow_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_accountshow;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 05-08-2016.
 */
public class parsingforaccountinfo {
    public static StringRequest sr;
    public static SharedPreferences prefs2;
    public static RequestQueue queue;
    public static List<Inner_accountshow> accountshows = new ArrayList<>();
    public static ArrayList<String> userid = new ArrayList<String>();
    public static ArrayList<String> companyid = new ArrayList<String>();
    public static ArrayList<String> userimeino = new ArrayList<String>();
    public static ArrayList<String> userdatejoin = new ArrayList<String>();
    public static ArrayList<String> username = new ArrayList<String>();
    public static ArrayList<String> useremail = new ArrayList<String>();
    public static ArrayList<String> userphn = new ArrayList<String>();
    public static ArrayList<String> userdoorno = new ArrayList<String>();
    public static ArrayList<String> userstreetno = new ArrayList<String>();
    public static ArrayList<String> usertown = new ArrayList<String>();
    public static ArrayList<String> userpostcode = new ArrayList<String>();
    public static ArrayList<String> specialdirectn = new ArrayList<String>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Accountinfo +prefs2.getString("userid","");
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                AccountsFragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

//                    catname.clear();
//                    catid.clear();


                    Accountinfoshow_Outter received2 = new Accountinfoshow_Outter();
                    received2 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received2.status.equals("1")) {
                        Accountinfoshow2_Outter received3 = new Accountinfoshow2_Outter();
                        received3 = gson.fromJson(response, Accountinfoshow2_Outter.class);

                        String username = received3.data.get(0).user_name;
                        String useremail = received3.data.get(0).user_email;
                        String userphnno = received3.data.get(0).user_phone;
                        String userpass = received3.data.get(0).user_password;


                        AccountsFragment.name.setText(""+username);
                        AccountsFragment.email.setText(""+useremail);
                        AccountsFragment.phoneno.setText(""+userphnno);
                        AccountsFragment.password.setText(""+userpass);

                    } else {


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                //AccountsFragment.pb.setVisibility(View.GONE);

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
//        pd.show();
        AccountsFragment.pb.setVisibility(View.VISIBLE);

    }

    public static void parsingsave(final Context c, String name, String email, String phoneno, String trim) {
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Accountsave +prefs2.getString("userid","")+
                Apis_url.Accountsave1+name+Apis_url.Accountsave2+email+Apis_url.Accountsave3+phoneno+
                Apis_url.Accountsave4+trim+Apis_url.Accountsave5;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                pd.dismiss();
                AccountsFragment.pb.setVisibility(View.GONE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

//                    catname.clear();
//                    catid.clear();


                    Accountinfo_Outter received2 = new Accountinfo_Outter();
                    received2 = gson.fromJson(response, Accountinfo_Outter.class);

                    if (received2.status.equals("1")) {
                        Toast.makeText(c, ""+received2.message, Toast.LENGTH_SHORT).show();

                    } else {


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                // AccountsFragment.pb.setVisibility(View.GONE);
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
//        pd.show();
        AccountsFragment.pb.setVisibility(View.VISIBLE);
    }
}
