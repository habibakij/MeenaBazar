package com.example.meenabazardemo.Util;

import com.example.meenabazardemo.Model.ForImage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageEndPoint {
    @GET("api/catalog-contents/1/cereals")
    Call<ForImage> getImage();
}
