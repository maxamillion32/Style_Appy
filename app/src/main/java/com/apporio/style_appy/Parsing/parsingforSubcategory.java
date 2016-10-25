//package com.apporio.style_appy.Parsing;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NoConnectionError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.apporio.style_appy.Adapter.SubcategoryAdapter;
//import com.apporio.style_appy.Api_s.Apis_url;
//import com.apporio.style_appy.Setter_Getter.Inner_sub_categories;
//import com.apporio.style_appy.Setter_Getter.Sub_Category_Outter;
//import com.apporio.style_appy.Singleton.VolleySingleton;
//import com.apporio.style_appy.SubcategoryActivity;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by apporio6 on 12-07-2016.
// */
//public class parsingforSubcategory {
//    public static StringRequest sr;
//    public static RequestQueue queue;
//    public static String Rest_id;
//    public static SharedPreferences prefs2;
//    public static List<Inner_sub_categories> productses = new ArrayList<>();
//    public static ArrayList<String> product_id = new ArrayList<String>();
//    public static ArrayList<String> product_name = new ArrayList<String>();
//    public static ArrayList<String> product_descp = new ArrayList<String>();
//    public static ArrayList<String> product_rates = new ArrayList<String>();
//    public static ArrayList<String> categorystatus = new ArrayList<String>();
//    public static ArrayList<String> duration = new ArrayList<String>();
//
//
//    public static void parsing(final Context c, String category_idss) {
////        final ProgressDialog pd = new ProgressDialog(c);
////        pd.setMessage("Loading ...");
////        pd.setCancelable(false);
//        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
//        queue = VolleySingleton.getInstance(c).getRequestQueue();
//        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
//        String urlforRest_food = Apis_url.Sub_Category+category_idss;
//        urlforRest_food = urlforRest_food.replace(" ", "%20");
//        Log.e("bahjd", "" + urlforRest_food);
//        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("Sucess", "" + response);
//                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                // pd.dismiss();
//                SubcategoryActivity.pb.setVisibility(View.GONE);
//                try {
//                    GsonBuilder gsonBuilder = new GsonBuilder();
//                    final Gson gson = gsonBuilder.create();
//
//                    product_id.clear();
//                    product_name.clear();
//                    product_descp.clear();
//                    product_rates.clear();
//                    categorystatus.clear();
//                    duration.clear();
//
//
//                    Sub_Category_Outter received2 = new Sub_Category_Outter();
//                    received2 = gson.fromJson(response, Sub_Category_Outter.class);
//
//                    if (received2.status.equals("1")) {
//                        productses = received2.data_category;
//
//                        for (int i = 0; i < productses.size(); i++) {
//                            product_id.add(productses.get(i).category_ids);
//                            product_name.add(productses.get(i).sub_category_name);
//                            product_descp.add(productses.get(i).sub_category_des);
//                            product_rates.add(productses.get(i).sub_category_price);
//                            duration.add(productses.get(i).sub_category_duration);
//                            categorystatus.add(productses.get(i).sub_category_sttus);
//                        }
//                        SubcategoryActivity.lv.setAdapter(new SubcategoryAdapter(c, product_name, product_descp, product_rates,duration));
//
//
//                    } else {
//
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //pDialog.dismiss();
//                SubcategoryActivity.pb.setVisibility(View.GONE);
//                if(error instanceof NoConnectionError) {
//                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
//                }
//                Log.e("Sucess", "" + error.toString());
//                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        sr.setRetryPolicy(new DefaultRetryPolicy(
//                30000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        queue.add(sr);
//        // pd.show();
//        SubcategoryActivity.pb.setVisibility(View.VISIBLE);
//
//    }
//}
