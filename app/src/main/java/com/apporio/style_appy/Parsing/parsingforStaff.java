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
import com.apporio.style_appy.Adapter.GalleryAdapter;
import com.apporio.style_appy.Adapter.StaffAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.GalleryFragment;
import com.apporio.style_appy.Fragment.Staff_Fragment;
import com.apporio.style_appy.Setter_Getter.Gallery_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_Staff;
import com.apporio.style_appy.Setter_Getter.Inner_gallery;
import com.apporio.style_appy.Setter_Getter.Staff_Outter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 20-07-2016.
 */
public class parsingforStaff {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static List<Inner_Staff> Staffnames = new ArrayList<>();
    public static ArrayList<String> staffname = new ArrayList<String>();
    public static ArrayList<String> staffid = new ArrayList<String>();
    public static ArrayList<String> staffdescp = new ArrayList<String>();
    public static ArrayList<String> staffimage = new ArrayList<String>();
    public static ArrayList<String> staffposition = new ArrayList<String>();


    public static void parsing(final Context c) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Staff;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                Staff_Fragment.pb.setVisibility(View.GONE);

                staffid.clear();
                staffname.clear();
                staffdescp.clear();
                staffimage.clear();
                staffposition.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    Staff_Outter received2 = new Staff_Outter();
                    received2 = gson.fromJson(response, Staff_Outter.class);

                    if (received2.status.equals("1")) {
                        Staffnames=received2.data_staff;

                        for (int i = 0; i < Staffnames.size(); i++)
                        {
                            staffid.add(Staffnames.get(i).det.staff_id);
                            staffname.add(Staffnames.get(i).det.staff_name);
                            staffdescp.add(Staffnames.get(i).det.staff_description);
                            staffimage.add(Staffnames.get(i).det.staff_img);
                            staffposition.add(Staffnames.get(i).det.staff_position);
                        }
                        Staff_Fragment.lv.setAdapter(new StaffAdapter(c,staffid,staffname,staffdescp,staffposition,staffimage));


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
                Staff_Fragment.pb.setVisibility(View.GONE);
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
        Staff_Fragment.pb.setVisibility(View.VISIBLE);
    }
}
