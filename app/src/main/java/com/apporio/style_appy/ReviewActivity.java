package com.apporio.style_appy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
import com.apporio.style_appy.Parsing.parsingforAddorder;
import com.apporio.style_appy.Parsing.parsingforspecificclass;
import com.apporio.style_appy.Setter_Getter.Login_Outter;
import com.apporio.style_appy.Setter_Getter.Login_Outter2;
import com.apporio.style_appy.Setter_Getter.Review_Outter;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.apporio.style_appy.checker.ConnectionDetector;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import views.ProgressWheel;
import views.WorkaroundMapFragment;

public class ReviewActivity extends FragmentActivity {
    LinearLayout back;
    FrameLayout llfrconfirm;
//   public static MapView mapView;
    public static Context ctc;
    public static ReviewActivity rv;
    public static ImageView img;
    public static TextView title, staff, time,duration,descp,price,locationname,locationaddress;
    public static Boolean isInternetPresent = false;
    public static GoogleMap map;
    public static ProgressWheel pb;

    public static StringRequest sr;
    LinearLayout llfrprice;
    public static RequestQueue queue;
    public static SharedPreferences prefs2;
    public static String userid,customerid,trtid,staffid,locid,datess,timess,orderpricess,ordertype,ordernote;
    ScrollView sv;
    public static ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(ReviewActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        back = (LinearLayout) findViewById(R.id.back22);
        llfrprice = (LinearLayout) findViewById(R.id.llforprice);
        title = (TextView) findViewById(R.id.trt);
        staff = (TextView) findViewById(R.id.staffw);
        time = (TextView) findViewById(R.id.timew);
        duration = (TextView) findViewById(R.id.duration);
        descp = (TextView) findViewById(R.id.descp);
        price = (TextView) findViewById(R.id.price);
        locationname = (TextView) findViewById(R.id.loc);
        locationaddress = (TextView) findViewById(R.id.address);
        img = (ImageView) findViewById(R.id.staffimg);
        sv = (ScrollView) findViewById(R.id.sv);
        rv = ReviewActivity.this;
        prefs2 = PreferenceManager.getDefaultSharedPreferences(ReviewActivity.this);
//        mapView = (MapView)findViewById(R.id.mapgh);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);

        userid = prefs2.getString("userid","");

        ctc = ReviewActivity.this;
        map = ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapgh)).getMap();

        pb=(ProgressWheel)findViewById(R.id.pb11);
        llfrconfirm = (FrameLayout) findViewById(R.id.llfrcnfrm);
        cd = new ConnectionDetector(ReviewActivity.this);
        ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapgh)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                sv.requestDisallowInterceptTouchEvent(true);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llfrconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewActivity.this, "Appointment Confirmed !!!", Toast.LENGTH_SHORT).show();

                if(getIntent().getStringExtra("fragment").equals("service")) {
                    if(getIntent().getStringExtra("activity").equals("appoint")) {
                        Log.e("parsing","appoint_time");
                        parsingforAddorder.parsing(ReviewActivity.this,userid,customerid,trtid,
                                locid,staffid,datess,timess,ordertype,orderpricess,ordernote,"Appointment");
                        AppointmentActivity.apc.finish();
                    }
                    else {
                        Log.e("parsing","appoint_staff");
                        parsingforAddorder.parsing(ReviewActivity.this,userid,customerid,trtid,
                                locid,staffid,datess,timess,ordertype,orderpricess,ordernote,"Appointment");
                        AppointmentActivitybystaff.apc.finish();
                    }
                    SubcategoryActivity.spc.finish();
                }
                else {
                    Log.e("parsing","no_appoint_classes");
                    parsingforAddorder.parsing(ReviewActivity.this,userid,"null",trtid,
                            locid,staffid,datess,timess,ordertype,orderpricess,"null","Class");
                }
            }
        });
    }
    public static void getthetracker(String clientLatitude, String clientLongitude, GoogleMap map) {

        MapsInitializer.initialize(ctc);
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctc)) {
            case ConnectionResult.SUCCESS:
                isInternetPresent =cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {
                    //Toast.makeText(ctc, "tracker", Toast.LENGTH_SHORT).show();
                    // Gets to GoogleMap from the MapView and does initialization stuff
//                    if (mapView != null) {

                        map.clear();
                       map.getUiSettings().setMyLocationButtonEnabled(false);
                        LatLng curentpoint = new LatLng(Double.parseDouble(clientLatitude),
                                Double.parseDouble(clientLongitude));
                        map.addMarker(new MarkerOptions()
                                .position(curentpoint)
                                .title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.markerforcurrent)));

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(curentpoint).zoom(14).build();

                        map.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
//                    CameraUpdate center =
//                            CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(clientLatitude),
//                                    Double.parseDouble(clientLongitude)));
//                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
////                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(clientLatitude),
////                            Double.parseDouble(clientLongitude)), 10), 4000, null);
//                    map.moveCamera(center);
//                    map.animateCamera(zoom);


                    }





//                } else {
//                    // Internet connection is not present
//                    // Ask user to connect to Internet
//                    Toast.makeText(ctc, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                }



                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(ctc, "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(ctc, "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(ctc, GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctc), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
//        mapView.onResume();
        super.onResume();
        if(getIntent().getStringExtra("activity").equals("rev")){
            customerid ="";
            Log.e("resume_class","classes");
            staff.setVisibility(View.GONE);
            llfrprice.setVisibility(View.GONE);
            parsingforspecificclass.parsing(ReviewActivity.this, getIntent().getStringExtra("id"), getIntent().getStringExtra("Date"));
        }
        else {
            Log.e("resume_class","appoint");
           staff.setVisibility(View.VISIBLE);
            llfrprice.setVisibility(View.VISIBLE);
            userid=getIntent().getStringExtra("user_id");
            customerid=getIntent().getStringExtra("category_id");
           trtid= getIntent().getStringExtra("trt_id");
           locid= getIntent().getStringExtra("loc_id");
           datess= getIntent().getStringExtra("date");
           timess= getIntent().getStringExtra("time");
           staffid= getIntent().getStringExtra("staffid");
//           orderpricess= getIntent().getStringExtra("orderprice");
           ordertype= getIntent().getStringExtra("ordertype");
           ordernote = getIntent().getStringExtra("ordernote");
            parsingforReviewprice(trtid,staffid);
            locationaddress.setText("" + getIntent().getStringExtra("loc_address"));
            locationname.setText("" + getIntent().getStringExtra("loc_name"));
//            price.setText("£ " +  getIntent().getStringExtra("orderprice"));
            descp.setText("" + getIntent().getStringExtra("descp"));
            duration.setText("Duration - " + getIntent().getStringExtra("duration"));
            time.setText("" + getIntent().getStringExtra("time")+", "+getIntent().getStringExtra("date"));
            staff.setText("" + getIntent().getStringExtra("staff_name"));
            title.setText("" + getIntent().getStringExtra("title"));

            getthemap(getIntent().getStringExtra("loc_lat"), getIntent().getStringExtra("loc_long"));
            String url2 = Apis_url.imagebaseurl + getIntent().getStringExtra("staff_img");
            Picasso.with(ReviewActivity.this)
                    .load(url2)
                    .placeholder(R.drawable.stub) // optional
                    .error(R.drawable.errorimg)         // optional
                    .into(img);

//            parsingforspecificclass.parsing(ReviewActivity.this, getIntent().getStringExtra("id"), getIntent().getStringExtra("Date"));

        }
//        Handler handler = new Handler();
//
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run()
//
//            {
//        getthetracker("51.509289",
//                "-0.229295",map);
//            }
//            //startThread();
//        }
//                , 1000);

        // parsing(ctc);


    }

    private void parsingforReviewprice(String trtid, String staffid) {
        queue = VolleySingleton.getInstance(ReviewActivity.this).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food = Apis_url.serviceprice+trtid+"/"+staffid;
        urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);

                ReviewActivity.pb.setVisibility(View.GONE);
//                try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                final Gson gson = gsonBuilder.create();



                Login_Outter received2 = new Login_Outter();
                received2 = gson.fromJson(response, Login_Outter.class);

                if (received2.status.equals("1")) {

                    Review_Outter received3 = new Review_Outter();
                    received3 = gson.fromJson(response, Review_Outter.class);
                    price.setText("£ " +  received3.reviewinner.price);
                    orderpricess = received3.reviewinner.price;


                } else {
                    price.setText("NA");
                    orderpricess = "NA";
                    Log.e("llklklkslks","errrr");
                    Toast.makeText(ReviewActivity.this, " "+received2.message, Toast.LENGTH_SHORT).show();
                }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                pb.setVisibility(View.GONE);
                if(error instanceof NoConnectionError) {
                    Toast.makeText(ReviewActivity.this, "No internet connectivity..", Toast.LENGTH_SHORT).show();
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
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        mapView.onLowMemory();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.map = googleMap;
////
//    }

    public static void getthemap(String lat, String longi) {

        getthetracker(lat,
                longi,map);
    }
}
