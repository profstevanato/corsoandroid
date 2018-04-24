package com.example.genji.am031_cursor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // list data
    private List products;
    private ListView myListView;
    private ArrayAdapter myAdapter;
    // DB data
    private final String dbName = "Shop";
    private static SQLiteDatabase sqliteDB = null;

    // this is the table name
    private final String tableName = "Products";

    // this is the names of products
    private String[] names = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the ArrayAdapter for the result list
        products = new ArrayList();
        myListView = (ListView)findViewById(R.id.listView);
        myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, products);
        ArrayList results = new ArrayList();

        // Declare SQLiteDatabase object
        sqliteDB = null;

        try {
            // open or create the sqlite database (a Context method)
            sqliteDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            // execute the query
            sqliteDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (name TEXT);");
            // insert bookTitle Array versions into table created
            names = Products.NAMES;
            for (String name : names) {
                sqliteDB.execSQL("INSERT INTO " + tableName + " Values ('" + name + "');");
            }

            // create Cursor in order to parse our sqlite results
            Cursor cursor = sqliteDB.rawQuery("SELECT name FROM " + tableName, null);
            // if Cursor is contains results
            if (cursor != null) {
                // move cursor to first row
                if (cursor.moveToFirst()) {
                    do {
                        // Get version from Cursor
                        String name = cursor.getString(cursor.getColumnIndex("name"));

                        // add the bookName into the bookTitles ArrayList
                        products.add(name);
                        // move to next row
                    } while (cursor.moveToNext());
                }
            }
            // initiate the listadapter
            myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, products);

            // assign the list adapter
            myListView.setAdapter(myAdapter);

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(), "Error creating the database", Toast.LENGTH_LONG).show();
        } finally {
            if (sqliteDB != null) {
                sqliteDB.execSQL("DELETE FROM " + tableName);
                sqliteDB.close();
            }
        }
    }
}
