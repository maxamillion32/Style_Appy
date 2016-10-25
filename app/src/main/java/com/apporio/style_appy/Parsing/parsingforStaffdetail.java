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
import com.apporio.style_appy.Adapter.StaffAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.Staff_Fragment;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.Inner_Staff;
import com.apporio.style_appy.Setter_Getter.Inner_Staff_detail;
import com.apporio.style_appy.Setter_Getter.Inner_services;
import com.apporio.style_appy.Setter_Getter.Staff_Outter;
import com.apporio.style_appy.Setter_Getter.Staff_detail_Outter;
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
 * Created by apporio6 on 20-07-2016.
 */
public class parsingforStaffdetail {


    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static Inner_Staff_detail Staffnames = new Inner_Staff_detail();
    public static ArrayList<String> staffname = new ArrayList<String>();
    public static ArrayList<String> staffid = new ArrayList<String>();
    public static ArrayList<String> staffdescp = new ArrayList<String>();
    public static ArrayList<String> staffimage = new ArrayList<String>();
    public static ArrayList<String> staffposition = new ArrayList<String>();
    public static ArrayList<String> servicesnames = new ArrayList<String>();
    private static List<Inner_services> Servicesnn = new ArrayList<>();


    public static void parsing(final Context c, String staffid2) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Staff_Specific+staffid2;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                StaffspecificActivity.pb.setVisibility(View.GONE);

                parsingforStaffdetail.staffid.clear();
                staffname.clear();
                staffdescp.clear();
                staffimage.clear();
                staffposition.clear();
                servicesnames.clear();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    Staff_detail_Outter received2 = new Staff_detail_Outter();
                    received2 = gson.fromJson(response, Staff_detail_Outter.class);

                    if (received2.status.equals("1")) {
                        Staffnames=received2.data_staff_detail;
                        Servicesnn = received2.services;
                        for (int i = 0; i < Servicesnn.size(); i++)
                        {
                            servicesnames.add(Servicesnn.get(i).service_name);
                        }
                           staffid.add(Staffnames.staff_id);
                            staffname.add(Staffnames.staff_name);
                            staffdescp.add(Staffnames.staff_description);
                            staffimage.add(Staffnames.staff_img);
                            staffposition.add(Staffnames.staff_position);
                       // }
                        StaffspecificActivity.header.setText("" + staffname.get(0).toString().trim());
                        StaffspecificActivity.descp.setText("" + staffdescp.get(0).toString().trim());
                        StaffspecificActivity.position.setText(""+staffposition.get(0).toString().trim());

                        String url2 = Apis_url.imagebaseurl+staffimage.get(0).toString().trim();
                        Log.e("jhdjdajhsd", "" + url2);
                        Picasso.with(c)
                                .load(url2)
                                .placeholder(R.drawable.stub) // optional
                                .error(R.drawable.errorimg)         // optional
                                .into(StaffspecificActivity.dpimage);
                        StaffspecificActivity.llfrservice.removeAllViews();
                        for(int i=0;i<servicesnames.size();i++) {
                            StaffspecificActivity.llfrservice.addView(StaffspecificActivity.serviceview(c,
                                    R.layout.itemfrservice
                                    , servicesnames.get(i)));
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
                StaffspecificActivity.pb.setVisibility(View.GONE);
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

        StaffspecificActivity.pb.setVisibility(View.VISIBLE);
    }
}
