package com.example.mvcdemo.allmovies.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvcdemo.allmovies.presenter.AllProductsPresenter;
import com.example.mvcdemo.allmovies.view.OnFavoriteClickListener;
import com.example.mvcdemo.allmovies.view.ProductsAdapter;
import com.example.mvcdemo.databinding.FragmentProductsBinding;
import com.example.mvcdemo.model.dto.NetworkCallback;
import com.example.mvcdemo.model.dto.Product;
import com.example.mvcdemo.model.repository.Repository;
import com.example.mvcdemo.model.repository.RepositoryInterface;

import java.util.List;


public class ProductsFragment extends Fragment implements  AllProductsViewInterface,OnFavoriteClickListener {

    private FragmentProductsBinding binding;
    ProductsAdapter adapter;
    AllProductsPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AllProductsPresenter(Repository.getInstance(getContext(),getViewLifecycleOwner()),this);
        presenter.getAllProducts();
        adapter  = new ProductsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(adapter);


    }

    @Override
    public void onResultSuccessCallback(List<Product> productsList) {

        adapter.setData(productsList,getContext(),this);
        Log.d("callbackYes","success");
    }

    @Override
    public void onResultFailureCallback(String error) {

        Log.d("callbackError",error);
    }

    @Override
    public void onFavoriteClickListener(Product product) {
        presenter.insertProduct(product);
        Toast.makeText(getContext(),"product is inserted",Toast.LENGTH_LONG).show();
    }
}