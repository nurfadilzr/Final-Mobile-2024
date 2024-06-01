package com.example.noloremstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.noloremstore.api.ApiService;
import com.example.noloremstore.api.RetrofitClient;
import com.example.noloremstore.model.Product;
import com.example.noloremstore.model.User;

import retrofit2.Call;

public class DBConfig extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_final";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cart";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_QUANTITY = "quantity";
    public DBConfig(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IMAGE + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_PRICE + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER)");
    }

    public void insertData(int id) {
        // tembak api ambil produk per id
        ApiService service = RetrofitClient.getClient().create(ApiService.class);
//        Call<Product> call = service.getProductDetails();

//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_IMAGE, image);
//        values.put(COLUMN_TITLE, title);
//        values.put(COLUMN_PRICE, price);
//        values.put(COLUMN_QUANTITY, quantity);
//        db.insert(TABLE_NAME, null, values);
//        db.close();
    }

    public Cursor getAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void updateRecord(int id, String judul, String deskripsi) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_IMAGE, image);
//        values.put(COLUMN_TITLE, title);
//        values.put(COLUMN_PRICE, price);
//        values.put(COLUMN_QUANTITY, quantity);
//        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteRecords(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
