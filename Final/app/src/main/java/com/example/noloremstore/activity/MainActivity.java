package com.example.noloremstore.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.noloremstore.R;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
//                String token = sharedPreferences.getString("token", null);
//
//                Log.d(TAG, "Token from SharedPreferences: " + token);
//
//                if (token != null) {
//                    // Periksa status login dari API
//                    ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
//                    Call<User> call = apiService.getUserProfile("Bearer " + token);
//                    call.enqueue(new Callback<User>() {
//                        @Override
//                        public void onResponse(Call<User> call, Response<User> response) {
//                            Log.d(TAG, "API Response Code: " + response.code());
//                            Log.d(TAG, "API Response Message: " + response.message());
//
//                            if (response.isSuccessful() && response.body() != null) {
//                                Log.d(TAG, "User is still logged in: " + response.body().getName());
//                                // Pengguna masih login
//                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                                startActivity(intent);
//                            } else {
//                                Log.d(TAG, "User not logged in or response body is null");
//                                // Pengguna tidak login
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                            }
//                            progressBar.setVisibility(View.GONE); // Hide ProgressBar
//                            finish();
//                        }
//
//                        @Override
//                        public void onFailure(Call<User> call, Throwable t) {
//                            t.printStackTrace();
//                            Log.e(TAG, "API call failed: " + t.getMessage());
//                            Toast.makeText(MainActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE); // Hide ProgressBar
//                        }
//                    });
//                } else {
//                    // Tidak ada token, arahkan ke LoginActivity
//                    Log.d(TAG, "No token found, directing to LoginActivity");
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    progressBar.setVisibility(View.GONE); // Hide ProgressBar
//                    finish();
//                }
//            }
//        }, 2000); // Delay 2 detik






        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                    boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

                    if (isLoggedIn) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    progressBar.setVisibility(View.GONE); // Hide ProgressBar
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE); // Hide ProgressBar
                        }
                    });
                }
            }
        }, 2000); // Delay 2 detik
    }
}