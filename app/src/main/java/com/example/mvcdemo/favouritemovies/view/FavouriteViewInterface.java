package com.example.mvcdemo.favouritemovies.view;

import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface FavouriteViewInterface {
    public  void getFavouriteProductsCallback(List<Product> products);
}
