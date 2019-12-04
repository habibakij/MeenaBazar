# MeenaBazar
MeenaBazar API resource data

Adapter Class where i added image with GLIDE Library

package com.example.meenabazardemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.meenabazardemo.Model.CatalogProduct;
import com.example.meenabazardemo.Model.ForImage;
import com.example.meenabazardemo.R;
import com.example.meenabazardemo.Retrofit.RetrofitClint;
import com.example.meenabazardemo.Util.ImageEndPoint;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecylarViewAdapter extends RecyclerView.Adapter<RecylarViewAdapter.RecylarViewHolder> {

    private Context context;
    private List<CatalogProduct> productList;
    private int count=0;
    RequestOptions options;
    private String getImageUrl;

    public RecylarViewAdapter(Context context, List<CatalogProduct> productList) {
        this.context = context;
        this.productList = productList;

        //options= new RequestOptions().centerCrop().placeholder(R.drawable.image_shape).error(R.drawable.image_shape);
    }

    @NonNull
    @Override
    public RecylarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent, false);
        RecylarViewHolder viewHolder= new RecylarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecylarViewHolder holder, int position) {
        CatalogProduct catalogProduct= productList.get(position);
        String title, prize, dis, image;
        title= catalogProduct.getProductName();
        prize= String.valueOf(catalogProduct.getPrice());
        dis= String.valueOf(catalogProduct.getDiscount());
        image= String.valueOf(catalogProduct.getImages());

        holder.productTitle.setText(title);
        holder.productPrize.setText(prize+"৳");
        holder.productDis.setText(dis+"৳");
        
        /*
        ImageEndPoint imageEndPoint= RetrofitClint.getRetrofitClint().create(ImageEndPoint.class);
        Call<ForImage> call= imageEndPoint.getImage();
        call.enqueue(new Callback<ForImage>() {
            @Override
            public void onResponse(Call<ForImage> call, Response<ForImage> response) {
                //Download_Image download_image= new Download_Image();

                getImageUrl= response.body().getProductImage();
            }
            @Override
            public void onFailure(Call<ForImage> call, Throwable t) {
                Toast.makeText(context, "Image request error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        */

        String baseUrl= "http://www.meenaclick.com/back_end/assets/product_images/";
        String mainUrlImage=baseUrl+image;
        Glide.with(context)
                .load(mainUrlImage)
                .centerCrop()
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class RecylarViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle, productPrize, productDis, productCount, addToBaz;
        ImageButton productImage, productPlus, productDelete;
        public RecylarViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle= itemView.findViewById(R.id.productTitle);
            productPrize= itemView.findViewById(R.id.productPrize);
            productDis= itemView.findViewById(R.id.productDis);
            productImage= itemView.findViewById(R.id.productImage);

            addToBaz= itemView.findViewById(R.id.add_to_baz);
            productCount= itemView.findViewById(R.id.productCount);
            productDelete= itemView.findViewById(R.id.delete);
            productPlus= itemView.findViewById(R.id.productPlus);

            ProductCritaria();
        }

        public void ProductCritaria (){
            productPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    addToBaz.setVisibility(View.GONE);
                    productDelete.setVisibility(View.VISIBLE);
                    productCount.setVisibility(View.VISIBLE);
                    productCount.setText(String.valueOf(count));

                }
            });
            productDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    productCount.setText(String.valueOf(count));
                    if (count<= 0){
                        addToBaz.setVisibility(View.VISIBLE);
                        productCount.setVisibility(View.GONE);
                        productDelete.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public class Download_Image extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url= new URL("http://www.meenaclick.com/back_end/assets/product_images/5d2ea8e12c6a7.png");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream= httpURLConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

when they can't permission to access image

![78214994_149086986394645_2606366956156092416_n](https://user-images.githubusercontent.com/31959794/70170317-a9843080-16f6-11ea-8bdb-9d8793c285d2.jpg)


Gradle file

apply plugin: 'com.android.application'

apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.example.meenabazardemo"
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'


    implementation 'com.squareup.retrofit2:converter-gson'
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

    //noinspection GradleCompatible
    implementation 'com.android.support:recyclerview-v7:28.0.0'

    implementation 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'

    implementation 'com.github.bumptech.glide:glide:4.6.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
    // Volley library
    implementation 'com.android.volley:volley:1.1.0'
    // Recyclerview Library
    implementation 'com.android.support:recyclerview-v7:29.0.2'
    implementation 'androidx.gridlayout:gridlayout:1.0.0'

}
