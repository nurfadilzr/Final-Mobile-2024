package com.example.noloremstore.api;

import com.example.noloremstore.model.Cart;
import com.example.noloremstore.model.Product;
import com.example.noloremstore.model.User;
import com.example.noloremstore.response.CartResponse;
import com.example.noloremstore.response.TokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    // products yg dicari
    // product yg dipencet
    // products dari salah satu kategori
    // data user yg login

    @POST("auth/login")
    Call<TokenResponse> login(@Body User user);

//    @GET("products")
//    Call<ProductResponse> getAllProducts();

    @GET("products")
    Call<List<Product>> getAllProducts();

//    @GET("products/category/{category}")
//    Call<List<Product>> getProductsByCategory(String category);

    @GET("products/category/{category}")
    Call<List<Product>> getProductsByCategory(@Path("category") String category);

    @GET("products/{id}")
    Call<Product> getProductDetails(@Path("id") int id);


    @GET("products/categories")
//    Call<List<Category>> getAllCategories();
    Call<List<String>> getAllCategories();

//    @GET("products/category/{category}")
//    Call<List<Product>> getProductCategory();

    @GET("carts/user/{id}")
    Call<List<Cart>> getUserCart(@Path("id") int id);

    @GET("users/{id}")
    Call<User> getUserData(@Path("id") int id);

    @GET("auth/login")
    Call<User> getUserLogin();

}
