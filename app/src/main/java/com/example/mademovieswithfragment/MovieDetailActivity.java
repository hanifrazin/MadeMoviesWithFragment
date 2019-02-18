package com.example.mademovieswithfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mademovieswithfragment.MoviesRecyclerView.Movies;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";
    TextView txtJudul, txtDate, txtDeskripsi;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movies detailMovies = getIntent().getParcelableExtra(EXTRA_PERSON);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(detailMovies.getJudul());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtJudul = findViewById(R.id.txtJudul);
        txtJudul.setText(detailMovies.getJudul());

        txtDate = findViewById(R.id.txtDateRelease);
        txtDate.setText(detailMovies.getTanggal());

        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        txtDeskripsi.setText(detailMovies.getDeskripsi());

        imgPoster = findViewById(R.id.PosterView);
        Glide.with(this).load(detailMovies.getPoster())
             .apply(new RequestOptions().override(350,550))
             .into(imgPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
