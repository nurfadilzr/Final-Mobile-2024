package com.example.noloremstore.fragment;

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
    private List<Cart> cartList;
    private List<Product> productList;
    private CartAdapter cartAdapter;
    private List<CartProduct> cartProducts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rv_cart = view.findViewById(R.id.rv_cart);
        rv_cart.setLayoutManager(new GridLayoutManager(getContext(), 1));

        cartList = new ArrayList<>();
        productList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList, productList, getContext());
        rv_cart.setAdapter(cartAdapter);

        fetchCartData();

        return view;
    }

    private void fetchCartData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Cart>> call = apiService.getUserCart(1);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cartList = response.body();
//                    cartAdapter = new CartAdapter(cartList);
//                    rv_cart.setAdapter(cartAdapter);
                    fetchProductData(cartList);

//                    cartProducts = new CartProduct()

                } else {
                    Toast.makeText(getContext(), "Failed to retrieve cart data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchProductData(List<Cart> carts) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<List<Product>> call = apiService.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    cartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve product data", Toast.LENGTH_SHORT).show();
                }
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
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}