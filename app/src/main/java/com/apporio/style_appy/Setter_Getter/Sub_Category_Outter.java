package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 12-07-2016.
 */
public class Sub_Category_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_sub_categories> data_category = new ArrayList<>();
}
