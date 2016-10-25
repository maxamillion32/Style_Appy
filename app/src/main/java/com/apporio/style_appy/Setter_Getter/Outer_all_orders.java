package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 6/2/2016.
 */
public class Outer_all_orders {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_All_orders> data = new ArrayList<Inner_All_orders>();
}
