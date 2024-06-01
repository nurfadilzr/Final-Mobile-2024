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
import com.example.noloremstore.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private Context context;

    public CartAdapter(List<Cart> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.title.setText(cart.getTitle());
        holder.price.setText("Price: " + cart.getPrice());
        holder.quantity.setText("Quantity: " + cart.getQuantity());
        Picasso.get()
                .load(cart.getImage()) // URL atau sumber gambar
                .into(holder.image); // ImageView target
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

//    private Product getProductById(int productId) {
//        for (Product product : productList) {
//            if (product.getId() == productId) {
//                return product;
//            }
//        }
//        return null;
//    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, price, quantity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_productImage);
            title = itemView.findViewById(R.id.tv_productName);
            price = itemView.findViewById(R.id.tv_productPrice);
            quantity = itemView.findViewById(R.id.tv_productQuantity);
        }
    }
}
