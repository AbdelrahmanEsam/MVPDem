package com.example.mvcdemo.model.local;

import androidx.lifecycle.LiveData;

import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface LocalDataSource {

    public void insertProduct(Product product);
    public  void deleteProduct(Product product);
    public void  updateProduct(Product product);
    public LiveData<List<Product>> getFavouriteProducts();
}
