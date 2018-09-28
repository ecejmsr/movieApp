package com.zensolutions.movieapp.View;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.zensolutions.movieapp.Model.Movie;
import com.zensolutions.movieapp.R;
import com.zensolutions.movieapp.ViewModel.ItemMovieViewModel;
import com.zensolutions.movieapp.databinding.ItemMovieBinding;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements Filterable{
    private List<Movie> movieList;
    private List<Movie> filteredMovieList= new ArrayList<>();
    private List<Movie> savedMovieList= new ArrayList<>();

    public MovieAdapter(){this.movieList= Collections.emptyList();}

    @NonNull
    @Override
    public MovieAdapter.MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MovieAdapterViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieAdapterViewHolder holder, int position) {
        holder.bindMovie(filteredMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredMovieList.size();
    }

    public void setMovieList(List<Movie> movieList){
        this.movieList= movieList;
        filteredMovieList= movieList;
        savedMovieList= movieList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();
                if(charString.isEmpty() || charString.equals("")){
                    filteredMovieList= savedMovieList;
                }
                else{
                    List<Movie> fMovie= new ArrayList<>();
                    for(Movie movie: savedMovieList){
                        if(movie.title.toLowerCase().contains(charString) || movie.genre.toLowerCase().contains(charString)){
                            fMovie.add(movie);
                        }
                    }
                    filteredMovieList= fMovie;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values = filteredMovieList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(filterResults.values != null){
                    filteredMovieList= (ArrayList<Movie>)filterResults.values;
                    notifyDataSetChanged();
                }
                else{
                    filteredMovieList= savedMovieList;
                    notifyDataSetChanged();
                }
            }
        };
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
                itemMovieBinding.getMovieViewModel().setMovie(movie);
            }
        }
    }


}
