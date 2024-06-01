package com.example.noloremstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.Cart;
import com.example.noloremstore.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBConfig extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_final";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_NAME = "cart";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_QUANTITY = "quantity";

    public DBConfig(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IMAGE + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_PRICE + " DOUBLE,"
                + COLUMN_QUANTITY + " INTEGER)");
    }

//    public void insertData(final int productId) {
//        // Tembak API ambil produk per id
//        ApiService service = RetrofitClient.getClient().create(ApiService.class);
//        Call<Product> call = service.getProductDetails(productId);
//
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Product product = response.body();
//
//                    SQLiteDatabase db = getWritableDatabase();
//                    ContentValues values = new ContentValues();
//
//                    values.put(COLUMN_IMAGE, product.getImage());
//                    values.put(COLUMN_TITLE, product.getTitle());
//                    values.put(COLUMN_PRICE, product.getPrice());
//                    values.put(COLUMN_QUANTITY, 1); // Set default quantity to 1
//                    db.insert(TABLE_NAME, null, values);
//                    db.close();
//                } else {
//                    Log.e("DBConfig", "Failed to fetch product data from API: " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                Log.e("DBConfig", "API call failed", t);
//            }
//        });
//    }

    public List<Cart> getAllCarts() {
//        SQLiteDatabase db = getReadableDatabase();
//        return db.query(TABLE_NAME, null, null, null, null, null, null);
        List<Cart> cartList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                cart.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                cart.setImage(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)));
                cart.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                cart.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                cartList.add(cart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(db);
    }

//    public boolean addToCart(int productId, String productImage, String productName, double productPrice, int quantity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_ID, productId);
//        values.put(COLUMN_TITLE, productName);
//        values.put(COLUMN_IMAGE, productImage);
//        values.put(COLUMN_PRICE, productPrice);
//        values.put(COLUMN_QUANTITY, quantity);
//
//        long result = db.insert(TABLE_NAME, null, values);
//        db.close();
//        return result != -1;
//    }
    public boolean addToCart(int productId, String productImage, String productName, double productPrice, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, productId);
        values.put(COLUMN_IMAGE, productImage);
        values.put(COLUMN_TITLE, productName);
        values.put(COLUMN_PRICE, productPrice);
        values.put(COLUMN_QUANTITY, quantity);

        long result = db.insert("cart", null, values);
        db.close();
        return result != -1;
    }
}
