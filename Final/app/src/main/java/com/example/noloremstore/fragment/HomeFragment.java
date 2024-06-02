package com.example.noloremstore.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noloremstore.R;
import com.example.noloremstore.adapter.ProductAdapter;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.Product;
import com.example.noloremstore.model.User;
import com.example.noloremstore.response.ProductResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private TextView tv_no_data, tv_halo;
    private RecyclerView rv_searchProducts, rv_products;
    private ProgressBar progressBar;
    private androidx.appcompat.widget.SearchView searchView;
    private ProductAdapter productAdapter;
    private List<Product> product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tv_no_data = view.findViewById(R.id.tv_no_data);
        tv_halo = view.findViewById(R.id.tv_halo);
        progressBar = view.findViewById(R.id.progressBar);

        rv_searchProducts = view.findViewById(R.id.rv_searchProducts);
        product = new ArrayList<>();
        productAdapter = new ProductAdapter(product, getContext());
        rv_searchProducts.setAdapter(productAdapter);
        rv_searchProducts.setLayoutManager(new GridLayoutManager(getContext(), 1));

        rv_products = view.findViewById(R.id.rv_products);
        rv_products.setLayoutManager(new GridLayoutManager(getContext(), 2));

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.filter(query);
                // Disini bisa dilakukan proses ketika pengguna menekan tombol submit pada keyboard setelah memasukkan query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Disini dilakukan proses pemfilteran berdasarkan teks yang diinputkan oleh pengguna
                productAdapter.filter(newText);
                return true;
            }
        });

        SharedPreferences sharedUsername = getActivity().getSharedPreferences("username_pref", getContext().MODE_PRIVATE);
        String username = sharedUsername.getString("username", null);
        tv_halo.setText("Hello, " + username + "!");

        progressBar.setVisibility(View.VISIBLE);
        rv_products.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                rv_products.setVisibility(View.VISIBLE);
                fetchAllProducts();
            }
        }, 1000); // 2 seconds delay

        return view;
    }

    private void fetchAllProducts() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Product>> call = apiService.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> productList = response.body();
                    productAdapter = new ProductAdapter(productList, getContext());
                    rv_products.setAdapter(productAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                    Log.e("HomeFragment", "Response not successful: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                Log.e("HomeFragment", "API call failed", t);
            }
        });
    }
}