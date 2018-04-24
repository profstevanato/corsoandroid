package com.example.genji.am101_db;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by genji on 3/26/16.
 *
 * this class connect with JSON from MariaDB via PHP
 */
public class Connector {

    // TAG for Volley
    static final String TAG = "VConnector";

    // json array response url (modify it changing server)
    private static final String myurl = "http://192.168.1.2";
    // the main activity
    private Context context;
    final static JSONArray response = new JSONArray();
    // protocol
    boolean connectionOK, queryOK;

    // constructor

    public Connector(Context context){
        this.context = context;
        connectionOK = queryOK = false;
    }


    // test connection method
    public void testConnection(){
        JsonArrayRequest req = new JsonArrayRequest(myurl + "/connection.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject first = (JSONObject) response
                                    .get(0);
                            if(first.has("connection") && first.getString("connection").matches("nok")){
                                Toast.makeText(context, "connection NOK", Toast.LENGTH_SHORT).show();
                                connectionOK = false;
                            }
                            if(first.has("connection") && first.getString("connection").matches("ok")){
                                Toast.makeText(context, "connection OK", Toast.LENGTH_SHORT).show();
                                connectionOK = true;
                            }
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
    }

    // synchronizing method from external DB
    void downloadAll(){

        // test connection OK
        if(!connectionOK){
            Toast.makeText(context, "test connection", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(myurl + "/select.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject first = (JSONObject) response
                                    .get(0);
                            if(first.has("sql") && first.getString("sql").matches("nok")){
                                queryOK = false;
                                return;
                            }
                            if(response.length()==1){
                                Toast.makeText(context, "no products in DB", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            // Parsing json array response
                            // loop through each json object
                            MainActivity ma = (MainActivity)context;
                            for (int i = 1; i < response.length(); i++) {
                                JSONObject product = (JSONObject) response
                                        .get(i);
                                long id = product.getLong("_id");
                                String name = product.getString("name");
                                String description = product.getString("description");
                                Log.d(TAG, "download: (" + id + ", " + name + ", " + description +")");
                                ma.rawAdd(new Product(id, name, description, 0));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
    }

    void insert(Product product){
        if(!connectionOK){
            Toast.makeText(context, "Try connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = product.getName();
        String description = product.getDescription();
        String urlRequest = myurl + "/insert.php?" +
                "name=" +  Uri.encode(name) + "&"
                + "description=" + Uri.encode(description);
        Log.w(TAG, urlRequest);
        // request
        queryOKrequest(urlRequest);
        if(queryOK) Log.w(TAG, "(" + name +", + " + description +") inserted");
    }

    void update(Product product){
        if(!connectionOK){
            Toast.makeText(context, "Try connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        long id = product.getId();
        String name = product.getName();
        String description = product.getDescription();
        String urlRequest = myurl + "/update.php?" +
                "_id=" + Uri.encode(String.valueOf(id)) + "&" +
                "name=" + Uri.encode(name) + "&"
                + "description=" + Uri.encode(description);
        Log.w(TAG, urlRequest);
        // request
        queryOKrequest(urlRequest);
        if(queryOK) Log.w(TAG, "(" + name +", " + description +") updated");
    }

    void delete(long id){
        if(!connectionOK){
            Toast.makeText(context, "Try connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        String urlRequest = myurl + "/delete.php?" +
                "_id=" + Uri.encode(String.valueOf(id));
        Log.w(TAG, urlRequest);
        // request
        queryOKrequest(urlRequest);
        if(queryOK) Log.w(TAG, "(" + id +") deleted");
    }

    // an helper method
    private void queryOKrequest(String url){
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject first = (JSONObject) response
                                    .get(0);
                            if(first.has("sql") && first.getString("sql").matches("ok")){
                                queryOK = true;
                                return;
                            }
                            if(first.has("sql") && first.getString("sql").matches("nok")){
                                queryOK = false;
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
    }

}