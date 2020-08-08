package com.example.resume.shop.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.resume.WelcomeActivity;
import com.example.resume.common.DynamicType;
import com.example.resume.download.database.DBHelper;
import com.example.resume.retrofit.Api;
import com.example.resume.retrofit.ProductAPI;
import com.example.resume.shop.models.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchService extends Service {
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sharedPreferences=getSharedPreferences("product", Context.MODE_PRIVATE);
        Boolean isFetched=sharedPreferences.getBoolean("isFetched",false);
        if (isFetched==true)
        {
            return START_STICKY;
        }

        Api api = ProductAPI.getAPI();

        Call<ArrayList<Product>> request = api.getproducts();

        request.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> list = response.body();

                DBHelper db = new DBHelper(FetchService.this);
                for (Product product : list) {
                    db.insertproduct(product);
                } sharedPreferences.edit().putBoolean("isFetched",true).apply();
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.e(WelcomeActivity.class.getSimpleName(), t.getMessage());
            }
        });
        return START_STICKY;
    }

}
