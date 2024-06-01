package com.example.noloremstore.model;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
//    private String name; // firstname + lastnama
    private String phone;
//    "address": {
//        "geolocation": {
//            "lat": "-37.3159",
//                    "long": "81.1496"
//        },
//        "city": "kilcoole",
//                "street": "new road",
//                "number": 7682,
//                "zipcode": "12926-3874"
//    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
