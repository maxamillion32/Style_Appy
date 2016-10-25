package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by apporio6 on 05-07-2016.
 */
public class GalleryAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    int[] category_name ;
    ArrayList<String> category_id = new ArrayList<String>();
    ArrayList<String> category_descp = new ArrayList<String>();
    ArrayList<String> Galid = new ArrayList<String>();
    ArrayList<String> Galname = new ArrayList<String>();
    ArrayList<String> Galdescp = new ArrayList<String>();
    ArrayList<String> Galimg = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();




    public GalleryAdapter(Context c, ArrayList<String> galid, ArrayList<String> galname, ArrayList<String> galdescp, ArrayList<String> galimg) {
        ctc = c;
        inflater = LayoutInflater.from(this.ctc);
        this.Galid=galid;
        this.Galname=galname;
        this.Galdescp=galdescp;
        this.Galimg=galimg;
    }

    @Override
    public int getCount() {
        return Galname.size();

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

        public ImageView galleryimage,  product_price, noofunit_product, cuisines;
        TextView tv1, tv2,categorydescp;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutfrgallery, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.galleryimage = (ImageView) convertView.findViewById(R.id.galleryimg);
        holder.categorydescp = (TextView) convertView.findViewById(R.id.descpgall);

        holder.categorydescp.setText(""+Galdescp.get(position));
        String url = "";
        if(Galimg.get(position).replace(" ", "%20").equals("")){
            url="abc";
        }
        else{
            url = Galimg.get(position).replace(" ", "%20");
        }
        String url2 = Apis_url.imagebaseurl+url;
        Log.e("jhdjdajhsd",""+url2);
        Picasso.with(ctc)
                .load(url2)
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.errorimg)         // optional
                .into(holder.galleryimage);


        return convertView;
    }
}


