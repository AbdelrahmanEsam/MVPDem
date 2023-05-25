package com.example.mvcdemo.model.remote;

import android.util.Log;

import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class RemoteDataSourceImpl implements RemoteDataSource{

    private static RemoteDataSourceImpl remote = null;
    private static  Retrofit retrofit = null ;

    public static synchronized RemoteDataSourceImpl getClient() {

        if (remote == null)
        {
            remote = new RemoteDataSourceImpl();
           retrofit =  new Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return remote;
    }


    @Override
    public void getAllProducts(NetworkCallback callback) {
        retrofit.create(ApiProvider.class).getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, retrofit2.Response<ProductsResponse> response) {


                if (response.isSuccessful())
                {

                    callback.onResultSuccessCallback(response.body().getProducts());
                }


            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.d("responseYes",t.getMessage());

                callback.onResultFailureCallback(t.getMessage());
            }
        });

    }
}

