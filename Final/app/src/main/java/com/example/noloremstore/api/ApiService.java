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
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/login")
    Call<TokenResponse> login(@Body User user);


    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("products/category/{category}")
    Call<List<Product>> getProductsByCategory(@Path("category") String category);

    @GET("products/{id}")
    Call<Product> getProductDetails(@Path("id") int id);

    @GET("products/categories")
    Call<List<String>> getAllCategories();


    @GET("users/{id}")
    Call<User> getUserData(@Path("id") int id);
//    @GET("user/{id}")
//    Call<User> getUserProfile(@Header("Authorization") String token);

}
