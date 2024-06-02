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
    private static final int DATABASE_VERSION = 6;
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

    public List<Cart> getAllCarts() {
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

                Log.d("DBConfig", "Retrieved cart item: " + cart.toString());
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

    public boolean addToCart(int productId, String productImage, String productName, double productPrice, int productQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, productId);
        values.put(COLUMN_IMAGE, productImage);
        values.put(COLUMN_TITLE, productName);
        values.put(COLUMN_PRICE, productPrice);
        values.put(COLUMN_QUANTITY, productQuantity);

        Log.d("DBConfig", "Adding to cart: " + values.toString());

        long result = db.insert("cart", null, values);
        db.close();
        return result != -1;
    }
}
