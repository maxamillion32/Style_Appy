package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 02-08-2016.
 */
public class Outter_classses {

    @SerializedName("status")
    public String status;

    @SerializedName("details")
    public List<Inner_outter_classes> detailss = new ArrayList<>();
}
