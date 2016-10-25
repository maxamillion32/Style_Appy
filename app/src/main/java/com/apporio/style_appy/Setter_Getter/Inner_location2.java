package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 17-08-2016.
 */
public class Inner_location2 {

    @SerializedName("details")
    public Locationdetails details = new Locationdetails();

    @SerializedName("opening")
    public List<Inner_location> opening = new ArrayList<>();
}
