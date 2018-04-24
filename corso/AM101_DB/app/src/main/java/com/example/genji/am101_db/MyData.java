package com.example.genji.am101_db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 3/26/16.
 *
 * this class is only for first populating list
 */

public class MyData {

    public static List<Product> createList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "gioppini", " panetti sfiziosi", 0));
        products.add(new Product(2, "frollini plus", "frollini gustosi", 0));
        products.add(new Product(3, "secchini", "biscotti secchi", 0));
        products.add(new Product(4, "majo chips", "patatine alla maionese", 0));
        products.add(new Product(5, "ciocchini", "frollini con gocciole di cioccolato fondente", 0));
        products.add(new Product(6, "merovingi", "biscoti francesi con granella di zucchero", 0));
        return products;
    }
}
