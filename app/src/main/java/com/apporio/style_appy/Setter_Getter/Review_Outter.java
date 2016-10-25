package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 26-09-2016.
 */
public class Review_Outter {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public Inner_review reviewinner = new Inner_review();
}
