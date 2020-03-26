package com.example.admin.myapplication.objects;

public class room_type {
    int type_id;
    String type_name;
    float type_price;

    public room_type(int type_id, String type_name, float type_price) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.type_price = type_price;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public float getType_price() {
        return type_price;
    }

    public void setType_price(float type_price) {
        this.type_price = type_price;
    }
}
