package com.example.mvcdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvcdemo.R;
import com.example.mvcdemo.databinding.FragmentMainBinding;
import com.example.mvcdemo.model.repository.Repository;


public class MainFragment extends Fragment {

    NavController controller;
    private FragmentMainBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);




        binding.favouriteButton.setOnClickListener(view1 -> {

            controller.navigate(MainFragmentDirections.actionMainFragmentToFavouriteFragment());

        });


        binding.getAllButton.setOnClickListener(view1 -> {

            controller.navigate(MainFragmentDirections.actionMainFragmentToProductsFragment());

        });

        binding.exit.setOnClickListener(view1 -> {

            requireActivity().finish();

        });




    }
}