package com.example.resume.retrofit;

import com.example.resume.shop.models.Product;
import com.example.resume.weather.models.WeatherResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather?appid=92756c24107bc39dd0a7541f66ba55c5&units=metric")
    Call<WeatherResponse> fetchInfo(@Query("q") String name);

    @GET("v2/5dc92d682f0000760073ea4d")
    Call<ArrayList<Product>> getproducts();

}
