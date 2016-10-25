package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 5/7/2016.
 */
public class Accountinfoshow2_Outter {
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<Inner_accountshow> data = new ArrayList<Inner_accountshow>();
}
