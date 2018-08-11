package com.zensolutions.movieapp.Network;



import com.zensolutions.movieapp.Model.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieService {
    @GET
    Observable<UserResponse> fetchMovies(@Url String url);
}
