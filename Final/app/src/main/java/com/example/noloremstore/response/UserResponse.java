package com.example.noloremstore.response;

import androidx.annotation.NonNull;

import com.example.noloremstore.model.User;

import java.util.List;

public class UserResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
