package com.example.resume.weather;

import android.util.Log;

import com.example.resume.common.DynamicType;
import com.example.resume.retrofit.Api;
import com.example.resume.retrofit.WeatherAPI;
import com.example.resume.weather.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel {

    DynamicType<WeatherResponse> responseDynamicType;

    public WeatherViewModel() {
        responseDynamicType = new DynamicType<>(null);
    }

    public void fetchInfo(String city)
    {
        Api api = WeatherAPI.getAPI();
        Call<WeatherResponse> request = api.fetchInfo(city);
        request.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                responseDynamicType.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("TAg","didi");
            }
        });
    }


}
