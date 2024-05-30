package com.example.noloremstore.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noloremstore.R;
import com.example.noloremstore.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private List<String> categoryList;
    private OnCategoryClickListener onCategoryClickListener; // Interface untuk mendengarkan klik

    public interface OnCategoryClickListener {
        void onCategoryClicked(String category);
    }
    public CategoryAdapter(List<String> categoryList, OnCategoryClickListener onCategoryClickListener) {
        this.categoryList = categoryList;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public CategoryAdapter(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        String category = categoryList.get(position);
//        holder.categoryName.setText(category);
//
//        // Set listener klik pada ViewHolder
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Panggil metode dari interface saat item diklik
//                if (onCategoryClickListener != null) {
//                    onCategoryClickListener.onCategoryClick(position);
//                }
//            }
//        });

        String category = categoryList.get(position);
        holder.categoryName.setText(category);
        holder.itemView.setOnClickListener(v -> onCategoryClickListener.onCategoryClicked(category));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tv_kategori);
        }
    }

    // Konstruktor untuk adapter

    // ViewHolder untuk RecyclerView
//    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView categoryName;
//
//        public CategoryViewHolder(View itemView) {
//            super(itemView);
//            categoryName = itemView.findViewById(R.id.tv_kategori);
//            itemView.setOnClickListener(this); // Set listener klik pada item
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }

//        @Override
//        public void onClick(View view) {
//            // Panggil metode dari interface saat item diklik
//            if (onCategoryClickListener != null) {
//                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    onCategoryClickListener.onCategoryClicked(category);
//                }
//            }
//        }
//    }

    // Metode lainnya di sini

    // Interface untuk mendengarkan klik pada item kategori
//    public interface OnCategoryClickListener {
//        void onCategoryClick(String category);
//    }

    // Metode untuk menetapkan listener
    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.onCategoryClickListener = listener;
    }
}
