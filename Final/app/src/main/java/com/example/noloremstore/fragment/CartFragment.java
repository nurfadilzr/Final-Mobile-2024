package com.example.noloremstore.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.noloremstore.R;
import com.example.noloremstore.adapter.CartAdapter;
import com.example.noloremstore.adapter.ProductAdapter;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.database.DBConfig;
import com.example.noloremstore.model.Cart;
import com.example.noloremstore.model.CartProduct;
import com.example.noloremstore.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private RecyclerView rv_cart;
//    private List<Cart> cartList;
//    private List<Product> productList;
    private CartAdapter cartAdapter;
    private DBConfig dbConfig;
//    private List<CartProduct> cartProducts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        dbConfig = new DBConfig(getContext());

        rv_cart = view.findViewById(R.id.rv_cart);
        rv_cart.setLayoutManager(new GridLayoutManager(getContext(), 1));

//        cartList = new ArrayList<>();
//        productList = new ArrayList<>();
//        cartAdapter = new CartAdapter(cartList, getContext());


        loadCartData();

        return view;
    }

    private void loadCartData() {
        List<Cart> cartList = dbConfig.getAllCarts();
        if (cartList == null) {
            cartList = new ArrayList<>(); // Inisialisasi dengan daftar kosong jika null
        }
        cartAdapter = new CartAdapter(cartList, getContext());
        rv_cart.setAdapter(cartAdapter);
    }
//    private void loadCartData() {
//        cartList = new ArrayList<>();
////        productList = new ArrayList<>();
//
//        SQLiteDatabase db = dbConfig.getReadableDatabase();
//        Cursor cursor = db.query("cart", null, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
//            String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
//            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
//            double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
//            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
//
//            Cart cart = new Cart(id, title, price, quantity, image);
////            Product product = new Product(id, title, description, price, category, image, quantity); // Adjust the constructor as per your model
//
//            cartList.add(cart);
////            productList.add(product);
//        }
//        cursor.close();
//    }



//    private void fetchProductData(List<Cart> carts) {
//        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
//
//        Call<List<Product>> call = apiService.getAllProducts();
//        call.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    productList = response.body();
//                    cartAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getContext(), "Failed to retrieve product data", Toast.LENGTH_SHORT).show();
//                }
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Product> products = response.body();
//                    for (Cart cart : carts) {
//                        for (Product product : products) {
//                            if (cart.get() == product.getId()) {
//                                cart.setProduct(product);
//                                break;
//                            }
//                        }
//                    }
//                    cartList.clear();
//                    cartList.addAll(carts);
////                    cartAdapter = new CartAdapter(cartList);
////                    rv_cart.setAdapter(cartAdapter);
//                    cartAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getContext(), "Failed to retrieve product data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}