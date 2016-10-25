package com.apporio.style_appy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.apporio.style_appy.Parsing.parsingforaccountinfo;
import com.apporio.style_appy.R;

import views.ProgressWheel;

/**
 * Created by saifi45 on 4/13/2016.
 */
public class AccountsFragment extends Fragment {



    Context ctc;
    LinearLayout save;
    public static ProgressWheel pb;
   public static EditText name, email, phoneno, password, streetno,town, postcode,specialdirections;
    String [] ads= {"Meal Deals","Pizza","Sides","Drinks","Deserts","Extras"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accounts, container, false);
        ctc = getActivity();
        save = (LinearLayout)v.findViewById(R.id.save);
        name = (EditText)v.findViewById(R.id.name);
        email = (EditText)v.findViewById(R.id.email);
        phoneno = (EditText)v.findViewById(R.id.phnno);
        password = (EditText)v.findViewById(R.id.password);

        pb=(ProgressWheel)v.findViewById(R.id.pb11);


        parsingforaccountinfo.parsing(ctc);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        ctc.getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                if(name.getText().toString().trim().equals("")||phoneno.getText().toString().trim().equals("")||email.getText().toString().trim().equals("")
                        ||password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(ctc, "Please fill required fields ...", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(ctc, "Saved ...", Toast.LENGTH_SHORT).show();

                    parsingforaccountinfo.parsingsave(ctc, name.getText().toString().trim(),
                            email.getText().toString().trim(),phoneno.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });



        return v;
    }


    @Override
    public void onResume() {

        super.onResume();

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