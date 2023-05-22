package com.example.mvcdemo.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvcdemo.model.dto.Product;


@Database(entities = {Product.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {
    public abstract ProductsDao productDao();

    private static ProductsDatabase instance;

    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ProductsDatabase.class, "products_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}