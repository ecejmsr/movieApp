package com.zensolutions.movieapp.View;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zensolutions.movieapp.Model.Movie;
import com.zensolutions.movieapp.R;
import com.zensolutions.movieapp.ViewModel.ItemMovieViewModel;
import com.zensolutions.movieapp.databinding.ItemMovieBinding;


import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private List<Movie> movieList;
    public MovieAdapter(){this.movieList= Collections.emptyList();}

    @NonNull
    @Override
    public MovieAdapter.MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MovieAdapterViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieAdapterViewHolder holder, int position) {
        holder.bindMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList){
        this.movieList= movieList;
        notifyDataSetChanged();
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemMovieBinding itemMovieBinding;

        public MovieAdapterViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.itemMovie);
            this.itemMovieBinding= itemMovieBinding;
        }

        void bindMovie(Movie movie){
            if(itemMovieBinding.getMovieViewModel()==null){
                itemMovieBinding.setMovieViewModel(new ItemMovieViewModel(movie, itemView.getContext()));
            }
            else{
                //??
                itemMovieBinding.getMovieViewModel();
            }
        }
    }
}
