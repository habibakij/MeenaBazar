package com.example.meenabazardemo.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {

    public static Retrofit getRetrofitClint(){
        return new Retrofit.Builder()
                .baseUrl("https://www.meenaclick.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
