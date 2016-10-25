package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 02-08-2016.
 */
public class Inner_class_det {

    @SerializedName("details")
    public Inner_details_class inner_details_class = new Inner_details_class();

    @SerializedName("location_details")
    public Inner_location_details_class inner_location_details_class = new Inner_location_details_class();

    @SerializedName("staff_details")
    public Inner_staff_detail_class inner_staff_detail_class = new Inner_staff_detail_class();
}
