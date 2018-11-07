package com.iaz.filmesfamosos.networkUtils;

import com.iaz.filmesfamosos.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;


public interface TheMovieDBService {

    //TODO insert API key
    String API_KEY = "";

    @GET("3/movie/popular?api_key=" + API_KEY)
    Call<Response> getPopular();

    @GET("3/movie/top_rated?api_key=" + API_KEY)
    Call<Response> getTopRated();

    @GET("3/movie/upcoming?api_key=" + API_KEY)
    Call<Response> getUpcoming();

}

