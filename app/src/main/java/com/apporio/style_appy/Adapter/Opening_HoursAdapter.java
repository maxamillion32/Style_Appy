package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apporio.style_appy.R;

import java.util.ArrayList;



/**
 * Created by saifi45 on 4/13/2016.
 */
public class Opening_HoursAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    ArrayList<String> opening_id = new ArrayList<String>();
    ArrayList<String> opening_day = new ArrayList<String>();
    ArrayList<String> lunchf = new ArrayList<String>();
    ArrayList<String> dinnerf = new ArrayList<String>();
    ArrayList<String> lunchto = new ArrayList<String>();
    ArrayList<String> dinnerto = new ArrayList<String>();



//    public Opening_HoursAdapter(Context c, ArrayList<String> openingday, ArrayList<String> lunch, ArrayList<String> dinner, ArrayList<String> lunchtimeto, ArrayList<String> dinnertimeto) {
//        ctc = c;
//
//        inflater = LayoutInflater.from(this.ctc);
//        this.opening_day=openingday;
//        this.lunchf=lunch;
//        this.dinnerf=dinner;
//        this.lunchto=lunchtimeto;
//        this.dinnerto=dinnertimeto;
//    }


    public Opening_HoursAdapter(Context c, String[] ads) {
        ctc = c;
        ad=ads;
        inflater = LayoutInflater.from(this.ctc);

    }

    @Override
    public int getCount() {
        return ad.length;

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
        public LinearLayout size, toppings, sausage, sizeno, toppingsno, sausageno;
        public ImageView deletebutton,plus,minus;
        public TextView product_name, lunch, dinner, noofunit_product, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforopeninghours, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.product_name = (TextView) convertView.findViewById(R.id.txt);
        holder.lunch = (TextView) convertView.findViewById(R.id.txt3);



//        ll.setBackgroundColor(Color.parseColor(color_arr[pos]));
//        pos++;
//        if (pos == color_arr.length) {
//            pos = 0;
//        }
        holder.product_name.setText(""+ad[position]);
//        holder.lunch.setText(""+lunchf.get(position)+" - "+lunchto.get(position));
//        holder.dinner.setText(""+dinnerf.get(position)+" - "+dinnerto.get(position));

        return convertView;
    }
}


