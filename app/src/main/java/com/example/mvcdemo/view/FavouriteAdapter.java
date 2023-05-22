package com.example.mvcdemo.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.example.mvcdemo.R;
import com.example.mvcdemo.controller.OnDeleteClickListener;
import com.example.mvcdemo.controller.OnFavoriteClickListener;
import com.example.mvcdemo.model.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();
    private Context context;
    private OnDeleteClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = products.get(position);
        holder.title.setText(product.getTitle());
     holder.price.setText(product.getPrice()+"  $");
     holder.brand.setText(product.getBrand());
       holder.desc.setText(product.getDescription());

        Log.d("onBindViewHolder",products.get(position).getDescription());
       /* Glide.with(context)
                .load(product.getImages().get(0))
                .override(300, 200).downsample(DownsampleStrategy.CENTER_INSIDE)
                .into(holder.image);*/
        holder.deleteButton.setOnClickListener(view -> {

            listener.onDeleteClickListener(product);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public void setData(List<Product> products, Context context, OnDeleteClickListener listener)
    {

        this.listener = listener ;
        this.products = products;
        this.context = context;
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price ;
        TextView brand ;
        TextView desc ;

        ImageView image;
        View layout ;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout = itemView;
            deleteButton = layout.findViewById(R.id.deleteButton);
            title = layout.findViewById(R.id.titleTextView);
            price = layout.findViewById(R.id.priceTextView);
            brand = layout.findViewById(R.id.brandTextView);
            desc = layout.findViewById(R.id.descTextView);
            image = layout.findViewById(R.id.product_image);



        }
    }
}
