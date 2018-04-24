package com.example.genji.am016_sqlite;

/**
 * Created by genji on 2/9/16.
 */
public class Product {
    private long id;
    private String name;
    private String description;

    public Product(String name, String description){
        // id = 666L;
        this.name = name;
        this.description = description;
    }

    public Product(){
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name;
    }
}
