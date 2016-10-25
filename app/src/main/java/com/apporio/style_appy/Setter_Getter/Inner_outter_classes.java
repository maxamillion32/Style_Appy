package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 02-08-2016.
 */
public class Inner_outter_classes {

    @SerializedName("date")
    public String date;

    @SerializedName("day")
    public String day;

    @SerializedName("classes")
    public ArrayList<Inner_classes_details> classes = new ArrayList<>();
}
