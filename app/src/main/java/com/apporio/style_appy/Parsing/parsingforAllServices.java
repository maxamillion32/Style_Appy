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
import com.apporio.style_appy.Adapter.BookServiceAdapter;
import com.apporio.style_appy.Adapter.SubcategoryAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.BookClasses_Fragment;
import com.apporio.style_appy.Fragment.BookService_Fragment;
import com.apporio.style_appy.Setter_Getter.All_Services_Outter;
import com.apporio.style_appy.Setter_Getter.Categories_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_all_services;
import com.apporio.style_appy.Setter_Getter.Inner_categories;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.apporio.style_appy.SubcategoryActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 23-08-2016.
 */
public class parsingforAllServices {

    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static List<Inner_all_services> services_names = new ArrayList<>();
    public static ArrayList<String> service_id = new ArrayList<String>();
    public static ArrayList<String> servicename = new ArrayList<String>();
    public static ArrayList<String> servicedescp = new ArrayList<String>();
    public static ArrayList<String> servicebreak = new ArrayList<String>();
    public static ArrayList<String> serviceduration = new ArrayList<String>();
    public static ArrayList<String> serviceprice = new ArrayList<String>();
    public static ArrayList<String> servicecategory = new ArrayList<String>();
    public static ArrayList<String> servicestatus = new ArrayList<String>();

    public static void parsing(final Context c,String catid) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.SpecificService+catid;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                SubcategoryActivity.pb.setVisibility(View.GONE);

                service_id.clear();
                servicename.clear();
                servicedescp.clear();
                servicebreak.clear();
                servicecategory.clear();
                serviceduration.clear();
                serviceprice.clear();
                servicestatus.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    All_Services_Outter received2 = new All_Services_Outter();
                    received2 = gson.fromJson(response, All_Services_Outter.class);

                    if (received2.status.equals("1")) {
                        services_names=received2.data_services;

                        for (int i = 0; i < services_names.size(); i++)
                        {
                            service_id.add(services_names.get(i).details.service_id);
                            servicename.add(services_names.get(i).details.service_name);
                            servicedescp.add(services_names.get(i).details.service_description);
                            servicebreak.add(services_names.get(i).details.service_break);
                            servicecategory.add(services_names.get(i).details.service_category);
                            serviceduration.add(services_names.get(i).details.service_duration);
                            servicestatus.add(services_names.get(i).details.service_status);
                            serviceprice.add(services_names.get(i).details.service_price);
                        }
                        SubcategoryActivity.lv.setAdapter(new SubcategoryAdapter(c,servicename,
                                servicedescp, serviceprice,
                                serviceduration));


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
                SubcategoryActivity.pb.setVisibility(View.GONE);
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
        SubcategoryActivity.pb.setVisibility(View.VISIBLE);
    }
}
