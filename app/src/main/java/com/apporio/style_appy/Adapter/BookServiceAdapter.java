package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.style_appy.R;

import java.util.ArrayList;




/**
 * Created by saifi45 on 4/26/2016.
 */
public class BookServiceAdapter  extends BaseAdapter {

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
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


      public BookServiceAdapter(Context c, ArrayList<String> catid, ArrayList<String> catname, ArrayList<String> catdesc) {
//    public  BookServiceAdapter(Context c,String catname[]){
        ctc = c;

        inflater = LayoutInflater.from(this.ctc);
          this.category_name= new ArrayList<>();
//        this.product_id=product_id11;
//        this.quantity=quantity;
//        this.images11=image11;
        //this.category_name=catname;
       this.category_name=catname;
          this.category_id=catid;
          this.category_descp=catdesc;
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

        public TextView categoryname, categorydescp, product_price, noofunit_product, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemformenulist, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.categoryname = (TextView) convertView.findViewById(R.id.itemname);
        holder.categorydescp = (TextView) convertView.findViewById(R.id.descp);
//        Log.e("catname", ""+category_name.get(position));
//
        holder.categoryname.setText(category_name.get(position));
        holder.categorydescp.setText(""+category_descp.get(position));

        return convertView;
    }
}


