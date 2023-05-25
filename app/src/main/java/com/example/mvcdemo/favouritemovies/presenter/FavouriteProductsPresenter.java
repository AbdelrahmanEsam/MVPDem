package com.example.mvcdemo.favouritemovies.presenter;

import androidx.lifecycle.LiveData;

import com.example.mvcdemo.favouritemovies.view.FavouriteViewInterface;
import com.example.mvcdemo.model.dto.Product;
import com.example.mvcdemo.model.repository.RepositoryInterface;

import java.util.List;

public class FavouriteProductsPresenter implements FavouriteProductsInterface {

    private RepositoryInterface repository;


    public FavouriteProductsPresenter(RepositoryInterface repository)
    {


        this.repository = repository;


    }

    public LiveData<List<Product>> getFavouriteData()
    {
        return repository.getFavouriteProducts();
    }




    @Override
    public void deleteFavouriteProduct(Product product) {
        repository.deleteProduct(product);
    }
}
