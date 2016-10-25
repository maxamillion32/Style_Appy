package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 01-08-2016.
 */
public class Location_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_location2> data_location = new ArrayList<>();
}
