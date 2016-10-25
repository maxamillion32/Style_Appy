package com.apporio.style_appy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.apporio.style_appy.Fragment.AccountsFragment;
import com.apporio.style_appy.Fragment.AppointmentFragment;
import com.apporio.style_appy.Fragment.BookClasses_Fragment;
import com.apporio.style_appy.Fragment.BookService_Fragment;
import com.apporio.style_appy.Fragment.GalleryFragment;
import com.apporio.style_appy.Fragment.Mainfragment;
import com.apporio.style_appy.Fragment.Opening_Hours;
import com.apporio.style_appy.Fragment.Staff_Fragment;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_ITEM3 = 3;
    public static String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];
    private String name;        //String Resource for header View Name
    private int profile ;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    Fragment fragment = null;
    public static Context ctc2;

    public static Context ctc;
    private static String mCurrentPhotoPath;
    public static String NAME = "";
    public static String EMAIL = "";
    public static String PROFILE = "";
    public static SharedPreferences prefs2;
    static SharedPreferences.Editor edit2;
    // private static RealmConfiguration realmConfig;
    private android.support.v7.widget.Toolbar toolbar;                              // Declaring the Toolbar Object
    public static String TITLES[] = {"Home","Book a Class"
            ,"Book a Service","Bookings","Staff","Gallery","Account","Log Out"};
    public static int ICONS[] = {

            R.drawable.home,
            R.drawable.locations,
            R.drawable.locations,
            R.drawable.appoinments,
            R.drawable.staff,
            R.drawable.gallery,
            R.drawable.account,
            R.drawable.logout};
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    public TextView head;
    public static RecyclerView mRecyclerView;                           // Declaring RecyclerView
    public static RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public static RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public static DrawerLayout Drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(MainActivity.this, R.color.statusbarcolor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        head = (TextView) findViewById(R.id.header);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        fragment = new Mainfragment();
        if (fragment != null) {
            head.setText("STYLE-APPY");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        mRecyclerView.setAdapter(mAdapter);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int Holderid;

            TextView textView,textView2;
            ImageView imageView,imageView2,drop;
            public ImageView profile11;
            public TextView Name;
            TextView email;
            FrameLayout llforprof;
            LinearLayout llforsub,llfrbbs,llfrbbt;
            LinearLayout itemll,itemll2;

            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);


                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    itemll = (LinearLayout) itemView.findViewById(R.id.llfornavi);

                    itemll.setOnClickListener(this);
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;

                    // setting holder id as 1 as the object being populated are of type item row
                }
              else  if (ViewType == TYPE_ITEM3) {
                    textView2 = (TextView) itemView.findViewById(R.id.rowTex); // Creating TextView object with the id of textView from item_row.xml
                    imageView2 = (ImageView) itemView.findViewById(R.id.rowIco);
                    drop = (ImageView) itemView.findViewById(R.id.rowiconup);
                    itemll2 = (LinearLayout) itemView.findViewById(R.id.llfornav);
                    llforsub = (LinearLayout) itemView.findViewById(R.id.llfrsub);
                    llfrbbt = (LinearLayout) itemView.findViewById(R.id.llfrbbt);
                    llfrbbs = (LinearLayout) itemView.findViewById(R.id.llfrbbs);
                    itemll2.setOnClickListener(this);

                    llfrbbs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Drawer.closeDrawer(Gravity.LEFT);
                            fragment = new BookService_Fragment();
                            Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {

                                @Override
                                public void run()

                                {

                                    if (fragment != null) {
                                        Bundle b = new Bundle();
                                        b.putString("bookby", "bookbystaff");
                                        head.setText("BOOK BY STAFF");
                                        fragment.setArguments(b);
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                    } else {
                                        Log.e("MainActivity", "Error in creating fragment");
                                    }

                                }
                                //startThread();
                            }
                                    , 500);
                          //  Toast.makeText(MainActivity.this, "Book by Staff", Toast.LENGTH_SHORT).show();
                        }
                    });
                    llfrbbt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Drawer.closeDrawer(Gravity.LEFT);
                            fragment = new BookService_Fragment();
                            Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {

                                @Override
                                public void run()

                                {

                                    if (fragment != null) {
                                        Bundle b = new Bundle();
                                        b.putString("bookby", "bookbydate");
                                        head.setText("BOOK BY DATE");
                                        fragment.setArguments(b);
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                    } else {
                                        Log.e("MainActivity", "Error in creating fragment");
                                    }

                                }
                                //startThread();
                            }
                                    , 500);
                            //Toast.makeText(MainActivity.this, "Book by Time", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 2;

                    // setting holder id as 1 as the object being populated are of type item row
                }else {

                    // Creating Text View object from header.xml for email

                    profile11 = (ImageView) itemView.findViewById(R.id.circleView);
                    llforprof = (FrameLayout) itemView.findViewById(R.id.llforprofile);
                    profile11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.Drawer.closeDrawer(Gravity.LEFT);
                            //  showcamerdialog();
                        }
                    });
                    llforprof.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(ctc2, Profileactivity.class);
//                            ctc2.startActivity(in);
                        }
                    });

                    // Creating Image view object from header.xml for profile pic
                    Holderid = 0;
                }
            }


            @Override
            public void onClick(View v) {
                try {

                    if (mNavTitles[getPosition() - 1].equals("Home")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new Mainfragment();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {

                                if (fragment != null) {
                                    head.setText("STYLE-APPY");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }

                            }
                            //startThread();
                        }
                                , 500);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("Book a Class")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new BookClasses_Fragment();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {

                                if (fragment != null) {
                                    head.setText("CLASSES");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }

                            }
                            //startThread();
                        }
                                , 500);
                    }


                    else if (mNavTitles[getPosition() - 1].equals("Book a Service")) {
//                        Drawer.closeDrawer(Gravity.LEFT);
                        if(llforsub.getVisibility()==View.GONE) {
                            drop.setImageResource(R.drawable.up);
                            llforsub.setVisibility(View.VISIBLE);
                        }
                        else {
                            drop.setImageResource(R.drawable.down);
                            llforsub.setVisibility(View.GONE);
                        }

//                        fragment = new Mainfragment();
//                        Handler handler = new Handler();
//
//                        handler.postDelayed(new Runnable() {
//
//                            @Override
//                            public void run()
//
//                            {
//
//
//                                Intent in = new Intent(MainActivity.this, CartActivity.class);
//                                startActivity(in);
//
//                            }
//                            //startThread();
//                        }
//                                , 500);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Bookings")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new AppointmentFragment();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {


                                if (fragment != null) {
                                    head.setText("BOOKINGS");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 500);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Staff")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new Staff_Fragment();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {


                                if (fragment != null) {
                                    head.setText("STAFF");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 500);
                    }
//                    else if (mNavTitles[getPosition() - 1].equals("Opening Hours")) {
//                        Drawer.closeDrawer(Gravity.LEFT);
//                        fragment = new Opening_Hours();
//                        Handler handler = new Handler();
//
//                        handler.postDelayed(new Runnable() {
//
//                            @Override
//                            public void run()
//
//                            {
//
//
//                                if (fragment != null) {
//                                    head.setText("OPENING HOURS");
//                                    FragmentManager fragmentManager = getSupportFragmentManager();
//                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();
//
//                                } else {
//                                    Log.e("MainActivity", "Error in creating fragment");
//                                }
//                            }
//                            //startThread();
//                        }
//                                , 500);
//                    }
                    else if (mNavTitles[getPosition() - 1].equals("Gallery")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new GalleryFragment();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {


                                if (fragment != null) {
                                    head.setText("GALLERY");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 500);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Account")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        fragment = new AccountsFragment();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {


                                if (fragment != null) {
                                    head.setText("ACCOUNT");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 500);
                    }
                    else if (mNavTitles[getPosition() - 1].equals("Log Out")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {

                               showexitdialog();

                            }
                            //startThread();
                        }
                                , 200);
                    }
                    else{
                        //
                    }
                }catch (Exception e){
                    Log.e("ddddd", "" + e);
                }
            }

        }



        public MyAdapter(MainActivity mainActivity, String Titles[], int Icons[], String Name, String Email, String Profile){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
            email = Email;
            //profile = Profile;
            ctc2= mainActivity;
            //here we assign those passed values to the values we declared here
            //in adapter


        }



        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem; // Returning the created object

                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            else if (viewType == TYPE_ITEM3) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row2,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
           else if(holder.Holderid ==2) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView2.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView2.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
            }
            else{
//                Picasso.with(MainActivity.this)
//                        .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + profile)
//                        .placeholder(R.drawable.download) // optional
//                        .error(R.drawable.download)         // optional
//                        .into(holder.profile11);
                //  holder.profile11.setImageBitmap(bitmap1);           // Similarly we set the resources for header view
//                holder.Name.setText(name);

            }

        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            else if (isPositionItem(position))
                return TYPE_ITEM3;

            return TYPE_ITEM;

        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }
        private boolean isPositionItem(int position) {
            return position == 3;
        }

    }
    private void showexitdialog() {

        final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogforexit);

        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        TextView cancel = (TextView) dialog.findViewById(R.id.cncl);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        edit2 = prefs2.edit();
                        edit2.putBoolean("pref_previously_started", Boolean.FALSE);
                        edit2.putString("username", "");
                        edit2.putString("useremail","");
                        edit2.putString("userphnno","");
                        edit2.putString("doorno","");
                        edit2.putString("userid","");
                        edit2.putString("streetno","");
                        edit2.putString("town","");
                        edit2.putString("postcode","");
                        edit2.putString("specialdirection", "");

                        edit2.commit();

                        dialog.dismiss();
                        Intent in = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(in);
                        finish();


                    }
                }
                        , 500);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        dialog.dismiss();
                    }
                }
                        , 500);
            }
        });


        dialog.show();
    }
}
