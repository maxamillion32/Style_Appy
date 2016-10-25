package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 23-08-2016.
 */
public class All_Services_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_all_services> data_services = new ArrayList<>();

}
