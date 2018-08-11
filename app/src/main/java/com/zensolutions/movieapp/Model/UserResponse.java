package com.zensolutions.movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("results") private List<Movie> movieList;

    public List<Movie> getMovieList(){return movieList;}

    public void setMovieList(List<Movie> mMovieList){this.movieList= mMovieList;}
}
