package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apporio6 on 26-08-2016.
 */
public class Detailsstaff {
    @SerializedName("staff_id")
    public String staff_id;

    @SerializedName("staff_client_id")
    public String staff_client_id;

    @SerializedName("staff_name")
    public String staff_name;

    @SerializedName("staff_position")
    public String staff_position;

    @SerializedName("staff_img")
    public String staff_img;

    @SerializedName("staff_biography")
    public String staff_description;

    @SerializedName("staff_status")
    public String staff_status;
}
