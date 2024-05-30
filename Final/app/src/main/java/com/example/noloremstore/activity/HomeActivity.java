package com.example.noloremstore.activity;

import android.os.Bundle;

import com.example.noloremstore.fragment.CartFragment;
import com.example.noloremstore.fragment.CategoryFragment;
import com.example.noloremstore.fragment.HomeFragment;
import com.example.noloremstore.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.noloremstore.databinding.ActivityHomeBinding;

import com.example.noloremstore.R;

public class HomeActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
    private ImageView iv_store, iv_category, iv_cart, iv_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv_store = findViewById(R.id.iv_store);
        iv_category = findViewById(R.id.iv_category);
        iv_cart = findViewById(R.id.iv_cart);
        iv_profile = findViewById(R.id.iv_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment)
                    .commit();
        }

        iv_store.setOnClickListener(v -> {
            HomeFragment homeFragment1 = new HomeFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment1)
                    .addToBackStack(null)
                    .commit();
        });

        iv_category.setOnClickListener(v -> {
            CategoryFragment categoryFragment = new CategoryFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, categoryFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_cart.setOnClickListener(v -> {
            CartFragment cartFragment = new CartFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, cartFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_profile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    .addToBackStack(null)
                    .commit();
        });

//        BottomNavigationView nav_bottom = findViewById(R.id.nav_view);
//        NavController navController = Navigation.findNavController(this, R.id.nav_view);
//        appBarConfiguration = new AppBarConfiguration
//                .Builder(R.id.homeFragment, R.id.categoryFragment, R.id.cartFragment, R.id.profileFragment)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(nav_bottom, navController);

//        BottomNavigationView navBottom = findViewById(R.id.nav_view);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.homeFragment, R.id.categoryFragment, R.id.cartFragment, R.id.profileFragment)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navBottom, navController);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}