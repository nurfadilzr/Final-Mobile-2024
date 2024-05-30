package com.example.noloremstore.activity;

import android.os.Bundle;

import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.noloremstore.databinding.ActivityProductDetailBinding;

import com.example.noloremstore.R;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView iv_productImage, btn_back;
    private TextView tv_productName, tv_productPrice, tv_productCategory, tv_productRating, tv_productDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        btn_back = findViewById(R.id.btn_back);
        iv_productImage = findViewById(R.id.iv_productImage);
        tv_productName = findViewById(R.id.tv_productName);
        tv_productPrice = findViewById(R.id.tv_productPrice);
        tv_productCategory = findViewById(R.id.tv_productCategory);
        tv_productRating = findViewById(R.id.tv_productRating);
        tv_productDescription = findViewById(R.id.tv_productDescription);

        // Assume that you pass product data through Intent
        String productId = getIntent().getStringExtra("product_id");

        // Fetch product data from API
        fetchProductDetails(productId);

    }

    private void fetchProductDetails(String productId) {
        // Use Retrofit or any other networking library to fetch product details from API
        // Here is an example using pseudo code
        ApiService service = RetrofitClient.getClient().create(ApiService.class);
//        Call<Product> call = service.getProductDetails(productId);
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Product product = response.body();
//                    updateUI(product);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                // Handle failure
//            }
//        });
    }

//    private void updateUI(Product product) {
//        tvProductName.setText(product.getName());
//        tvProductPrice.setText("Price: " + product.getPrice());
//        tvProductCategory.setText("Category: " + product.getCategory());
//        tvProductRating.setText("Rating: " + product.getRating());
//        tvProductDescription.setText(product.getDescription());
//
//        Picasso.get().load(product.getImageUrl()).into(ivProductImage);
//    }
}