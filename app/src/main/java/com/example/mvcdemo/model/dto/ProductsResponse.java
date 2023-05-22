package com.example.mvcdemo.model.dto;



import java.util.List;

public class ProductsResponse {
    private int total;
    private int skip;
    private int limit;

    private List<Product> products;

    public ProductsResponse(int total, int skip, int limit, List<Product> products) {
        this.total = total;
        this.skip = skip;
        this.limit = limit;
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
