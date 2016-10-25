package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 23-08-2016.
 */
public class Staffservicebystaff_outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_Staffservicebystaff> data_staff = new ArrayList<>();
}
