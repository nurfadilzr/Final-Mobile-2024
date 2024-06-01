package com.example.noloremstore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.noloremstore.R;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

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