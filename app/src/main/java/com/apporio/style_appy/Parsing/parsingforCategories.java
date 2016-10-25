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
import com.apporio.style_appy.Adapter.BookServiceAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.BookService_Fragment;
import com.apporio.style_appy.LoginActivity;
import com.apporio.style_appy.MainActivity;
import com.apporio.style_appy.Setter_Getter.Categories_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_categories;
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
 * Created by apporio6 on 12-07-2016.
 */
public class parsingforCategories {
    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static List<Inner_categories> category_names = new ArrayList<>();
    public static ArrayList<String> catname = new ArrayList<String>();
    public static ArrayList<String> catid = new ArrayList<String>();
    public static ArrayList<String> catdesc = new ArrayList<String>();
    public static ArrayList<String> catstatus = new ArrayList<String>();

    public static void parsing(final Context c) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Category;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                BookService_Fragment.pb.setVisibility(View.GONE);

                catname.clear();
                catid.clear();
                catdesc.clear();
                catstatus.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    Categories_Outter received2 = new Categories_Outter();
                    received2 = gson.fromJson(response, Categories_Outter.class);

                    if (received2.status.equals("1")) {
                        category_names=received2.data_category;

                        for (int i = 0; i < category_names.size(); i++)
                        {
                            catname.add(category_names.get(i).category_name);
                            catid.add(category_names.get(i).category_id);
                            catdesc.add(category_names.get(i).category_des);
                            catstatus.add(category_names.get(i).category_status);
                        }
                        BookService_Fragment.lv.setAdapter(new BookServiceAdapter(c, catid,catname,catdesc ));


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
                BookService_Fragment.pb.setVisibility(View.GONE);
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
        BookService_Fragment.pb.setVisibility(View.VISIBLE);
    }
}
