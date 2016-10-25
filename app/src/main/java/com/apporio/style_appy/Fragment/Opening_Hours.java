package com.apporio.style_appy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.apporio.style_appy.Adapter.Opening_HoursAdapter;
import com.apporio.style_appy.R;

import views.ProgressWheel;

/**
 * Created by saifi45 on 4/13/2016.
 */
public class Opening_Hours extends Fragment {


    public  static ListView lv;
    Context ctc;
    public static ProgressWheel pb;
    String [] ads= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    public  static Opening_HoursAdapter adp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_openinghours, container, false);
        ctc = getActivity();
        pb=(ProgressWheel)v.findViewById(R.id.pb11);
        lv=(ListView)v.findViewById(R.id.listforopeninghours);
        //parsingforOpeninghours.parsing(ctc);
        lv.setAdapter(new Opening_HoursAdapter(getActivity(),ads));

        return v;
    }


    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }


}
