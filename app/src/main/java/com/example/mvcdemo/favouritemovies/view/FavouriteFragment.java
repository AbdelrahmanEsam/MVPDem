package com.example.mvcdemo.favouritemovies.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvcdemo.databinding.FragmentFavouriteBinding;
import com.example.mvcdemo.favouritemovies.presenter.FavouriteProductsPresenter;
import com.example.mvcdemo.model.dto.Product;
import com.example.mvcdemo.model.repository.Repository;
import com.example.mvcdemo.model.repository.RepositoryInterface;

import java.util.List;


public class FavouriteFragment extends Fragment implements OnDeleteClickListener {

    private FragmentFavouriteBinding binding;
    NavController controller;
    FavouriteAdapter adapter;
    FavouriteProductsPresenter presenter ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        adapter = new FavouriteAdapter();
        presenter = new FavouriteProductsPresenter(Repository.getInstance(getContext(),getViewLifecycleOwner()));
        presenter.getFavouriteData().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setData(products,getContext(),FavouriteFragment.this);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.favouriteRecycler.setLayoutManager(linearLayoutManager);
        binding.favouriteRecycler.setAdapter(adapter);


    }

    @Override
    public void onDeleteClickListener(Product product) {
        presenter.deleteFavouriteProduct(product);
    }



}