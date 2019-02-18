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
public class UpComingFragment extends Fragment {
    private RecyclerView rvUpComing;
    private ArrayList<Movies> upcoming = new ArrayList<>();
    SwipeRefreshLayout swipeUpComing;
    String[] dataTitleUpComing;
    String[] dataReleaseUpComing;
    String[] dataDescriptionUpComing;
    TypedArray dataPosterUpComing;
    Movies moviesUpComing;
    MoviesAdapter adapterUpComing;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        rvUpComing = view.findViewById(R.id.rv_up_coming);
        rvUpComing.setHasFixedSize(true);

        ShowRvUpComing();
        getDataUpComing();
        addItemUpComing();

        swipeUpComing = view.findViewById(R.id.SwipeUpComing);
        swipeUpComing.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ShowRvUpComing();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeUpComing.setRefreshing(false);
                    }
                },4000);
            }
        });

        return view;
    }

    private void addItemUpComing() {
        for(int i=0; i<dataTitleUpComing.length ;i++){
            moviesUpComing = new Movies();
            moviesUpComing.setJudul(dataTitleUpComing[i]);
            moviesUpComing.setTanggal(dataReleaseUpComing[i]);
            moviesUpComing.setDeskripsi(dataDescriptionUpComing[i]);
            moviesUpComing.setPoster(dataPosterUpComing.getResourceId(i,-1));
            upcoming.add(moviesUpComing);
        }
        adapterUpComing.setListmovies(upcoming);
    }

    private void getDataUpComing() {
        dataTitleUpComing = getResources().getStringArray(R.array.upcoming_title);
        dataReleaseUpComing = getResources().getStringArray(R.array.upcoming_release_date);
        dataDescriptionUpComing = getResources().getStringArray(R.array.upcoming_description);
        dataPosterUpComing = getResources().obtainTypedArray(R.array.upcoming_poster);
    }

    private void ShowRvUpComing() {
        rvUpComing.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapterUpComing = new MoviesAdapter(getContext());
        adapterUpComing.setListmovies(upcoming);
        rvUpComing.setAdapter(adapterUpComing);

        ItemClickSupport.addTo(rvUpComing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               moviesUpComing = new Movies();
               moviesUpComing.setJudul(dataTitleUpComing[position]);
               moviesUpComing.setTanggal(dataReleaseUpComing[position]);
               moviesUpComing.setDeskripsi(dataDescriptionUpComing[position]);
               moviesUpComing.setPoster(dataPosterUpComing.getResourceId(position,-1));

               Intent UpComingIntent = new Intent(getActivity(), MovieDetailActivity.class);
               UpComingIntent.putExtra(MovieDetailActivity.EXTRA_PERSON,moviesUpComing);
               startActivity(UpComingIntent);
            }
        });
    }

}
