package com.example.genji.amrx_002;

/**
 * Created by genji on 2/3/17.
 */

public class Thing {
    int id;
    String name;
    Boolean val;

    public Thing(int id, String name, Boolean val){
        this.id = id;
        this.name = name;
        this.val = val;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getVal() {
        return val;
    }
}
