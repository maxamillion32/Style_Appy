package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 19-07-2016.
 */
public class Gallery_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_gallery> data_gallery = new ArrayList<>();
}
