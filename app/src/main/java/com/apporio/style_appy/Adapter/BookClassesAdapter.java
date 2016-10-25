package com.apporio.style_appy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apporio.style_appy.Parsing.parsingforClasses;
import com.apporio.style_appy.R;
import com.apporio.style_appy.ReviewActivity;

import java.util.ArrayList;


/**
 * Created by apporio6 on 04-07-2016.
 */
public class BookClassesAdapter extends BaseAdapter  {

    private ArrayList<String> countries;
    private ArrayList<String> classtimeb;
    private ArrayList<String> classdurationb;
    private ArrayList<String> placesleftb;
    private ArrayList<String> locab;
    private ArrayList<String> staffnameb;
    String header[];
    Context c;
    private LayoutInflater inflater;

    public BookClassesAdapter(Context context, ArrayList<String> catname, ArrayList<String> classtime, ArrayList<String> classduration, ArrayList<String> staffname, ArrayList<String> locations, ArrayList<String> placesleft) {
        inflater = LayoutInflater.from(context);
       countries=catname;
        c= context;
        classdurationb = classduration;
        classtimeb = classtime;
        placesleftb=placesleft;
        locab = locations;
        staffnameb = staffname;

    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.test_list_item_layout, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.head);
            holder.placesleft = (TextView) convertView.findViewById(R.id.pllft);
            holder.location = (TextView) convertView.findViewById(R.id.loc);
            holder.duration = (TextView) convertView.findViewById(R.id.tnd);
            holder.staffname = (TextView) convertView.findViewById(R.id.stff);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.llforintent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(countries.get(position));
        if(!placesleftb.get(position).equals("1")) {
            holder.placesleft.setText(placesleftb.get(position)+ " places left");
        }
        else {
            holder.placesleft.setText(placesleftb.get(position)+ " place left");

        }
        holder.location.setText(locab.get(position));
        holder.duration.setText("Duration - "+classtimeb.get(position)+" minutes");
        holder.staffname.setText(staffnameb.get(position));
        holder.ll.setTag(position);



        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent in = new Intent(c, ReviewActivity.class);
                in.putExtra("id", parsingforClasses.classid.get(pos));
                in.putExtra("activity", "rev");
                in.putExtra("Date", parsingforClasses.datess.get(pos));
//                in.putExtra("header",parsingforcategories.catname.get(position));
                in.putExtra("fragment","class");
                c.startActivity(in);
            }
        });
        return convertView;
    }






    public static class ViewHolder {
        TextView text,placesleft,location,duration,staffname;
        LinearLayout ll;
        @SuppressWarnings("unchecked")
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }
}