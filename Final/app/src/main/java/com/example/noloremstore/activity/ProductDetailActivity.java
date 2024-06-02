package com.example.noloremstore.activity;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.database.DBConfig;
import com.example.noloremstore.fragment.HomeFragment;
import com.example.noloremstore.model.Cart;
import com.example.noloremstore.model.Product;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.noloremstore.databinding.ActivityProductDetailBinding;

import com.example.noloremstore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private int product_id;
    private int quantity = 1;
    private Product product;
    private DBConfig dbConfig;
    private Button btn_add_to_cart;
    private ImageView iv_productImage, btn_back, btn_decrease, btn_increase;
    private TextView tv_productName, tv_productPrice, tv_productCategory, tv_productDescription, tv_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        dbConfig = new DBConfig(this);
        btn_back = findViewById(R.id.btn_back);
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart);
        btn_decrease = findViewById(R.id.btn_decrease_quantity);
        btn_increase = findViewById(R.id.btn_increase_quantity);
        iv_productImage = findViewById(R.id.iv_productImage);
        tv_productName = findViewById(R.id.tv_productName);
        tv_productPrice = findViewById(R.id.tv_productPrice);
        tv_productCategory = findViewById(R.id.tv_productCategory);
        tv_productDescription = findViewById(R.id.tv_productDescription);
        tv_quantity = findViewById(R.id.tv_quantity);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_increase.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(tv_quantity.getText().toString());
            currentQuantity++;
            tv_quantity.setText(String.valueOf(currentQuantity));
            product.setQuantity(currentQuantity); // Update kuantitas di objek Product
        });

        btn_decrease.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(tv_quantity.getText().toString());
            if (currentQuantity > 1) {
                currentQuantity--;
                tv_quantity.setText(String.valueOf(currentQuantity));
                product.setQuantity(currentQuantity); // Update kuantitas di objek Product
            }
        });

        btn_add_to_cart.setOnClickListener(v -> {
            addToCart();
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product_id")) {
            product_id = intent.getIntExtra("product_id", -1);
            fetchProductDetails(product_id);
        }
    }

    private void fetchProductDetails(int productId) {
        ApiService service = RetrofitClient.getClient().create(ApiService.class);
        Call<Product> call = service.getProductDetails(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product = response.body();
                    updateUI(product);
                } else {
                    Log.e("ProductDetailActivity", "Failed to fetch product data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("HomeFragment", "API call failed", t);
            }
        });
    }

    private void updateUI(Product product) {
        tv_productName.setText(product.getTitle());
        tv_productPrice.setText("Price: $" + product.getPrice());
        tv_productCategory.setText("Category: " + product.getCategory());
        tv_productDescription.setText("Description: \n" + product.getDescription());
        Picasso.get().load(product.getImage()).into(iv_productImage);
    }

    private void addToCart() {
        if (product != null) {
            Log.d("ProductDetailActivity", "Adding to cart: " + product.toString());
            boolean isAdded = dbConfig.addToCart(product.getId(), product.getImage(), product.getTitle(), product.getPrice(), product.getQuantity());

            if (isAdded) {
                showAlertDialog("Success", "Product added to cart successfully");
            } else {
                showAlertDialog("Error", "Failed to add product to cart");
            }
        } else {
            Log.e("ProductDetailActivity", "Product is null, cannot add to cart");
        }
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}