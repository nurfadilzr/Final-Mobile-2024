package com.example.noloremstore.activity;

import android.os.Bundle;

import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.User;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.noloremstore.response.TokenResponse;
import com.example.noloremstore.response.UserResponse;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.noloremstore.databinding.ActivityLoginBinding;

import com.example.noloremstore.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username, et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        User user = new User(username, password);

        Call<TokenResponse> call = apiService.login(user);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TokenResponse tokenResponse = response.body();
                    String token = tokenResponse.getToken();

                    // Logging untuk memeriksa isi dari tokenResponse
                    Log.d("LoginActivity", "Response body: " + response.body());
                    Log.d("LoginActivity", "Token: " + token);

                    // Misalnya, setelah verifikasi kredensial pengguna dan login berhasil:
//                    SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("isLoggedIn", true);
//                    editor.apply(); // Terapkan perubahan

                    saveToken(token);
                    saveUsername(username);
                    navigateToHome();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void saveToken(String token) {
        SharedPreferences sharedUserLogin = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedUserLogin.edit();
        editor.putString("token", token);
        editor.apply();
    }

    private void saveUsername(String username){
        SharedPreferences sharedUsername = getSharedPreferences("username_pref", MODE_PRIVATE);
        username = et_username.getText().toString().trim();
        SharedPreferences.Editor editor = sharedUsername.edit();
        editor.putString("username", username);
        editor.apply();
    }



    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}