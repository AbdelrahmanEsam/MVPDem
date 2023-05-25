package com.example.mvcdemo.model.repository;

import androidx.lifecycle.LiveData;

import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface RepositoryInterface {
    void getAllProducts(NetworkCallback callback);
    LiveData<List<Product>> getFavouriteProducts();
    void deleteProduct(Product product);
    void insertProduct(Product product);
}
