package com.example.mvcdemo.model.remote;




import com.example.mvcdemo.model.dto.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiProvider {

    @GET("product")
    Call<ProductsResponse> getProducts();

}
