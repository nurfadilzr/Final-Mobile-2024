package com.example.noloremstore.fragment;

import android.content.Intent;
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
import com.example.noloremstore.activity.ProductDetailActivity;
import com.example.noloremstore.adapter.CategoryAdapter;
import com.example.noloremstore.adapter.ProductAdapter;
import com.example.noloremstore.adapter.ProductsByCategoryAdapter;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.Category;
import com.example.noloremstore.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {
    private RecyclerView rv_category, rv_productsCategory;
//    private List<Category> categoryList;
    private List<String> categoryList;
    private List<Product> products;
    private CategoryAdapter categoryAdapter;
    private ProductsByCategoryAdapter productsByCategoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Inisialisasi RecyclerView
        rv_category = view.findViewById(R.id.rv_category);
        rv_productsCategory = view.findViewById(R.id.rv_productsCategory);

        categoryList = new ArrayList<>();
        products = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryList, this::onCategoryClick);
        productsByCategoryAdapter = new ProductsByCategoryAdapter(products);

        rv_category.setAdapter(categoryAdapter);
        rv_category.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_productsCategory.setAdapter(productsByCategoryAdapter);
        rv_productsCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_productsCategory.setVisibility(View.GONE);

        // Tambahkan kategori ke dalam daftar
        fetchCategories();

        return view;
    }

    private void fetchCategories() {
        // Panggil API FakeStoreAPI untuk mendapatkan daftar kategori
        // Implementasi panggilan API menggunakan Retrofit atau metode lainnya
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<String>> call = apiService.getAllCategories();
        call.enqueue(new Callback<List<String>>() {

            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> categories = response.body();
                    categoryList.addAll(categories);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to fetch categories: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onCategoryClick(String category) {
        // Implementasikan aksi ketika kategori dipilih
        // Implementasikan aksi ketika kategori dipilih
//        String selectedCategory = categoryList.get(position);
//        String categoryName = selectedCategory.getName();
//
//        // Contoh: Buka halaman baru untuk menampilkan produk sesuai kategori yang dipilih
//        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//        intent.putExtra("categoryName", categoryName);
//        startActivity(intent);

        fetchProducts(category);
    }

    private void fetchProducts(String category) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Product>> call = apiService.getProductsByCategory(category);
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.clear();
                    products.addAll(response.body());
                    productsByCategoryAdapter.notifyDataSetChanged();
                    rv_productsCategory.setVisibility(View.VISIBLE);
//                    List<Product> productList = response.body();
//                    productsByCategoryAdapter = new ProductsByCategoryAdapter(productList);
//                    rv_productsCategory.setAdapter(productsByCategoryAdapter);
//                    rv_productsCategory.setVisibility(View.VISIBLE);

//                    productsByCategoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                    Log.e("HomeFragment", "Response not successful: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("HomeFragment", "API call failed", t);
            }
        });
//
//        productsByCategoryAdapter = new ProductsByCategoryAdapter(products);
//        rv_productsCategory.setAdapter(productsByCategoryAdapter);


    }
}