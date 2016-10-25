package com.apporio.style_appy.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.style_appy.Parsing.parsingforLocation;
import com.apporio.style_appy.Parsing.parsingforOpeninghours;
import com.apporio.style_appy.R;
import com.apporio.style_appy.checker.ConnectionDetector;
import com.apporio.style_appy.checker.GPStracker;
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import views.ProgressWheel;
import views.WorkaroundMapFragment;


public class Mainfragment extends Fragment {

//    public  static StringRequest sr;
//    public  static RequestQueue queue;
    public static String Rest_id;
    SharedPreferences.Editor edit2;
    public static SharedPreferences prefs2;
    ImageView  callus, mailus,fb,tw;
//    public static Inner_home Innerhome = new Inner_home();
//    public  static  ArrayList<String> area_id = new ArrayList<>();
//    public  static  ArrayList<String> area_code = new ArrayList<>();
//    public  static  ArrayList<String> area_fees = new ArrayList<>();
//    public static List<Inner_client_details> Innerclientdetails = new ArrayList<>();
//    public static List<Inner_delivery_area> Innerdeliverydetails = new ArrayList<>();
//    public static List<Inner_minimum_order_value> Innerminimumvalue = new ArrayList<>();
    public static  Spinner sp;
    public static String posted_by,emaillink,fblink,twlink;
//    public static  MapView mapView;
    public static Boolean isInternetPresent = false;
    public static GoogleMap map;
    public static ConnectionDetector cd;
    public static  Context ctc;
    ScrollView sv;
    public static ProgressWheel pb;
    int permissionCheck ;
    private static final int MY_PERMISSIONS_REQUEST_Call_CONTACTS = 0;
    private static final int MY_PERMISSIONS_REQUEST_Call_Contacts = 0;
//    String [] ads= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
   public static TextView addressss, descpbio,mincollvalue,Open_status;
    public static LinearLayout llforvalues,llfordays;
    //String data[] = {"Location 1", "Location 2", "Location 3", "Location 4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mainfragment, container, false);
//        mapView = (MapView)v.findViewById(R.id.mapgh);
//        mapView.onCreate(savedInstanceState);
        sp = (Spinner) v.findViewById(R.id.sppinnerloc);
        sv = (ScrollView) v.findViewById(R.id.sv);
        pb = (ProgressWheel)v.findViewById(R.id.pb11);
        callus = (ImageView) v.findViewById(R.id.img);
        mailus = (ImageView) v.findViewById(R.id.mail);
        fb = (ImageView) v.findViewById(R.id.fb);
        tw = (ImageView) v.findViewById(R.id.tw);
        addressss = (TextView)v.findViewById(R.id.address);
        descpbio = (TextView)v.findViewById(R.id.descp);
        llfordays = (LinearLayout) v.findViewById(R.id.llfropeninghours);
        ctc=getActivity();
//       mapView.getMapAsync(this);
        map = ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mapgh)).getMap();

        posted_by ="00000000";
        emaillink = "";
        fblink = "www.facebook.com";
        twlink = "www.twitter.com";
        parsingforLocation.parsing(ctc);

        ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mapgh)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                sv.requestDisallowInterceptTouchEvent(true);
            }
        });
//        parsingforOpeninghours.parsing(ctc);
        permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE);
//        sp.setAdapter(new ArrayAdapter(ctc, R.layout.itemlayoutfordropdown,
//                R.id.txtitem2, ads));
        prefs2 = PreferenceManager.getDefaultSharedPreferences(ctc);
        cd = new ConnectionDetector(ctc);
//        getthetracker("51.509289",
//                "-0.229295");

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(parsingforLocation.loc_details.get(position).size()>0) {
                        llfordays.removeAllViews();
                        for (int i = 0; i < parsingforLocation.loc_details.get(position).size(); i++) {
                            llfordays.addView(openinghours(R.layout.itemlayoutforopeninghours,
                                    parsingforLocation.loc_details.get(position).get(i).loc_opn_day,
                                    parsingforLocation.loc_details.get(position).get(i).loc_opn_open + " to " +
                                            parsingforLocation.loc_details.get(position).get(i).loc_opn_close));
                        }
                        getthetracker(parsingforLocation.location_lat.get(position),
                                parsingforLocation.location_long.get(position));
                        addressss.setText("" + parsingforLocation.location_address.get(position));
                        descpbio.setText("" + parsingforLocation.location_bio.get(position));
                        posted_by = "" + parsingforLocation.location_cellno.get(position);
                        emaillink = "" + parsingforLocation.location_email.get(position);
                        fblink = "" + parsingforLocation.location_fb.get(position);
                        twlink = "" + parsingforLocation.location_tw.get(position);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String permission = "android.permission.CALL_PHONE";

                    if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        try {


                            int permissionCheck = ContextCompat.checkSelfPermission(ctc,
                                    Manifest.permission.CALL_PHONE);

                            if (ContextCompat.checkSelfPermission(ctc,
                                    Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {

                                // Should we show an explanation?
                                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                        Manifest.permission.CALL_PHONE)) {
                                    Toast.makeText(ctc, "Please allow phone permission to make calls !!!", Toast.LENGTH_LONG).show();
                                    // Show an expanation to the user *asynchronously* -- don't block
                                    // this thread waiting for the user's response! After the user
                                    // sees the explanation, try again to request the permission.


                                } else {

                                    // No explanation needed, we can request the permission.

                                    ActivityCompat.requestPermissions(getActivity(),
                                            new String[]{Manifest.permission.READ_PHONE_STATE},
                                            MY_PERMISSIONS_REQUEST_Call_Contacts);


                                    String uri = "tel:" + posted_by.trim();
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse(uri));
                                    startActivity(intent);

                                }
                            } else {

                                String uri = "tel:" + posted_by.trim();
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            Log.e("ghghghhg", "" + e);
                        }
                    } else {

                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }

                    // parsingfornotification.parsing(MainActivity.this,regId,dd);

                } else {

                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }

            }


        });

        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "" + emaillink, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Style_Appy App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                emailIntent.setType("text/plain");


            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=fblink;
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=twlink;
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });


        return v;
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Call_Contacts: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


        return;
    }
    public static View openinghours(int itemlayoutforopeninghours, String ad, String s) {
        LayoutInflater layoutInflater =
                (LayoutInflater)ctc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(itemlayoutforopeninghours, null);
        final TextView cb = (TextView) addView.findViewById(R.id.txt);
        final TextView cbvalue = (TextView) addView.findViewById(R.id.txt3);
        cbvalue.setText(""+s);
        cb.setText("" + ad);

        return addView;
    }


    public static void getthetracker(String clientLatitude, String clientLongitude) {

        MapsInitializer.initialize(ctc);
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(ctc)) {
            case ConnectionResult.SUCCESS:
                isInternetPresent =cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {
                //Toast.makeText(ctc, "tracker", Toast.LENGTH_SHORT).show();
                // Gets to GoogleMap from the MapView and does initialization stuff


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





//                    } else {
//                        // Internet connection is not present
//                        // Ask user to connect to Internet
//                        Toast.makeText(ctc, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    }



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
       // parsing(ctc);



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




    public void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);

//        queue = VolleySingleton.getInstance(c).getRequestQueue();
//        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
//        String urlforRest_food  = Apis_url.home+""+prefs2.getString("client_id","");
//        urlforRest_food= urlforRest_food.replace(" ","%20");
//        Log.e("bahjd", "" + urlforRest_food);
//        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onResponse(String response) {
//                Log.e("Sucess", "" + response);
//                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//
//                try {
//                    GsonBuilder gsonBuilder = new GsonBuilder();
//                    final Gson gson = gsonBuilder.create();
//                 pb.setVisibility(View.GONE);
//                    area_id.clear();
//                    area_code.clear();
//                    area_fees.clear();
//
//                    Outter_home received2 = new Outter_home();
//                    received2 = gson.fromJson(response, Outter_home.class);
//
//                    if (received2.status.equals("1")) {
//                        Log.e("fdfdfdf",""+received2.data.client_details);
//                        Innerhome=received2.data;
//                        Innerclientdetails = Innerhome.client_details;
//                        Innerdeliverydetails = Innerhome.delivery_area;
//                        Innerminimumvalue = Innerhome.minimum_order_value;
//                        String open_status = Innerhome.open_status;
//                        if(open_status.equals("0")){
//                            Open_status.setText("OPEN");
//                            Open_status.setBackground(getResources().getDrawable(R.drawable.bgforopen));
//                        }
//                        else {
//                            Open_status.setText("CLOSED");
//                            Open_status.setBackground(getResources().getDrawable(R.drawable.bgforclosed));
//
//                        }
//                        if(Innerclientdetails.size()<=0){
//
//                        }
//                        else {
//                            String clientCompanyId = Innerclientdetails.get(0).client_company_id;
//                            String clientCompanyName = Innerclientdetails.get(0).client_company_name;
//                            String clientRegisterAddress = Innerclientdetails.get(0).client_register_address;
//                            String clientTradingAddress = Innerclientdetails.get(0).client_trading_address;
//                            String clientLatitude = Innerclientdetails.get(0).client_latitude;
//                            String clientLongitude = Innerclientdetails.get(0).client_longitude;
//                            addressss.setText(""+clientRegisterAddress);
//                            edit2 = prefs2.edit();
//                            edit2.putString("latitude",""+clientLatitude);
//                            edit2.putString("longitude",""+clientLongitude);
//                            edit2.commit();
//                            getthetracker("22.121445",
//                                    "72.155458");
//                        }
//                            area_code.add("Area");
//                        area_fees.add("Charge");
//                        for (int i = 0; i < Innerdeliverydetails.size(); i++)
//                        {
//                            area_id.add(Innerdeliverydetails.get(i).area_id);
//                            area_code.add(Innerdeliverydetails.get(i).area_code);
//                            area_fees.add(Innerdeliverydetails.get(i).area_fees);
//                        }
//                        if(Innerminimumvalue.size()<=0){
//
//                        }
//                        else{
//                            String min_delivery_value = Innerminimumvalue.get(0).min_order_deli_value;
//                            String min_collection_value = Innerminimumvalue.get(0).min_order_coll_value;
//                            mincollvalue.setText("Collection  £ "+min_collection_value);
//                            mindelvalue.setText("Delivery    £ " + min_delivery_value);
//
//                        }
//
//                        llforvalues.removeAllViews();
//                        for(int i=0;i<area_code.size();i++){
//                            llforvalues.addView(valueadd(R.layout.layoutforadd,area_code.get(i),area_fees.get(i)));
//                        }
//
//
//
//                    } else {
//
//
//
//                    }
//                } catch (Exception e) {
//                    Log.e("excp", "" + e);
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////               pb.setVisibility(View.GONE);
//
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
//        pb.setVisibility(View.VISIBLE);
//
    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//    }
}



