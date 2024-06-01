package com.example.noloremstore.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
//    private SearchView searchView;
    private androidx.appcompat.widget.SearchView searchView;
    private ProductAdapter productAdapter;
    private List<Product> product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tv_no_data = view.findViewById(R.id.tv_no_data);
        tv_halo = view.findViewById(R.id.tv_halo);
//        tv_halo.setText();

        rv_searchProducts = view.findViewById(R.id.rv_searchProducts);
        product = new ArrayList<>();
        productAdapter = new ProductAdapter(product, getContext());
        rv_searchProducts.setAdapter(productAdapter);
        rv_searchProducts.setLayoutManager(new GridLayoutManager(getContext(), 1));

        rv_products = view.findViewById(R.id.rv_products);
        rv_products.setLayoutManager(new GridLayoutManager(getContext(), 2));

        searchView = view.findViewById(R.id.searchView);
//        setSearchViewHintColor(searchView, Color.GRAY);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.filter(query);
//                fetchProductsByQuery(query);
                // Disini bisa dilakukan proses ketika pengguna menekan tombol submit pada keyboard setelah memasukkan query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Disini dilakukan proses pemfilteran berdasarkan teks yang diinputkan oleh pengguna
                productAdapter.filter(newText);
//                productAdapter.getFilter().filter(newText);
                return true;
            }
        });

        SharedPreferences sharedUsername = getActivity().getSharedPreferences("username_pref", getContext().MODE_PRIVATE);
        String username = sharedUsername.getString("username", null);
        tv_halo.setText("Halo, " + username + "!");


//        fetchUserProfile();
        fetchAllProducts();

        return view;
    }

    private void fetchUserProfile() {
        SharedPreferences sharedUserLogin = getActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        SharedPreferences sharedUsername = getActivity().getSharedPreferences("username_pref", getContext().MODE_PRIVATE);
        String token = sharedUserLogin.getString("token", null);
        if (token != null) {
            int userId = getUserIdFromToken(token);
            if (userId != -1) {
                ApiService service = RetrofitClient.getClient().create(ApiService.class);
                Call<User> call = service.getUserData(userId);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            User user = response.body();
                            Log.d("username", user.getUsername());
                            tv_halo.setText("Halo, " + user.getUsername() + "!");
                        } else {
                            Log.e("ProfileFragment", "Response not successful: " + response.code());
                            Toast.makeText(getContext(), "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("ProfileFragment", "API call failed", t);
                        Toast.makeText(getContext(), "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(getContext(), "Token not found", Toast.LENGTH_SHORT).show();
        }
    }

    private int getUserIdFromToken(String token) {
        try {
            String[] split = token.split("\\.");
            String payload = new String(Base64.decode(split[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);
            return jsonObject.getInt("userId");
        } catch (JSONException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }

//    private void fetchUserProfile() {
//        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
//        Call<User> call = apiService.getUserData(userId);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    User user = response.body();
//                    tv_halo.setText("Halo, " + user.getUsername() + "!");
//                } else {
//                    Toast.makeText(getContext(), "Failed to retrieve user profile", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("HomeFragment", "API call failed", t);
//            }
//        });
//    }

//    private void setSearchViewHintColor(SearchView searchView, int color) {
//        int id = searchView.getContext().getResources().getIdentifier("Cari produk", null, null);
//        TextView hintText = searchView.findViewById(id);
//        hintText.setHintTextColor(color);
//    }

//    private void setSearchViewHintColor(androidx.appcompat.widget.SearchView searchView, int color) {
//        int id = searchView.getContext().getResources().getIdentifier("Cari produk", null, null);
//        TextView hintText = searchView.findViewById(id);
//        hintText.setHintTextColor(color);
//    }

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
                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("HomeFragment", "API call failed", t);
            }
        });
    }

//    private void fetchProductsByQuery(String query) {
//        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
//        Call<List<Product>> call = apiService.getProductsByQuery(query);
//        call.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Product> productList = response.body();
//                    productAdapter.updateProductList(productList);
//                    rv_searchProducts.setVisibility(View.VISIBLE);
//                } else {
//                    Toast.makeText(getContext(), "Failed to retrieve products", Toast.LENGTH_SHORT).show();
//                    Log.e("HomeFragment", "Response not successful: " + response.code() + " - " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e("HomeFragment", "API call failed", t);
//            }
//        });
//    }
}