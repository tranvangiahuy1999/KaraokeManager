package com.example.admin.myapplication.objects;

public class order_snack {

    private int snack_id, quantity;
    private String snack_name;
    private float pay_order;

    public order_snack(int snack_id, String snack_name, int quantity, float pay_order) {
        this.snack_id = snack_id;
        this.quantity = quantity;
        this.snack_name = snack_name;
        this.pay_order = pay_order;
    }

    public int getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(int snack_id) {
        this.snack_id = snack_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSnack_name() {
        return snack_name;
    }

    public void setSnack_name(String snack_name) {
        this.snack_name = snack_name;
    }

    public float getPay_order() {
        return pay_order;
    }

    public void setPay_order(float pay_order) {
        this.pay_order = pay_order;
    }
}
