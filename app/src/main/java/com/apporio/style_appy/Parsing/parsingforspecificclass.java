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
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.BookService_Fragment;
import com.apporio.style_appy.R;
import com.apporio.style_appy.ReviewActivity;
import com.apporio.style_appy.Setter_Getter.Categories_Outter;
import com.apporio.style_appy.Setter_Getter.Inner_categories;
import com.apporio.style_appy.Setter_Getter.Inner_class_det;
import com.apporio.style_appy.Setter_Getter.Inner_classes_details;
import com.apporio.style_appy.Setter_Getter.Specific_class_Outter;
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
 * Created by apporio6 on 02-08-2016.
 */
public class parsingforspecificclass {
    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static String locationid="";
    public static String staffid="";
    public static List<Inner_class_det> specificclassdetails = new ArrayList<>();
    public static ArrayList<String> treatname = new ArrayList<String>();
    public static ArrayList<String> staffname = new ArrayList<String>();
    public static ArrayList<String> time = new ArrayList<String>();
    public static ArrayList<String> duration = new ArrayList<String>();
    public static ArrayList<String> descp = new ArrayList<String>();
    public static ArrayList<String> price = new ArrayList<String>();
    public static ArrayList<String> staffimage = new ArrayList<String>();
    public static ArrayList<String> locaname = new ArrayList<String>();
    public static ArrayList<String> locaddress = new ArrayList<String>();

    public static void parsing(final Context c, String id, final String s) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        ReviewActivity.trtid=""+id;
        ReviewActivity.datess =""+s;
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.SpecificClasses+id+"/"+s;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                ReviewActivity.pb.setVisibility(View.GONE);

                locaddress.clear();
                locaname.clear();
                staffimage.clear();
                price.clear();
                descp.clear();
                duration.clear();
                time.clear();
                staffname.clear();
                treatname.clear();
                staffid="";
                locationid="";


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    Specific_class_Outter received2 = new Specific_class_Outter();
                    received2 = gson.fromJson(response, Specific_class_Outter.class);

                    if (received2.status.equals("1")) {
                       String locaaddress = received2.details.get(0).location_address;
                        String locaname = received2.details.get(0).location_name;
                        String staffimage = received2.details.get(0).staff_image;
                        String price = received2.details.get(0).classses_price;
                        String descp = received2.details.get(0).classes_desc;
                        String duration = received2.details.get(0).classes_duration;
                        String time = received2.details.get(0).recurring_time;
                        String staffname = received2.details.get(0).staff_name;
                        String treatname = received2.details.get(0).classes_title;
                        String loclat = received2.details.get(0).location_lat;
                        String loclong = received2.details.get(0).location_long;
                        locationid =received2.details.get(0).location_id;
                        staffid = received2.details.get(0).staff_id;



                            ReviewActivity.locationaddress.setText("" + locaaddress);
                            ReviewActivity.locationname.setText("" + locaname);
                            ReviewActivity.price.setText("£ " + price);
                            ReviewActivity.descp.setText("" + descp);
                            ReviewActivity.duration.setText("Duration - " + duration+" minutes");
                            ReviewActivity.time.setText("" + time+", "+s);

                            ReviewActivity.staff.setText("" + staffname);
                            ReviewActivity.title.setText("" + treatname);
                            ReviewActivity.getthemap(loclat, loclong);
                            String url2 = Apis_url.imagebaseurl + staffimage.trim();
                            Picasso.with(c)
                                    .load(url2)
                                    .placeholder(R.drawable.stub) // optional
                                    .error(R.drawable.errorimg)         // optional
                                    .into(ReviewActivity.img);

                            ReviewActivity.staffid = ""+staffid;
                            ReviewActivity.locid =""+locationid;
                            ReviewActivity.timess=""+time;
                            ReviewActivity.ordernote="";
                            ReviewActivity.orderpricess = price;
                            ReviewActivity.ordertype="classess";





                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error",""+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                ReviewActivity.pb.setVisibility(View.GONE);
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
        ReviewActivity.pb.setVisibility(View.VISIBLE);
    }
}
