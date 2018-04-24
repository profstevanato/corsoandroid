package com.example.genji.am101_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 3/25/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TAG = "Helper";

    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_UPDATED = "updated";

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    // DB creation SQL
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_PRODUCTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_UPDATED + " INTEGER DEFAULT 0, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL);";

    // factory = null	to use for creating cursor objects
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    // CRUD OPERATION

    // Adding new product
    public void addProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        // a transaction is not necessary ...
        // db.beginTransaction();
        try{
            String insertQuery = "INSERT INTO " + TABLE_PRODUCTS + "(" + COLUMN_NAME + ", "
                                                + COLUMN_DESCRIPTION + ", " + COLUMN_UPDATED + ") " +
                                 "VALUES (\"" + product.getName() + "\", \"" + product.getDescription() + "\", " +
                                 String.valueOf(product.getUpdated()) + ");";
            // Inserting Row with atoincrement
            db.execSQL(insertQuery);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add product to database");
        } finally {
            db.endTransaction();
        }
        db.close();
    }

    public void addProducts(ArrayList<Product> products) {

        SQLiteDatabase db = this.getWritableDatabase();

        // a transaction is not necessary ...
        db.beginTransaction();
        for(Product product : products){
            try{
                String insertQuery = "INSERT INTO " + TABLE_PRODUCTS + "(" + COLUMN_NAME + ", "
                        + COLUMN_DESCRIPTION + ", " + COLUMN_UPDATED + ") " +
                        "VALUES (\"" + product.getName() + "\", \"" + product.getDescription() + "\", " +
                        String.valueOf(product.getUpdated()) + ");";
                // Inserting Row with atoincrement
                db.execSQL(insertQuery);
            } catch (Exception e) {
                Log.d(TAG, "Error while trying to add product to database");
            }
        }
        db.endTransaction();
        db.close();
    }



    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        Product product = null;
        try{
            cursor = db.query(TABLE_PRODUCTS, new String[]{COLUMN_ID,
                            COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_UPDATED}, COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get a product from database");
        }
        if(cursor != null){
            product = new Product(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        }
        // return product
        return product;
    }

    // Getting All Contacts
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get all products from database");
        }
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                // Adding product to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        // return contact list
        return productList;
    }

    // Getting products Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(countQuery, null);
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get count products from database");
        }
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_UPDATED, product.getUpdated());


        // updating row
        return db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }

    // Deleting single product
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }

    // Deleting all products
    public void deleteAllProducts() {
        String deleteQuery = "DELETE  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(deleteQuery, null);
        cursor.close();
        db.close();
    }
    
}
