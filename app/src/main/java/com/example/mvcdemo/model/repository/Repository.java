package com.example.mvcdemo.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvcdemo.favouritemovies.presenter.FavouriteProductsInterface;
import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;

import com.example.mvcdemo.model.local.LocalDataSource;
import com.example.mvcdemo.model.dto.ProductsResponse;
import com.example.mvcdemo.model.local.LocalDataSourceImpl;
import com.example.mvcdemo.model.remote.ApiProvider;
import com.example.mvcdemo.model.remote.RemoteDataSourceImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Repository implements RepositoryInterface{

    private Context context;


    RemoteDataSourceImpl remote;
    LocalDataSource local ;
    private MutableLiveData<List<Product>> favouriteProducts ;
    private LifecycleOwner lifecycleOwner;
    private static Repository instance = null;

    private Repository(Context context,LifecycleOwner lifecycleOwner)
    {

        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        local = LocalDataSourceImpl.getInstance(context,lifecycleOwner);
        remote = RemoteDataSourceImpl.getClient();
        favouriteProducts = new MutableLiveData<>();
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


        remote.getAllProducts(callback);

    }


    public LiveData<List<Product>> getFavouriteProducts()
    {
        return local.getFavouriteProducts();
    }





    public void deleteProduct(Product product) {
        local.deleteProduct(product);
    }

    public void insertProduct(Product product) {
        local.insertProduct(product);
    }
}
