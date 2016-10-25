package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 20-07-2016.
 */
public class Staff_detail_Outter {


    @SerializedName("status")
    public String status;

    @SerializedName("details")
    public Inner_Staff_detail data_staff_detail = new Inner_Staff_detail();

    @SerializedName("services")
    public List<Inner_services> services = new ArrayList<>();

}
