package com.example.noloremstore.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noloremstore.R;
import com.example.noloremstore.adapter.CartAdapter;
import com.example.noloremstore.database.DBConfig;
import com.example.noloremstore.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView rv_cart;
    private CartAdapter cartAdapter;
    private DBConfig dbConfig;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        dbConfig = new DBConfig(getContext());

        rv_cart = view.findViewById(R.id.rv_cart);
        rv_cart.setLayoutManager(new GridLayoutManager(getContext(), 1));

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
}