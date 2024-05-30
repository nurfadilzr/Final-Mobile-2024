package com.example.noloremstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noloremstore.R;
import com.example.noloremstore.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsByCategoryAdapter extends RecyclerView.Adapter<ProductsByCategoryAdapter.ProductViewHolder> {

    private List<Product> products;

    public ProductsByCategoryAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsByCategoryAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsByCategoryAdapter.ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText("$" + product.getPrice());
        Picasso.get()
                .load(product.getImage()) // URL atau sumber gambar
                .into(holder.image); // ImageView target
    }

    @Override
    public int getItemCount() {
        return products.size();

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_productImage);
            title = itemView.findViewById(R.id.tv_productName);
            price = itemView.findViewById(R.id.tv_productPrice);

        }
    }
}
