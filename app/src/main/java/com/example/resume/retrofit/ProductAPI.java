package com.example.resume.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAPI {
    public static Api api = null;

    public static Api getAPI() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(Api.class);
        }

        return api;
    }
}
