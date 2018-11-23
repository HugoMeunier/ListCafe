package com.example.meuni.cafeeuro;


import com.example.meuni.cafeeuro.models.Cafe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    @GET("cellars/{user}/bottles")
    Call<List<Cafe>> getAllCafes(@Path("user") String user);

}
