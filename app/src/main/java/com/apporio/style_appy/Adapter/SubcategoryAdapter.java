package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.style_appy.R;
import com.apporio.style_appy.SubcategoryActivity;

import java.util.ArrayList;

/**
 * Created by apporio6 on 02-07-2016.
 */
public class SubcategoryAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    ArrayList<String> category_name ;
    ArrayList<String> category_id = new ArrayList<String>();
    ArrayList<String> category_descp = new ArrayList<String>();
    ArrayList<String> food_category = new ArrayList<String>();
    ArrayList<String> names12 = new ArrayList<String>();
    ArrayList<String> prices21 = new ArrayList<String>();
    ArrayList<String> duration = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


        public SubcategoryAdapter(Context c, ArrayList<String> name,
                                  ArrayList<String> descp, ArrayList<String> rates,
                                  ArrayList<String> duration) {
//    public  SubcategoryAdapter(Context c,String catname[]){
        ctc = c;
        this.category_name = new ArrayList<>();
        inflater = LayoutInflater.from(this.ctc);
//        this.product_id=product_id11;
//        this.quantity=quantity;
        this.duration=duration;
        this.category_name=name;

        this.category_descp=descp;
        this.prices21=rates;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
        return category_name.size();

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

        public TextView categoryname, categorydescp, product_price, durationtxt, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforspecificitem, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.categoryname = (TextView) convertView.findViewById(R.id.itemname);
        holder.categorydescp = (TextView) convertView.findViewById(R.id.descp);
        holder.product_price = (TextView) convertView.findViewById(R.id.price);
        holder.durationtxt = (TextView) convertView.findViewById(R.id.duration);
//        Log.e("catname", ""+category_name.get(position));
//
        holder.categoryname.setText(category_name.get(position));
        holder.categorydescp.setText(""+category_descp.get(position));
        holder.product_price.setText("£ "+prices21.get(position));
        holder.durationtxt.setText("Duration - "+duration.get(position));

        return convertView;
    }
}


