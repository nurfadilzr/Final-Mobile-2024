package com.example.noloremstore.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noloremstore.R;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private TextView tvEmail, tvUsername, tvPassword, tvFirstname, tvLastname, tvAddress, tvPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvEmail = view.findViewById(R.id.tv_email);
        tvUsername = view.findViewById(R.id.tv_username);
        tvPassword = view.findViewById(R.id.tv_password);
        tvFirstname = view.findViewById(R.id.tv_firstname);
        tvLastname = view.findViewById(R.id.tv_lastname);
        tvAddress = view.findViewById(R.id.tv_address);
        tvPhone = view.findViewById(R.id.tv_phone);

        // Fetch user data from API
        fetchUserProfile();

        return view;
    }

    private void fetchUserProfile() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null) {
            int userId = getUserIdFromToken(token);
            if (userId != -1) {
                ApiService service = RetrofitClient.getClient().create(ApiService.class);
                Call<User> call = service.getUserData(userId);
                Log.d("UserId", String.valueOf(userId));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            User user = response.body();
                            updateUI(user);
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
            } else {
                Toast.makeText(getContext(), "Failed to decode user ID from token", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Token not found", Toast.LENGTH_SHORT).show();
        }
    }


    private int getUserIdFromToken(String token) {
        System.out.println(token);
        try {
            String[] split = token.split("\\.");
            String payload = new String(Base64.decode(split[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);
            // Ganti "sub" dengan field yang benar jika berbeda
            return jsonObject.getInt("sub");
        } catch (JSONException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void updateUI(User user) {
        tvEmail.setText(user.getEmail());
        tvUsername.setText(user.getUsername());
        tvPassword.setText(user.getPassword()); // Assuming password is already hashed
        tvFirstname.setText(user.getFirstname());
        tvLastname.setText(user.getLastname());
//        tvAddress.setText(user.getAddress());
        tvPhone.setText(user.getPhone());

    }
}