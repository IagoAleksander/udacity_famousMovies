package com.iaz.filmesfamosos.presentation.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.iaz.filmesfamosos.Constants;
import com.iaz.filmesfamosos.R;
import com.iaz.filmesfamosos.models.Response;
import com.iaz.filmesfamosos.models.Results;
import com.iaz.filmesfamosos.networkUtils.TheMovieDBApi;
import com.iaz.filmesfamosos.presentation.ui.adapters.MoviesAdapter;
import com.iaz.filmesfamosos.utils.Utilities;
import com.iaz.filmesfamosos.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter moviesAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.app_name);

        chooseButton(1);

        binding.btPopular.setOnClickListener(view -> chooseButton(Constants.POPULAR_MOVIES));

        binding.btTopRated.setOnClickListener(view -> chooseButton(Constants.TOP_RATED_MOVIES));

        binding.btUpcoming.setOnClickListener(view -> chooseButton(Constants.UPCOMING_MOVIES));

    }

    private void getPopular() {
        TheMovieDBApi.getPopular(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null)
                    setAdapter(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_recovering_data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTopRated() {
        TheMovieDBApi.getTopRated(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null)
                    setAdapter(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_recovering_data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUpcoming() {
        TheMovieDBApi.getUpcoming(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null)
                    setAdapter(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_recovering_data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(ArrayList<Results> results) {

        if (moviesAdapter == null)
            moviesAdapter = new MoviesAdapter(MainActivity.this, results);
        else {
            moviesAdapter.setNewList(results);
            moviesAdapter.notifyDataSetChanged();
        }

        binding.recyclerMovies.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        Utilities.BottomOffsetDecoration bottomOffsetDecoration = new Utilities.BottomOffsetDecoration(8);
        binding.recyclerMovies.addItemDecoration(bottomOffsetDecoration);
        binding.recyclerMovies.setAdapter(moviesAdapter);
    }

    private void chooseButton(int number) {
        switch (number) {
            case Constants.POPULAR_MOVIES:
                getPopular();
                binding.btPopular.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                binding.btTopRated.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                binding.btUpcoming.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                break;
            case Constants.TOP_RATED_MOVIES:
                getTopRated();
                binding.btPopular.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                binding.btTopRated.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                binding.btUpcoming.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                break;
            case Constants.UPCOMING_MOVIES:
                getUpcoming();
                binding.btPopular.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                binding.btTopRated.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                binding.btUpcoming.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                break;

        }
    }


}
