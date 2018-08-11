package com.zensolutions.movieapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private List<Movie> movieList= null;

    public List<Movie> getMovieList(){return movieList;}

    public void setMovieList(List<Movie> mMovieList){this.movieList= mMovieList;}
}
