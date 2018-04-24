package com.example.genji.am101_db;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProductAdapter pAdapter;

    // CRUD list for remote DB
    List<Product> productsInserted;
    List<Product> productsUpdated;
    List<Long> productsDeleted; // id product deleted



    // manager for remote actions
    private Connector mConnector;

    // fragment manager fro dialgs
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fragment manager for dialogs
        fm = getFragmentManager();

        // create a connector
        mConnector = new Connector(this);

        // as in android developers
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // add an offline set of products
        // products = MyData.createList();
        productsInserted = new ArrayList<>();
        productsUpdated = new ArrayList<>();
        productsDeleted = new ArrayList<>();
        // adapter and his listeners
        pAdapter = new ProductAdapter( MyData.createList());
        pAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = pAdapter.getProduct(position).getName();
                Toast.makeText(MainActivity.this, "#" + position + " - " + name, Toast.LENGTH_SHORT).show();
                MainActivity.this.openUpdateDialog(name, position);
            }
        });
        // recycler view
        mRecyclerView.setAdapter(pAdapter);

        // FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Snackbar.make(view, "adding a product", Snackbar.LENGTH_LONG).show();
                       // .setAction("Action", null).show();
                MainActivity.this.openInsertDialog();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.setDrawerListener(toggle); (deprecated)
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.drawer_connection:
                testConnection();
                break;
            case R.id.drawer_download:
                downloadAll();
                break;
            case R.id.drawer_upload:
                uploadAll();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // ********************* MY ADDED METHODS *******************

    public ProductAdapter getAdapter(){return pAdapter;}

    public void add(Product product){
        productsInserted.add(product);
        pAdapter.add(product, pAdapter.getItemCount());
    }

    // used only by connector in first download
    public void rawAdd(Product product){
        pAdapter.add(product, pAdapter.getItemCount());
    }

    public void update(int position, String description){
        Product p = pAdapter.getProduct(position);
        if (!productsUpdated.contains(p)) productsUpdated.add(p);
        pAdapter.update(position, description);
    }

    public void delete(int position){
        Product p = pAdapter.getProduct(position);
        pAdapter.remove(position);
        long id = p.getId();
        if(productsUpdated.contains(p)) productsUpdated.remove(p);
        productsDeleted.add(id);
    }

    public void openInsertDialog(){
        // Create an instance of the dialog fragment and show it
        InsertDialog dialog = new InsertDialog();
        dialog.show(fm, "Insert");
    }

    public void openUpdateDialog(String name, int position){
        // Create an instance of the dialog fragment and show it;

        UpdateDialog dialog = new UpdateDialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(fm, "");
    }

    // Connector methods

    public void testConnection(){
        mConnector.testConnection();
    }

    public void downloadAll(){
        productsUpdated.clear();
        productsInserted.clear();
        productsDeleted.clear();
        // remove the local products
        int size = pAdapter.getItemCount();
        for(int i = 0; i < size; i++) pAdapter.remove(0);
        // download from DB
        mConnector.downloadAll();
    }

    public void uploadAll(){
        // CRUD
        for(Product p : productsInserted) mConnector.insert(p);
        productsInserted.clear();
        Toast.makeText(this, "products inserted in remote db", Toast.LENGTH_SHORT).show();
        for(Product p : productsUpdated) mConnector.update(p);
        productsUpdated.clear();
        Toast.makeText(this, "products updated in remote db", Toast.LENGTH_SHORT).show();
        for(long id : productsDeleted) mConnector.delete(id);
        productsDeleted.clear();
        Toast.makeText(this, "products deleted in remote db", Toast.LENGTH_SHORT).show();
    }
}
