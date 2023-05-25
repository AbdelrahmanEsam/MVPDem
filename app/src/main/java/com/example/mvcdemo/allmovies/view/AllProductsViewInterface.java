package com.example.mvcdemo.allmovies.view;

import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface AllProductsViewInterface {

    public void onResultSuccessCallback(List<Product> productsList);
    public void onResultFailureCallback(String error);
}
