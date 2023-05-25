package com.example.mvcdemo.model.local;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public class LocalDataSourceImpl implements  LocalDataSource{

    private ProductsDao dao;
     private  ProductsDatabase db ;
     private static  LocalDataSourceImpl instance =null;
    private MutableLiveData<List<Product>> favouriteProducts ;
    private LifecycleOwner lifecycleOwner;

     private LocalDataSourceImpl(Context context, LifecycleOwner viewLifecycleOwner)
     {
         db  = ProductsDatabase.getInstance(context);
         dao =  db.productDao();
         this.lifecycleOwner = viewLifecycleOwner;
         favouriteProducts = new MutableLiveData<>();
         databaseObserver();
     }


    private void databaseObserver()
    {
        dao.getAllProducts().observeForever(new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {


                favouriteProducts.postValue(products);
            }
        });
    }



    public static synchronized LocalDataSourceImpl getInstance(Context context,LifecycleOwner viewLifecycleOwner) {

        if (instance == null)
        {
            instance = new LocalDataSourceImpl(context,viewLifecycleOwner);
        }
        return instance;
    }


    @Override
    public void insertProduct(Product product) {
        dao.insert(product);
    }

    @Override
    public void deleteProduct(Product product) {
        dao.delete(product);
    }

    @Override
    public void updateProduct(Product product) {
         dao.update(product);
    }

    @Override
    public LiveData<List<Product>> getFavouriteProducts() {
        return favouriteProducts;
    }



}
