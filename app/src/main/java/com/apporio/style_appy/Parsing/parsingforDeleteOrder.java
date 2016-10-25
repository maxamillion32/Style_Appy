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
import com.apporio.style_appy.Fragment.AppointmentFragment;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.DeleteOutter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apporio6 on 05-08-2016.
// */
public class parsingforDeleteOrder {
    public static StringRequest sr, sr2;
    public static RequestQueue queue, queue2;
    public static String Rest_id;
    public static SharedPreferences prefs2;
//    public static List<Inner_all_categories> category_names = new ArrayList<>();
//    public static ArrayList<String> catname = new ArrayList<String>();
//    public static ArrayList<String> catid = new ArrayList<String>();
//    public static ArrayList<String> catdesc = new ArrayList<String>();
//    public static ArrayList<String> catstatus = new ArrayList<String>();

    public static void parsing(final Context c, String orderid) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.deleteordr + orderid;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
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

//                    catname.clear();
//                    catid.clear();


                    DeleteOutter received2 = new DeleteOutter();
                    received2 = gson.fromJson(response, DeleteOutter.class);
//
                    if (received2.status.equals("1")) {
                        Toast.makeText(c, ""+received2.message, Toast.LENGTH_SHORT).show();
                        parsingforRecentOrders.parsing(c);

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

    }
}