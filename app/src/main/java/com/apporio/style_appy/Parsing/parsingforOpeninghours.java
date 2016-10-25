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
import com.apporio.style_appy.Fragment.Mainfragment;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.Accountinfoshow_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_openinghours;
import com.apporio.style_appy.Setter_Getter.Openinghours_outter;
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
public class parsingforOpeninghours {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    public static RequestQueue queue;
    public static List<Inner_openinghours> openinghourses = new ArrayList<>();
    public static ArrayList<String> openingid = new ArrayList<String>();
    public static ArrayList<String> openingclientid = new ArrayList<String>();
    public static ArrayList<String> openingday = new ArrayList<String>();
    public static ArrayList<String> openingopen = new ArrayList<String>();
    public static ArrayList<String> openingclose = new ArrayList<String>();
    public static ArrayList<String> lunchtimefrom = new ArrayList<String>();
    public static ArrayList<String> dinnertimefrom = new ArrayList<String>();
    public static ArrayList<String> lunchtimeto = new ArrayList<String>();
    public static ArrayList<String> dinnertimeto = new ArrayList<String>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Opening_Hours;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                pd.dismiss();
                Mainfragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    openingid.clear();
                    openingclientid.clear();
                    openingday.clear();
                    openingopen.clear();
                    openingclose.clear();
                    lunchtimefrom.clear();
                    dinnertimefrom.clear();


                    Accountinfoshow_Outter received2 = new Accountinfoshow_Outter();
                    received2 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received2.status.equals("1")) {
                        Openinghours_outter received3 = new Openinghours_outter();
                        received3 = gson.fromJson(response, Openinghours_outter.class);

                        openinghourses = received3.data;
                        for(int i=0;i<openinghourses.size();i++){
                            openingid.add(openinghourses.get(i).opening_id);
                            openingclientid.add(openinghourses.get(i).opening_client_id);
                            openingday.add(openinghourses.get(i).opening_day);
                            openingopen.add(openinghourses.get(i).opening_open);
                            openingclose.add(openinghourses.get(i).opening_close);
                            lunchtimefrom.add(openinghourses.get(i).lunch_time_from);
                            dinnertimefrom.add(openinghourses.get(i).dinner_time_from);
                            lunchtimeto.add(openinghourses.get(i).lunch_time_to);
                            dinnertimeto.add(openinghourses.get(i).dinner_time_to);

                        }
                        if(openingid.size()>0) {
                            Mainfragment.llfordays.removeAllViews();
                            for (int i = 0; i < openingid.size(); i++) {
                                Mainfragment.llfordays.addView(Mainfragment.openinghours(R.layout.itemlayoutforopeninghours, openingday.get(i), openingopen.get(i) + " to " + openingclose.get(i)));
                            }
                        }
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
                //Opening_Hours.pb.setVisibility(View.GONE);
                Mainfragment.pb.setVisibility(View.GONE);
                Log.e("Sucess", "" + error.toString());
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
        Mainfragment.pb.setVisibility(View.VISIBLE);

    }
}
