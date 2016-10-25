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
import com.apporio.style_appy.Adapter.AppointmentAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.AppointmentFragment;
import com.apporio.style_appy.ReviewActivity;
import com.apporio.style_appy.Setter_Getter.Inner_All_orders;
import com.apporio.style_appy.Setter_Getter.Outer_all_orders;
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
public class parsingforRecentOrders {
    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
//    public static List<Inner_All_orders> orderList = new ArrayList<>();
//    public static ArrayList<String> orderid = new ArrayList<String>();
//    public static ArrayList<String> orderdate = new ArrayList<String>();
//    public static ArrayList<String> orderstatus = new ArrayList<String>();
//    public static ArrayList<String> orderprice = new ArrayList<String>();
//    public static ArrayList<String> ordertype = new ArrayList<String>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.recentorder+prefs2.getString("userid","");
//        String urlforRest_food  = Apis_url.recentorder+prefs2.getString("userid","");
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                AppointmentFragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

//                    orderid.clear();
//                    orderdate.clear();
//                    orderstatus.clear();
//                    orderprice.clear();
//                    ordertype.clear();



                    Outer_all_orders received2 = new Outer_all_orders();
                    received2 = gson.fromJson(response, Outer_all_orders.class);

                    if (received2.status.equals("1")) {
//                        orderList=received2.data;
//
//                        for (int i = 0; i < orderList.size(); i++)
//                        {
//                            orderid.add(orderList.get(i).order_id);
//                            orderdate.add(orderList.get(i).order_date);
//                            orderstatus.add(orderList.get(i).order_status);
//                            orderprice.add(orderList.get(i).order_price);
//                            ordertype.add(orderList.get(i).order_type);
//
//                        }
//                        AppointmentFragment.lv.setAdapter(new AppointmentAdapter(c, orderid,orderdate,orderstatus ,ordertype,orderprice));
                        AppointmentFragment.lv.setAdapter(new AppointmentAdapter(c,received2.data));

                    } else {



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // pd.dismiss();
                // OrderHistoryFragment.pb.setVisibility(View.GONE);
                AppointmentFragment.pb.setVisibility(View.GONE);
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
        AppointmentFragment.pb.setVisibility(View.VISIBLE);

        // pd.show();
    }
}
