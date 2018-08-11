package com.zensolutions.movieapp.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.zensolutions.movieapp.AppController;
import com.zensolutions.movieapp.Model.Movie;
import com.zensolutions.movieapp.Model.UserResponse;
import com.zensolutions.movieapp.Network.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.zensolutions.movieapp.Network.URL.BASE_URL;

public class MovieViewModel extends Observable{
    public ObservableInt movieRecycler;
    public ObservableInt movieLabel;

    private List<Movie> movieList;
    private Context context;
    private CompositeDisposable compositeDisposable= new CompositeDisposable();

    public MovieViewModel(Context context){
        this.context= context;
        this.movieList= new ArrayList<>();
        movieRecycler= new ObservableInt();
        fetchMovieList();
    }

    private void fetchMovieList(){
        AppController appController= AppController.create(context);
        MovieService movieService= appController.getMovieService();

        Disposable disposable= movieService.fetchMovies(BASE_URL)
                                .subscribeOn(appController.subcribeScheduler())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<UserResponse>() {
                                    @Override
                                    public void accept(UserResponse userResponse) throws Exception {
                                        updateMovieDataList(userResponse.getMovieList());
                                    }
                                });
        compositeDisposable.add(disposable);
    }

    private void updateMovieDataList(List<Movie> movies){
        movieList.addAll(movies);
        setChanged();
        notifyObservers();
    }

    public List<Movie> getMovieList(){
        return movieList;
    }

    private void unSubcribeFromObservable(){
        if(compositeDisposable != null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    public void reset(){
        unSubcribeFromObservable();
        compositeDisposable=null;
        context=null;
    }
}
