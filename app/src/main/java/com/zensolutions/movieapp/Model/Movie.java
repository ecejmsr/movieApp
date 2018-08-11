package com.zensolutions.movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable{
    @SerializedName("id") public String id;

    @SerializedName("title") public String title;

    @SerializedName("year") public String year;

    @SerializedName("genre") public String genre;

    @SerializedName("poser") public String poster;
}
