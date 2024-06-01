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
import com.example.noloremstore.model.CartProduct;
import com.example.noloremstore.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private List<Product> productList;
    private Context context;

    public CartAdapter(List<Cart> cartList, List<Product> productList, Context context) {
        this.cartList = cartList;
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
//        Cart cart = cartList.get(position);
//        holder.title.setText(product.getTitle());
//        holder.price.setText("$" + product.getPrice());
//        holder.quantity.setText(cart.getQuantity());
//        holder.date.setText(cart.getDate());
//        Picasso.get()
//                .load(product.getImage()) // URL atau sumber gambar
//                .into(holder.image); // ImageView target

//        Cart cart = cartList.get(position);
//        if (cart.getProduct() != null) {
//            holder.title.setText(cart.getProduct().getTitle());
//            holder.price.setText("$" + cart.getProduct().getPrice());
//            Picasso.get()
//                    .load(cart
//                    .getProduct()
//                            .getImage())
//                    .into(holder.image);
//        }
//        holder.quantity.setText(String.valueOf(cart.getQuantity()));
//        holder.date.setText(cart.getDate());

//        Cart cart = cartList.get(position);
//        CartProduct cartProduct = cart.getProducts().get(0); // Assuming 1 product per cart item for simplicity
//
//        Product product = getProductById(cartProduct.getProductId());
//        if (product != null) {
//            holder.title.setText(product.getTitle());
//            holder.price.setText("Price: " + product.getPrice());
//            holder.quantity.setText("Quantity: " + cartProduct.getQuantity());
//            holder.date.setText("Date: " + cart.getDate());

//            Glide.with(context)
//                    .load(product.getImage())
//                    .into(holder.ivProductImage);

//            Picasso.get()
//                    .load(product.getImage())
//                    .into(holder.image);
//        }
//    }

//    public getProductByCart(){
//
//    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    private Product getProductById(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, price, quantity, date;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_productImage);
            title = itemView.findViewById(R.id.tv_productName);
            price = itemView.findViewById(R.id.tv_productPrice);
            quantity = itemView.findViewById(R.id.tv_productQuantity);
            date = itemView.findViewById(R.id.tv_cartDate);
        }
    }
}
