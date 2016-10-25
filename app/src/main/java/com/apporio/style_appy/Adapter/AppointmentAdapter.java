package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.style_appy.Parsing.parsingforDeleteOrder;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Setter_Getter.Inner_All_orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 04-07-2016.
 */
public class AppointmentAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    String category_name[] ;
    ArrayList<String> orderid = new ArrayList<String>();
    ArrayList<String> orderdate = new ArrayList<String>();
    ArrayList<String> orderstatus = new ArrayList<String>();
    ArrayList<String> ordertype = new ArrayList<String>();
    ArrayList<String> orderprice = new ArrayList<String>();
    List<Inner_All_orders> data = new ArrayList<>();

    ArrayList<String> deliver_time = new ArrayList<String>();




    public AppointmentAdapter(Context c, ArrayList<String> orderid,
                              ArrayList<String> orderdate, ArrayList<String> orderstatus,
                              ArrayList<String> ordertype, ArrayList<String> orderprice) {
        ctc = c;

        inflater = LayoutInflater.from(this.ctc);
        this.orderdate=orderdate;
        this.orderstatus=orderstatus;
        this.ordertype=ordertype;
        this.orderid=orderid;
        this.orderprice=orderprice;
//        this.category_descp=catdesc;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;
    }

    public AppointmentAdapter(Context c,List<Inner_All_orders> data) {
        ctc = c;

        inflater = LayoutInflater.from(this.ctc);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();

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

        public TextView categoryname, categorydescp, loc, noofunit_product, cuisines;
        public ImageView del, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforappointment, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.categoryname = (TextView) convertView.findViewById(R.id.txt);
        holder.categorydescp = (TextView) convertView.findViewById(R.id.tnd);
        holder.loc = (TextView) convertView.findViewById(R.id.loc);
        holder.del = (ImageView) convertView.findViewById(R.id.deleteitem);
//        Log.e("catname", ""+category_name.get(position));
//
        holder.categorydescp.setText(data.get(position).time +", " + data.get(position).date);
        holder.loc.setText("" + data.get(position).location_name);

        holder.categoryname.setText("" + data.get(position).treatement_name );

        holder.del.setTag(position);
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                parsingforDeleteOrder.parsing(ctc,data.get(position).order_id);

            }
        });
//        holder.categorydescp.setText(""+category_descp.get(position));

        return convertView;
    }
}


