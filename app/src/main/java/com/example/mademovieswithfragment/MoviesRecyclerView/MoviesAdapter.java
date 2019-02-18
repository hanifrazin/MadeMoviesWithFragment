package com.example.mademovieswithfragment.MoviesRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mademovieswithfragment.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private Context context;
    private ArrayList<Movies> listmovies;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movies> getListmovies() {
        return listmovies;
    }

    public void setListmovies(ArrayList<Movies> listmovies) {
        this.listmovies = listmovies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_movies,viewGroup,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int i) {
        Glide.with(context)
             .load(getListmovies().get(i).getPoster())
             .apply(new RequestOptions().override(350, 550))
             .apply(new RequestOptions().fitCenter())
             .into(moviesViewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return listmovies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgView_Poster);
        }
    }
}
