package com.zensolutions.movieapp.Network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zensolutions.movieapp.Network.URL.BASE_URL;

public class ApiFactory {

    public static MovieService create(){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .build();
        return retrofit.create(com.zensolutions.movieapp.Network.MovieService.class);
    }
}
