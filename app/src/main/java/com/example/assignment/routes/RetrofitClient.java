package com.example.assignment.routes;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit ourInstance;
    public static final String BASE_URL="https://api.github.com/";

    public static Retrofit getInstance()
    {
        if (ourInstance==null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return ourInstance;

        }
        return ourInstance;
    }

    private RetrofitClient() {
    }
}
