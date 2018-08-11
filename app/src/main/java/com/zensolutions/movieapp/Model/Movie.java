package com.zensolutions.movieapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable{
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("year")
    @Expose
    public String year;

    @SerializedName("genre")
    @Expose
    public String genre;

    @SerializedName("poster")
    @Expose
    public String poster;
}
