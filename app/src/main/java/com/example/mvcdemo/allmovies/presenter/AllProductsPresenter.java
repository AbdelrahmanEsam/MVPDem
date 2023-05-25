package com.example.mvcdemo.allmovies.presenter;

import com.example.mvcdemo.allmovies.view.AllProductsViewInterface;
import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;
import com.example.mvcdemo.model.repository.RepositoryInterface;

import java.util.List;

public class AllProductsPresenter implements AllProductsInterface,NetworkCallback {


    private RepositoryInterface repositoryInterface ;
    private AllProductsViewInterface viewInterface;
    public AllProductsPresenter(RepositoryInterface repositoryInterface, AllProductsViewInterface viewInterface)
    {
        this.repositoryInterface = repositoryInterface;
        this.viewInterface = viewInterface;

    }

    @Override
    public void onResultSuccessCallback(List<Product> productsList) {

        viewInterface.onResultSuccessCallback(productsList);

    }

    @Override
    public void onResultFailureCallback(String error) {

        viewInterface.onResultFailureCallback(error);
    }

    @Override
    public void getAllProducts() {
        repositoryInterface.getAllProducts(this);
    }

    @Override
    public void insertProduct(Product product) {
        repositoryInterface.insertProduct(product);
    }
}
