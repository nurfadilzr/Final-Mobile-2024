package com.example.noloremstore.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noloremstore.R;
import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;

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
        // Use Retrofit or any other networking library to fetch user details from API
        // Here is an example using pseudo code
//        ApiService service = RetrofitClient.getClient().create(ApiService.class);
//        Call<User> call = service.getUserDetails();
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    User user = response.body();
//                    updateUI(user);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                // Handle failure
//            }
//        });
    }

//    private void updateUI(User user) {
//        tvEmail.setText(user.getEmail());
//        tvUsername.setText(user.getUsername());
//        tvPassword.setText(user.getPassword()); // Assuming password is already hashed
//        tvFirstname.setText(user.getFirstname());
//        tvLastname.setText(user.getLastname());
//        tvAddress.setText(user.getAddress());
//        tvPhone.setText(user.getPhone());
//
//    }
}