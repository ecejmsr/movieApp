package com.zensolutions.movieapp.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zensolutions.movieapp.Model.Movie;

public class ItemMovieViewModel extends BaseObservable{
    private Movie movie;
    private Context context;

    @BindingAdapter("movieImageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public ItemMovieViewModel(Movie movie, Context context){
        this.movie= movie;
        this.context= context;
    }

    public String getMovieThumb(){
        return movie.poster;
    }

    public String getTitle(){
        return movie.title;
    }

    public String getGenre(){
        return movie.genre;
    }

    public void onItemClick(View v){
        //TODO JOE: go ahead and call movie detail activity
    }

    public void setMovie(Movie movie){
        this.movie=movie;
        notifyChange();
    }
}
