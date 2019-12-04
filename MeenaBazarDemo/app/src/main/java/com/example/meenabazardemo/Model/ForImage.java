package com.example.meenabazardemo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForImage {

    @SerializedName("product_image")
    @Expose
    private String productImage;

    public ForImage(String productImage) {
        this.setProductImage(productImage);
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
