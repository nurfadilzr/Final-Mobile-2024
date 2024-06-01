package com.example.noloremstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
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

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> originalproductList;
    private List<Product> productListFiltered; // Daftar produk yang telah difilter
    private Context context;

//    public ProductAdapter(List<Product> productList) {
//        this.productList = productList;
//        this.productListFiltered = productListFiltered;
//        this.context = context;
//    }

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
//        holder.rating.setText((int) product.getRating());
        Picasso.get()
                .load(product.getImage()) // URL atau sumber gambar
                .into(holder.image); // ImageView target
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

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String charString = constraint.toString().toLowerCase().trim();
//                if (charString.isEmpty()) {
//                    productListFiltered = productList; // Jika tidak ada kueri, tampilkan semua produk
//                } else {
//                    List<Product> filteredList = new ArrayList<>();
//                    for (Product product : productList) {
//                        // Filter berdasarkan kriteria yang diinginkan, misalnya nama produk
//                        if (product.getName().toLowerCase().contains(charString)) {
//                            filteredList.add(product);
//                        }
//                    }
//                    productListFiltered = filteredList; // Mengupdate daftar produk yang difilter
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = productListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                productListFiltered = (List<Product>) results.values;
//                notifyDataSetChanged(); // Memperbarui tampilan RecyclerView dengan daftar produk yang difilter
//            }
//        };
//    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price, rating;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_productImage);
            title = itemView.findViewById(R.id.tv_productName);
            price = itemView.findViewById(R.id.tv_productPrice);
//            rating = itemView.findViewById(R.id.tv_productRate);
        }
    }
}
