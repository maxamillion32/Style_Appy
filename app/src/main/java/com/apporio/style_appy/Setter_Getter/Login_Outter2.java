package com.apporio.style_appy.Setter_Getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 12-07-2016.
 */
public class Login_Outter2 {
    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("user_info")
    public List<Inner_login> userinfo = new ArrayList<>();
}
