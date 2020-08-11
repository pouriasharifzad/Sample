package com.example.resume.weather;

import android.content.Context;
import android.util.Log;

import com.example.resume.common.BaseViewModelDelegate;
import com.example.resume.common.DynamicType;
import com.example.resume.retrofit.Api;
import com.example.resume.retrofit.WeatherAPI;
import com.example.resume.weather.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel {

    DynamicType<WeatherResponse> responseDynamicType;
    BaseViewModelDelegate delegate;
    Context context;

    public WeatherViewModel(BaseViewModelDelegate delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
        responseDynamicType = new DynamicType<>(null);
    }

    public void fetchInfo(String city)
    {
        delegate.showLoading();
        Api api = WeatherAPI.getAPI();
        Call<WeatherResponse> request = api.fetchInfo(city);
        request.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                responseDynamicType.setValue(response.body());
                delegate.hideLoading();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("TAg","didi");
            }
        });
    }


}
