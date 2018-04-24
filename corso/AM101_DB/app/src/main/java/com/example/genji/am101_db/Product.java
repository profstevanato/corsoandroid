package com.example.genji.am101_db;

/**
 * Created by genji on 3/25/16.
 */

// MODEL

public class Product {
    private long id;
    private String name;
    private String description;
    private int updated; // SQLite has not boolean

    public Product(String name, String description){
        // id = 666L;
        this.name = name;
        this.description = description;
        this.updated = 0; // FALSE
    }

    public Product(long id, String name, String description, int updated){
        this.id = id;
        this.name = name;
        this.description = description;
        this.updated = updated;
    }

    // in DB remote id is _id

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public int getUpdated() {
        return this.updated;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name;
    }
}
