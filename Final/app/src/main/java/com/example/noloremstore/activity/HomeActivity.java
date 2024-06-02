package com.example.noloremstore.activity;

import android.os.Bundle;

import com.example.noloremstore.fragment.CartFragment;
import com.example.noloremstore.fragment.CategoryFragment;
import com.example.noloremstore.fragment.HomeFragment;
import com.example.noloremstore.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.noloremstore.databinding.ActivityHomeBinding;

import com.example.noloremstore.R;

public class HomeActivity extends AppCompatActivity {
    private FrameLayout frame_container;
    private BottomNavigationView bottom_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frame_container = findViewById(R.id.frame_container);
        bottom_nav = findViewById(R.id.bottom_nav);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == R.id.homeFragment){
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.categoryFragment) {
                    loadFragment(new CategoryFragment(), false);
                } else if (itemId == R.id.cartFragment) {
                    loadFragment(new CartFragment(), false);
                } else {
                    loadFragment(new ProfileFragment(), false);
                }

                return true;
            }
        });

        loadFragment(new HomeFragment(), true);
    }

    private void loadFragment(Fragment fragment, boolean b) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (b) {
            fragmentTransaction.add(R.id.frame_container, fragment);
        } else {
            fragmentTransaction.replace(R.id.frame_container, fragment);
        }

        fragmentTransaction.commit();
    }
}