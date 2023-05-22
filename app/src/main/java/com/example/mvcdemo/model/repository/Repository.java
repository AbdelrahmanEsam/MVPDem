package com.example.mvcdemo.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;

import com.example.mvcdemo.model.local.ProductsDao;
import com.example.mvcdemo.model.local.ProductsDatabase;
import com.example.mvcdemo.model.dto.ProductsResponse;
import com.example.mvcdemo.model.remote.ApiProvider;
import com.example.mvcdemo.model.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Repository {

    private Context context;
    private LifecycleOwner lifecycleOwner;
    private ProductsDao dao;
    Retrofit remote;
    private MutableLiveData<List<Product>> favouriteProducts ;
    ProductsDatabase db ;
    private static Repository instance = null;

    private Repository(Context context,LifecycleOwner lifecycleOwner)
    {

        this.context = context;
        db  = ProductsDatabase.getInstance(context);
        dao =  db.productDao();
        remote = RetrofitClient.getClient();
        this.lifecycleOwner = lifecycleOwner;
        favouriteProducts = new MutableLiveData<>();
        databaseObserver();



    }




    public static synchronized  Repository getInstance(Context context, LifecycleOwner viewLifecycleOwner) {

        if (instance == null)
        {
            instance = new Repository(context,viewLifecycleOwner);
        }
        return instance;
    }

    public void getAllProducts(NetworkCallback callback)
    {


        remote.create(ApiProvider.class).getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, retrofit2.Response<ProductsResponse> response) {


                if (response.isSuccessful())
                {
                    callback.onResultSuccessCallback(response.body().getProducts());
                }


            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

                callback.onResultFailureCallback(t.getMessage());
            }
        });

    }


    public LiveData<List<Product>> getFavouriteProducts()
    {




        return favouriteProducts;
    }

    private void databaseObserver()
    {
        dao.getAllProducts().observe(lifecycleOwner, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {


                favouriteProducts.postValue(products);
            }
        });
    }


    public void insertProduct(Product product)
    {

        dao.insert(product);

    }

    public void deleteProduct(Product product)
    {

        dao.delete(product);


    }


    public void updateProduct(Product product)
    {
        dao.update(product);
    }






}
