package com.example.meenabazardemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meenabazardemo.Adapter.RecylarViewAdapter;
import com.example.meenabazardemo.Model.CatalogProduct;
import com.example.meenabazardemo.Model.MennaSub;
import com.example.meenabazardemo.Retrofit.RetrofitClint;
import com.example.meenabazardemo.Util.ApiEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    private List<CatalogProduct> list;
    private RecylarViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewList= findViewById(R.id.reclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        recyclerViewList.setLayoutManager(mLayoutManager);
        recyclerViewList.setItemAnimator(new DefaultItemAnimator());
        list= new ArrayList<>();

        ApiEndPoints apiEndPoints= RetrofitClint.getRetrofitClint().create(ApiEndPoints.class);
        Call<MennaSub> call= apiEndPoints.getProduct();
        call.enqueue(new Callback<MennaSub>() {
            @Override
            public void onResponse(Call<MennaSub> call, Response<MennaSub> response) {
                int status= response.code();
                if (status == 200) {
                    Toast.makeText(MainActivity.this, "server request successfully", Toast.LENGTH_SHORT).show();
                    MennaSub mennaSub= response.body();
                    list= mennaSub.getCatalogProducts();
                    /*String id= String.valueOf(mennaSub.getCatalogContents().getId());
                    Log.d("ID: ", id);*/
                    Log.d("List Size: ", String.valueOf(list.size()));
                    adapter= new RecylarViewAdapter(MainActivity.this, list);
                    recyclerViewList.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Server request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MennaSub> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error is: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}


