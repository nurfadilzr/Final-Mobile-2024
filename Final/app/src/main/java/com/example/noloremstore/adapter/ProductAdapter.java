package com.example.noloremstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noloremstore.R;
import com.example.noloremstore.activity.ProductDetailActivity;
import com.example.noloremstore.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> originalproductList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.originalproductList = new ArrayList<>(productList);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.title.setEllipsize(TextUtils.TruncateAt.END);
        holder.title.setSingleLine(true);

        holder.price.setText("$" + product.getPrice());
        Picasso.get()
                .load(product.getImage()) // URL atau sumber gambar
                .into(holder.image); // ImageView target

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        productList.clear();
        if(text.isEmpty()){
            productList.addAll(originalproductList);
        } else{
            text = text.toLowerCase();
            for(Product product: originalproductList){
                if(product.getTitle().toLowerCase().contains(text)){
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
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
