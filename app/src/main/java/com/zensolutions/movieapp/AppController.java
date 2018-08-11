package com.zensolutions.movieapp;

import android.app.Application;
import android.content.Context;

import com.zensolutions.movieapp.Network.ApiFactory;
import com.zensolutions.movieapp.Network.MovieService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {
    private MovieService movieService;
    private Scheduler scheduler;

    private static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context){
        return AppController.get(context);
    }

    public MovieService getMovieService() {
        if(movieService == null){
            movieService= ApiFactory.create();
        }
        return movieService;
    }

    public Scheduler subcribeScheduler(){
        if(scheduler==null){
            scheduler= Schedulers.io();
        }
        return scheduler;
    }
    public void setMovieService(MovieService movieService){
        this.movieService= movieService;
    }
    public void setScheduler(Scheduler scheduler){
        this.scheduler= scheduler;
    }
}
