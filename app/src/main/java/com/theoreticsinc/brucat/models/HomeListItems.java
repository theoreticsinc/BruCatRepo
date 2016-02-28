package com.theoreticsinc.brucat.models;

/**
 * Created by Angelo on 9/30/2015.
 */
import com.google.gson.annotations.SerializedName;

public class HomeListItems {
    @SerializedName("details")
    public String details;

    @SerializedName("name")
    public String name;

    @SerializedName("pic_url")
    public String pic_url;

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;
}
