package com.example.meenabazardemo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class MennaSub {

    @SerializedName("catalog_contents")
    private CatalogContents mCatalogContents;
    @SerializedName("catalog_products")
    private List<CatalogProduct> mCatalogProducts;
    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("subCategories")
    private List<Object> mSubCategories;

    public CatalogContents getCatalogContents() {
        return mCatalogContents;
    }

    public void setCatalogContents(CatalogContents catalogContents) {
        mCatalogContents = catalogContents;
    }

    public List<CatalogProduct> getCatalogProducts() {
        return mCatalogProducts;
    }

    public void setCatalogProducts(List<CatalogProduct> catalogProducts) {
        mCatalogProducts = catalogProducts;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

    public List<Object> getSubCategories() {
        return mSubCategories;
    }

    public void setSubCategories(List<Object> subCategories) {
        mSubCategories = subCategories;
    }

}
