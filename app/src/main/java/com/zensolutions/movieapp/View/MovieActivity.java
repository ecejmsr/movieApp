package com.zensolutions.movieapp.View;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.zensolutions.movieapp.R;
import com.zensolutions.movieapp.ViewModel.MovieViewModel;
import com.zensolutions.movieapp.databinding.ActivityMoviesBinding;

import java.util.Observable;
import java.util.Observer;

public class MovieActivity extends AppCompatActivity implements Observer {
    private ActivityMoviesBinding movieActivityBinding;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfMovies(movieActivityBinding.movieList);
        setUpObserver(movieViewModel);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof MovieViewModel){
            final MovieAdapter movieAdapter= (MovieAdapter) movieActivityBinding.movieList.getAdapter();
            MovieViewModel movieViewModel = (MovieViewModel) observable;
            movieAdapter.setMovieList(movieViewModel.getMovieList());
        }
    }

    private void initDataBinding(){
        movieActivityBinding= DataBindingUtil.setContentView(this, R.layout.activity_movies);
        movieViewModel = new MovieViewModel(this);
        movieActivityBinding.setMovieViewModel(movieViewModel);
    }

    private void setUpListOfMovies(RecyclerView listMovies){
        movieAdapter= new MovieAdapter();
        listMovies.setAdapter(movieAdapter);
        listMovies.setLayoutManager(new GridLayoutManager(this, 2));
        movieActivityBinding.svActivityMovies.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return false;
            }
        });
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
