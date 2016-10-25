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
import com.apporio.style_appy.Adapter.BookClassesAdapter;
import com.apporio.style_appy.Adapter.BookServiceAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Fragment.BookClasses_Fragment;
import com.apporio.style_appy.Fragment.BookService_Fragment;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.Inner_classes_details;
import com.apporio.style_appy.Setter_Getter.Inner_outter_classes;
import com.apporio.style_appy.Setter_Getter.Outter_classses;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter;




public class parsingforClasses {
    public static StringRequest sr;
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    public static RequestQueue queue;
    public static int countheader=0;
    public static List<Inner_outter_classes> classdetails = new ArrayList<>();
    public static ArrayList<ArrayList<Inner_classes_details>> recorddetails = new ArrayList<>();
    public static ArrayList<String> classid = new ArrayList<String>();
    public static ArrayList<String> classname = new ArrayList<String>();
    public static ArrayList<String> classduration = new ArrayList<String>();
    public static ArrayList<String> locations = new ArrayList<String>();
    public static ArrayList<String> placesleft = new ArrayList<String>();
    public static ArrayList<String> staffname = new ArrayList<String>();
    public static ArrayList<String> classtime = new ArrayList<String>();
    public static ArrayList<String> dateday = new ArrayList<String>();
    public static ArrayList<String> datess = new ArrayList<String>();
    public static ArrayList<Integer> header = new ArrayList<>();
    public static ArrayList<SimpleSectionedListAdapter.Section> sections = new ArrayList<SimpleSectionedListAdapter.Section>();

    public static void parsing(final Context c) {

        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.Classes;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                BookClasses_Fragment.pb.setVisibility(View.GONE);
                countheader=0;
                dateday.clear();
                classid.clear();
                classduration.clear();
                classname.clear();
                classtime.clear();
                header.clear();
                recorddetails.clear();
                locations.clear();
                placesleft.clear();
                staffname.clear();
                sections.clear();
                datess.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    Outter_classses received2 = new Outter_classses();
                    received2 = gson.fromJson(response, Outter_classses.class);

                    if (received2.status.equals("1")) {
                        classdetails=received2.detailss;
//                        header.add(0);
                        for (int i = 0; i < classdetails.size(); i++)
                        {
                            dateday.add(classdetails.get(i).day+", "+classdetails.get(i).date);
                            recorddetails.add(classdetails.get(i).classes);

                                if(i==0){

                                    header.add(countheader);
                                    countheader = classdetails.get(0).classes.size() ;
                                    header.add(countheader);
                                }

                            else {
                                    countheader = countheader +classdetails.get(i).classes.size();
                                    header.add(countheader);
                                }


                            Log.e("datatatrdrd",""+classdetails.get(i).classes.size());

                            for(int j=0;j<recorddetails.get(i).size();j++){
                                datess.add(classdetails.get(i).date);
                                classid.add(recorddetails.get(i).get(j).classes_id);
                                classduration.add(recorddetails.get(i).get(j).classes_time_duration);
                                classname.add(recorddetails.get(i).get(j).classes_title);
                                classtime.add(recorddetails.get(i).get(j).classes_time_duration);
                                staffname.add(recorddetails.get(i).get(j).staff_name);
                                placesleft.add(""+recorddetails.get(i).get(j).left_places);
                                locations.add(recorddetails.get(i).get(j).location_name);
                            }
                        }
                        Log.e("classids",""+classid);
                        header.remove(header.size()-1);
                        Log.e("datatat", "" + header);
                        BookClassesAdapter  mAdapter =  new BookClassesAdapter(c,classname,classtime,
                                classduration,staffname,locations,placesleft);
                        for (int i = 0; i < header.size(); i++) {
                            sections.add(new SimpleSectionedListAdapter.Section(header.get(i), dateday.get(i)));
                        }
                        SimpleSectionedListAdapter simpleSectionedGridAdapter = new SimpleSectionedListAdapter(c, mAdapter,
                                R.layout.headerlist, R.id.head);
                        simpleSectionedGridAdapter.setSections(sections.toArray(new SimpleSectionedListAdapter.Section[0]));
                        BookClasses_Fragment.stickyList.setAdapter(simpleSectionedGridAdapter);

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("err",""+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                BookClasses_Fragment.pb.setVisibility(View.GONE);
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
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
//        pd.show();
        BookClasses_Fragment.pb.setVisibility(View.VISIBLE);
    }
}