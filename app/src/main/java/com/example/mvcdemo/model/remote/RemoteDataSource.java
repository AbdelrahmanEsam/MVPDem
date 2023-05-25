package com.example.mvcdemo.model.remote;

import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface RemoteDataSource {

    public void getAllProducts(NetworkCallback callback);
}
