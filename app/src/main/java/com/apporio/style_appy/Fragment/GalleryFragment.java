package com.apporio.style_appy.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.apporio.style_appy.Adapter.BookServiceAdapter;
import com.apporio.style_appy.Adapter.GalleryAdapter;
import com.apporio.style_appy.Api_s.Apis_url;
import com.apporio.style_appy.Parsing.parsingforGallery;
import com.apporio.style_appy.R;
import com.apporio.style_appy.Singleton.VolleySingleton;
import com.apporio.style_appy.SubcategoryActivity;
import com.squareup.picasso.Picasso;

import views.ProgressWheel;
import views.TouchImageView;

/**
 * Created by apporio6 on 05-07-2016.
 */
public class GalleryFragment extends Fragment {

    public static ProgressWheel pb;
    public static ListView lv;
    Context ctc;

    private ImageLoader mImageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        ctc = getActivity();
        lv=(ListView)v.findViewById(R.id.lisformenu);
        pb=(ProgressWheel)v.findViewById(R.id.pb11);
//        parsingforGallery.parsing(ctc);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                ShowPhotoDialog(parsingforGallery.galimg.get(position));
            }
        });


        // Inflate the layout for this fragment


        return v;
    }

    private void ShowPhotoDialog(String img) {
         final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(
                R.style.customDialogAnimation);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogforphotoopen);
        ImageView cancel = (ImageView) dialog.findViewById(R.id.close);
        TouchImageView pic = (TouchImageView)dialog.findViewById(R.id.imagess);
        mImageLoader = VolleySingleton.getInstance(getContext())
                .getImageLoader();
        String url = Apis_url.imagebaseurl +img.replace(" ","%20");
        Log.e("urll",""+url);
        mImageLoader.get(url,
                ImageLoader.getImageListener(pic,
                R.drawable.stub, R.drawable
                        .stub));
        pic.setImageUrl(""+url, mImageLoader);
//            String url="";
//        if(img.replace(" ", "%20").equals("")){
//            url="abc";
//        }
//        else{
//            url = img.replace(" ", "%20");
//        }
//        String url2 = Apis_url.imagebaseurl+url;
//        Log.e("jhdjdajhsd",""+url2);
//        Picasso.with(getActivity())
//                .load(url2)
//                .placeholder(R.drawable.stub) // optional
//                .error(R.drawable.errorimg)         // optional
//                .into(pic);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run()

                                        {

                                            dialog.dismiss();
                                        }
                                    }
                        , 500);
            }
        });


        dialog.show();

    }


    @Override
    public void onResume() {

        super.onResume();
        parsingforGallery.parsing(ctc);
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