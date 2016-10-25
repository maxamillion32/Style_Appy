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

import com.apporio.style_appy.Adapter.BookServiceAdapter;
import com.apporio.style_appy.Parsing.parsingforCategories;
import com.apporio.style_appy.R;
import com.apporio.style_appy.SubcategoryActivity;

import views.ProgressWheel;

/**
 * Created by apporio6 on 02-07-2016.
 */
public class BookService_Fragment extends Fragment {

    public static ProgressWheel pb;
    public static ListView lv;
    Context ctc;
     String abc;
    String [] catname= {"Ladies Hair","Gents Hair","Waxing","Pedicures","Massage","Offers"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bookclass, container, false);
        ctc = getActivity();
        lv=(ListView)v.findViewById(R.id.lisformenu);
        pb=(ProgressWheel)v.findViewById(R.id.pb11);
        abc = getArguments().getString("bookby");
        parsingforCategories.parsing(getActivity());
       // lv.setAdapter(new BookServiceAdapter(getActivity(),catname ));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(ctc, SubcategoryActivity.class);
                in.putExtra("bookby",abc);
                Log.e("tetetet1", "" + abc);
                in.putExtra("category_id", parsingforCategories.catid.get(position));
                in.putExtra("header",parsingforCategories.catname.get(position));
               // in.putExtra("header", "" + catname[position]);
                startActivity(in);
            }
        });


        // Inflate the layout for this fragment


        return v;
    }


    @Override
    public void onResume() {

        super.onResume();
//        parsingforcategories.parsing(getActivity());
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