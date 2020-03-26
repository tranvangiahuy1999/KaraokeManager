package com.example.admin.myapplication.objects;

import java.io.Serializable;

public class snacks implements Serializable {
    private int snack_id;
    private String snack_name;
    private String snack_descript;
    private float snack_price;

    public snacks(int snack_id, String snack_name, String snack_descript, float snack_price) {
        this.snack_id = snack_id;
        this.snack_name = snack_name;
        this.snack_descript = snack_descript;
        this.snack_price = snack_price;
    }

    public int getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(int snack_id) {
        this.snack_id = snack_id;
    }

    public String getSnack_name() {
        return snack_name;
    }

    public void setSnack_name(String snack_name) {
        this.snack_name = snack_name;
    }

    public String getSnack_descript() {
        return snack_descript;
    }

    public void setSnack_descript(String snack_descript) {
        this.snack_descript = snack_descript;
    }

    public float getSnack_price() {
        return snack_price;
    }

    public void setSnack_price(float snack_price) {
        this.snack_price = snack_price;
    }
}
