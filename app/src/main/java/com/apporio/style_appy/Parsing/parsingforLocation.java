package com.apporio.style_appy.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.apporio.style_appy.Setter_Getter.Inner_location;
import com.apporio.style_appy.Setter_Getter.Inner_location2;
import com.apporio.style_appy.Setter_Getter.Location_Outter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 01-08-2016.
 */
public class parsingforLocation {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    public static RequestQueue queue;
    public static List<Inner_location2> locationdetails = new ArrayList<>();
    public static ArrayList<String> location_id = new ArrayList<String>();
    public static ArrayList<String> location_name = new ArrayList<String>();
    public static ArrayList<String> location_address = new ArrayList<String>();
    public static ArrayList<String> location_lat = new ArrayList<String>();
    public static ArrayList<String> location_long = new ArrayList<String>();
    public static ArrayList<String> location_email = new ArrayList<String>();
    public static ArrayList<String> location_cellno = new ArrayList<String>();
    public static ArrayList<String> location_fb = new ArrayList<String>();
    public static ArrayList<String> location_tw = new ArrayList<String>();
    public static ArrayList<String> location_bio = new ArrayList<String>();
    public static ArrayList<List<Inner_location>> loc_details = new ArrayList<>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.View_location;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                pd.dismiss();
                Mainfragment.pb.setVisibility(View.GONE);
                try{
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    location_id.clear();
                    location_name.clear();
                    location_address.clear();
                    location_lat.clear();
                    location_long.clear();
                    location_cellno.clear();
                    location_email.clear();
                    location_fb.clear();
                    location_tw.clear();
                    location_bio.clear();



                    Accountinfoshow_Outter received2 = new Accountinfoshow_Outter();
                    received2 = gson.fromJson(response, Accountinfoshow_Outter.class);

                    if (received2.status.equals("1")) {
                        Location_Outter received3 = new Location_Outter();
                        received3 = gson.fromJson(response, Location_Outter.class);

                        locationdetails = received3.data_location;
                        for(int i=0;i<locationdetails.size();i++){
                            location_id.add(locationdetails.get(i).details.location_id);
                            location_name.add(locationdetails.get(i).details.location_name);
                            location_address.add(locationdetails.get(i).details.location_address);
                            location_lat.add(locationdetails.get(i).details.location_lat);
                            location_long.add(locationdetails.get(i).details.location_long);
                            location_cellno.add(locationdetails.get(i).details.location_cellno);
                            location_email.add(locationdetails.get(i).details.location_email);
                            location_fb.add(""+locationdetails.get(i).details.location_fb);
                            location_tw.add(""+locationdetails.get(i).details.location_tw);
                            location_bio.add(locationdetails.get(i).details.location_bio);
                            loc_details.add(locationdetails.get(i).opening);



                        }
                        Mainfragment.sp.setAdapter(new ArrayAdapter(c, R.layout.itemlayoutfordropdown,
                                R.id.txtitem2, location_name));
                        if(location_name.size()>0) {

                            Mainfragment.getthetracker(location_lat.get(0), location_long.get(0));
                            Mainfragment.addressss.setText("" + location_address.get(0));
                            Mainfragment.descpbio.setText("" + location_bio.get(0));
                            Mainfragment.posted_by = ""+location_cellno.get(0);
                            Mainfragment.emaillink = ""+location_email.get(0);
                            Mainfragment.fblink = ""+location_fb.get(0);
                            Mainfragment.twlink = ""+location_tw.get(0);

                        }
                        if(loc_details.size()>0) {
                            Mainfragment.llfordays.removeAllViews();
                            for (int i = 0; i < loc_details.get(0).size(); i++) {
                                Mainfragment.llfordays.addView(Mainfragment.openinghours(R.layout.itemlayoutforopeninghours,loc_details.get(0).get(i).loc_opn_day,
                                        loc_details.get(0).get(i).loc_opn_open + " to " + loc_details.get(0).get(i).loc_opn_close));
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
