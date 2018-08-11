package com.zensolutions.movieapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDELETE {
    @SerializedName("data")
    @Expose
    private List<Movie> data= null;
    public List<Movie> getData(){
        return data;
    }

    public void setData(List<Movie> data){
        this.data= data;
    }
}
