package com.example.meenabazardemo.Util;

import com.example.meenabazardemo.Model.MennaSub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoints {

    @GET("api/catalog-contents/1/cereals")
    Call<MennaSub> getProduct();
}
