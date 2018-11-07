package com.iaz.filmesfamosos.presentation.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.iaz.filmesfamosos.Constants;
import com.iaz.filmesfamosos.R;
import com.iaz.filmesfamosos.models.Results;
import com.iaz.filmesfamosos.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.details);
        }

        if (getIntent().getExtras() != null) {
            Results movie = getIntent().getExtras().getParcelable(Constants.MOVIE_PARCELABLE);

            if (movie != null) {

                binding.setMovie(movie);

                if (movie.getPoster_path() != null && !movie.getPoster_path().isEmpty())
                    Picasso.with(this).load(Constants.POSTER_IMAGE_BASE_URL + movie.getPoster_path()).into(binding.ivPoster);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }
}
