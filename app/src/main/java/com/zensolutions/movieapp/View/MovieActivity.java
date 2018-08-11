package com.zensolutions.movieapp.View;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zensolutions.movieapp.R;
import com.zensolutions.movieapp.ViewModel.MovieViewModel;
import com.zensolutions.movieapp.databinding.ActivityMoviesBinding;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.RecursiveAction;

public class MovieActivity extends AppCompatActivity implements Observer {
    private ActivityMoviesBinding movieActivityBinding;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();

    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof MovieViewModel){
            MovieAdapter movieAdapter= (MovieAdapter) movieActivityBinding.movieList.getAdapter();
            MovieViewModel movieViewModel = (MovieViewModel) o;
            movieAdapter.setMovieList(movieViewModel.getMovieList());
        }
    }

    private void initDataBinding(){
        movieActivityBinding= DataBindingUtil.setContentView(this, R.layout.activity_movies);
        movieViewModel = new MovieViewModel(this);
        movieActivityBinding.setMovieViewModel(movieViewModel);
    }

    private void setUpListOfMovies(RecyclerView listMovies){
        MovieAdapter movieAdapter= new MovieAdapter();
        listMovies.setAdapter(movieAdapter);
        //probs? prob
        listMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        movieViewModel.reset();
    }

}
