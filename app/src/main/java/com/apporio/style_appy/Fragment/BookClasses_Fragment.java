package com.apporio.style_appy.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apporio.style_appy.Adapter.BookClassesAdapter;

import com.apporio.style_appy.Parsing.parsingforClasses;
import com.apporio.style_appy.R;
import com.apporio.style_appy.ReviewActivity;

import java.util.ArrayList;

import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter;
import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter.Section;
import views.ProgressWheel;

/**
 * Created by apporio6 on 04-07-2016.
 */
public class BookClasses_Fragment extends Fragment {

    public static ProgressWheel pb;
    public static ListView stickyList;
    Context ctc;

//    String [] header= {"Day, Date","Day, Date"};
//    String [] catname= {"Class 1","Class 2","Class 1","Class 2","Class 3","Class 4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bookservice, container, false);
        ctc = getActivity();
        stickyList = (ListView) v.findViewById(R.id.list);

//         Integer[] mHeaderPositions = { 0, 2};
        pb=(ProgressWheel)v.findViewById(R.id.pb11);


//      BookClassesAdapter  mAdapter = new BookClassesAdapter(getActivity(),catname);



        stickyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("clpos",""+position);

            }
        });

        return v;
    }


    @Override
    public void onResume() {

        super.onResume();
        parsingforClasses.parsing(getActivity());
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