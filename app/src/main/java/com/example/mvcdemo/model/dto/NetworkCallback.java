package com.example.mvcdemo.model.dto;

import com.example.mvcdemo.model.dto.Product;

import java.util.List;

public interface NetworkCallback {

    public void onResultSuccessCallback(List<Product> productsList);
    public void onResultFailureCallback(String error);
}
