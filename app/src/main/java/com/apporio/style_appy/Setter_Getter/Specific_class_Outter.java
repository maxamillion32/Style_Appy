package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 02-08-2016.
 */
public class Specific_class_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("details")
    public List<Inner_details_class> details = new ArrayList<>();
}
