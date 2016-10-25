package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.style_appy.R;

import java.util.ArrayList;

/**
 * Created by apporio6 on 04-07-2016.
 */
public class StaffAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    String category_name[] ;
    ArrayList<String> staffid = new ArrayList<String>();
    ArrayList<String> staffname = new ArrayList<String>();
    ArrayList<String> staffdescp = new ArrayList<String>();
    ArrayList<String> staffpos = new ArrayList<String>();
    ArrayList<String> staffimg = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


        public StaffAdapter(Context c, ArrayList<String> staffid, ArrayList<String> staffname,
                            ArrayList<String> staffdescp, ArrayList<String> staffposition,
                            ArrayList<String> staffimage) {
   // public  StaffAdapter(Context c,String catname[]){
        ctc = c;

        inflater = LayoutInflater.from(this.ctc);
        this.staffid=staffid;
        this.staffname=staffname;
        this.staffdescp=staffdescp;
        this.staffpos=staffposition;
        this.staffimg=staffimage;
//        this.category_descp=catdesc;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
        return staffname.size();

    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {

        public TextView staffname, staffdescp,  staffpos, staffid;
        ImageView staffimg, tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforstaff, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.staffname = (TextView) convertView.findViewById(R.id.itemname);
        holder.staffdescp = (TextView) convertView.findViewById(R.id.descp);
//        Log.e("catname", ""+category_name.get(position));
//
        holder.staffname.setText(""+staffname.get(position));
       holder.staffdescp.setText(""+staffpos.get(position));

        return convertView;
    }
}


