package com.example.mademovieswithfragment.MoviesFragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mademovieswithfragment.MovieDetailActivity;
import com.example.mademovieswithfragment.MoviesRecyclerView.ItemClickSupport;
import com.example.mademovieswithfragment.MoviesRecyclerView.Movies;
import com.example.mademovieswithfragment.MoviesRecyclerView.MoviesAdapter;

import com.example.mademovieswithfragment.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {
    private RecyclerView rvNowPlaying;
    private ArrayList<Movies> nowplaying = new ArrayList<>();
    private SwipeRefreshLayout swipeNow;
    String[] dataTitleNow;
    String[] dataReleaseNow;
    String[] dataDescriptionNow;
    TypedArray dataPosterNow;
    MoviesAdapter adapterNow;
    Movies moviesNow;

    public NowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now, container, false);

        rvNowPlaying = view.findViewById(R.id.rv_now_playing);
        rvNowPlaying.setHasFixedSize(true);

        ShowRvNow();
        getDataNow();
        addItemNow();

        swipeNow = view.findViewById(R.id.SwipeNow);
        swipeNow.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ShowRvNow();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeNow.setRefreshing(false);
                    }
                },4000);
            }
        });

        return view;
    }

    private void ShowRvNow() {
        rvNowPlaying.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapterNow = new MoviesAdapter(getContext());
        adapterNow.setListmovies(nowplaying);
        rvNowPlaying.setAdapter(adapterNow);

        ItemClickSupport.addTo(rvNowPlaying).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            moviesNow = new Movies();
            moviesNow.setJudul(dataTitleNow[position]);
            moviesNow.setTanggal(dataReleaseNow[position]);
            moviesNow.setDeskripsi(dataDescriptionNow[position]);
            moviesNow.setPoster(dataPosterNow.getResourceId(position,-1));

            Intent NowIntent = new Intent(getActivity(), MovieDetailActivity.class);
            NowIntent.putExtra(MovieDetailActivity.EXTRA_PERSON,moviesNow);
            startActivity(NowIntent);
            }
        });
    }

    private void getDataNow(){
        dataTitleNow = getResources().getStringArray(R.array.now_title);
        dataReleaseNow = getResources().getStringArray(R.array.now_release_date);
        dataPosterNow = getResources().obtainTypedArray(R.array.now_poster);
        dataDescriptionNow = getResources().getStringArray(R.array.now_description);
    }

    private void addItemNow(){
        for(int i = 0; i < dataTitleNow.length; i++){
            moviesNow = new Movies();
            moviesNow.setJudul(dataTitleNow[i]);
            moviesNow.setTanggal(dataReleaseNow[i]);
            moviesNow.setPoster(dataPosterNow.getResourceId(i,-1));
            moviesNow.setDeskripsi(dataDescriptionNow[i]);
            nowplaying.add(moviesNow);
        }
        adapterNow.setListmovies(nowplaying);
    }

}
