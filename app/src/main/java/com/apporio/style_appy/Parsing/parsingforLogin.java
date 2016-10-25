package com.apporio.style_appy.Parsing;

import android.content.Context;
import android.content.Intent;
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
import com.apporio.style_appy.LoginActivity;
import com.apporio.style_appy.MainActivity;
import com.apporio.style_appy.Setter_Getter.Inner_login;
import com.apporio.style_appy.Setter_Getter.Login_Outter;
import com.apporio.style_appy.Setter_Getter.Login_Outter2;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by saifi45 on 5/7/2016.
 */
public class parsingforLogin {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;




    public static void parsingEmail(final Context c,String Username, String Password) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Login_email +Username+Apis_url.Login_email2+Password;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                LoginActivity.pb.setVisibility(View.GONE);
//                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();



                    Login_Outter received2 = new Login_Outter();
                    received2 = gson.fromJson(response, Login_Outter.class);

                    if (received2.status.equals("1")) {

                        Login_Outter2 received3 = new Login_Outter2();
                        received3 = gson.fromJson(response, Login_Outter2.class);

                        Toast.makeText(c, "Welcome "+received3.userinfo.get(0).user_name, Toast.LENGTH_SHORT).show();
                        String userid = received3.userinfo.get(0).user_id;
                           String username = received3.userinfo.get(0).user_name;
                            String useremail = received3.userinfo.get(0).user_email;
                            String userphnno = received3.userinfo.get(0).user_phone;
//                            String doorno = received3.userinfo.get(0).user_door_number;
//                            String streetno = received3.userinfo.get(0).user_street_number;
//                            String town = received3.userinfo.get(0).user_town;
//                            String postcode = received3.userinfo.get(0).user_post_code;
//                            String specialdirection = received3.userinfo.get(0).user_special_directions;
                        Log.e("dfd",""+userid);
                        edit2 = prefs2.edit();
                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("userid", "" + userid);
                        edit2.putString("username",""+username);
                        edit2.putString("useremail",""+useremail);
                        edit2.putString("userphnno",""+userphnno);
//                        edit2.putString("doorno",""+doorno);
//                        edit2.putString("streetno",""+streetno);
//                        edit2.putString("town",""+town);
//                        edit2.putString("postcode",""+postcode);
//                        edit2.putString("specialdirection", "" + specialdirection);

                        edit2.commit();
                        Intent in = new Intent(c, MainActivity.class);
                        c.startActivity(in);
                       LoginActivity.login.finish();

                    } else {

                        Toast.makeText(c, " "+received2.message, Toast.LENGTH_SHORT).show();
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                LoginActivity.pb.setVisibility(View.GONE);
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
//        pd.show();
        LoginActivity.pb.setVisibility(View.VISIBLE);

    }


    public static void parsingPhone(final Context c,String Phone, String Password) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Login_phone +Phone+Apis_url.Login_phone2+Password;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                LoginActivity.pb.setVisibility(View.GONE);
//                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();



                    Login_Outter received2 = new Login_Outter();
                    received2 = gson.fromJson(response, Login_Outter.class);

                    if (received2.status.equals("1")) {
                        Login_Outter2 received3 = new Login_Outter2();
                        received3 = gson.fromJson(response, Login_Outter2.class);
                        Toast.makeText(c, "Welcome "+received3.userinfo.get(0).user_name, Toast.LENGTH_SHORT).show();
                        String userid = received3.userinfo.get(0).user_id;
                        String username = received3.userinfo.get(0).user_name;
                        String useremail = received3.userinfo.get(0).user_email;
                        String userphnno = received3.userinfo.get(0).user_phone;

//                        String doorno = received3.userinfo.get(0).user_door_number;
//                        String streetno = received3.userinfo.get(0).user_street_number;
//                        String town = received3.userinfo.get(0).user_town;
//                        String postcode = received3.userinfo.get(0).user_post_code;
//                        String specialdirection = received3.userinfo.get(0).user_special_directions;

                        edit2 = prefs2.edit();
                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);

                        edit2.putString("userid", "" + userid);
                        edit2.putString("username",""+username);
                        edit2.putString("useremail",""+useremail);
                        edit2.putString("userphnno",""+userphnno);
//                        edit2.putString("doorno",""+doorno);
//                        edit2.putString("streetno",""+streetno);
//                        edit2.putString("town",""+town);
//                        edit2.putString("postcode",""+postcode);
//                        edit2.putString("specialdirection", "" + specialdirection);

                        edit2.commit();
                        Intent in = new Intent(c, MainActivity.class);
                        c.startActivity(in);
                        LoginActivity.login.finish();

                    } else {

                        Toast.makeText(c, " "+received2.message, Toast.LENGTH_SHORT).show();
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                LoginActivity.pb.setVisibility(View.GONE);
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
//        pd.show();
        LoginActivity.pb.setVisibility(View.VISIBLE);

    }
}
