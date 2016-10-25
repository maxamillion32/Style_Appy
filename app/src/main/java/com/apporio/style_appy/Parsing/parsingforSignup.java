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
import com.apporio.style_appy.Setter_Getter.Login_Outter;
import com.apporio.style_appy.Setter_Getter.Login_Outter2;
import com.apporio.style_appy.Setter_Getter.SignUp_Outter;
import com.apporio.style_appy.SignUpActivity;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apporio6 on 12-07-2016.
 */
public class parsingforSignup {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;



    public static void parsing(final Context c, final String Username, final String Email, final String Phoneno, String Password) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Sign_up +Username+Apis_url.Sign_up2+Email+
                Apis_url.Sign_up3 +Phoneno+Apis_url.Sign_up4+Password+Apis_url.Sign_up5;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                SignUpActivity.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();



                    SignUp_Outter received2 = new SignUp_Outter();
                    received2 = gson.fromJson(response, SignUp_Outter.class);

                    if (received2.status.equals("1")) {
                        Toast.makeText(c, "Welcome " + Username, Toast.LENGTH_SHORT).show();


                        edit2 = prefs2.edit();
                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("username",""+received2.userinfo.get(0).user_name);
                        edit2.putString("useremail",""+received2.userinfo.get(0).user_email);
                        edit2.putString("userphnno",""+received2.userinfo.get(0).user_phone);
                        edit2.putString("userid", "" + received2.userinfo.get(0).user_id);
                        edit2.putString("doorno","");
                        edit2.putString("streetno","");
                        edit2.putString("town","");
                        edit2.putString("postcode","");
                        edit2.putString("specialdirection", "");

                        edit2.commit();
                        Intent in = new Intent(c, MainActivity.class);
                        c.startActivity(in);
                        SignUpActivity.signup.finish();

                    } else {

                        Toast.makeText(c, ""+received2.message, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                SignUpActivity.pb.setVisibility(View.GONE);
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                Log.e("Sucess", "" +  error.toString());
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
        SignUpActivity.pb.setVisibility(View.VISIBLE);

    }

}
