package com.example.admin.myapplication.model;

public class payment {
    int customer_id,book_id;
    String time_create, payment_id;
    float payment_total;

    public payment(String payment_id, int customer_id, int book_id, String time_create, float payment_total) {
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.book_id = book_id;
        this.time_create = time_create;
        this.payment_total = payment_total;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTime_create() {
        return time_create;
    }

    public void setTime_create(String time_create) {
        this.time_create = time_create;
    }

    public float getPayment_total() {
        return payment_total;
    }

    public void setPayment_total(float payment_total) {
        this.payment_total = payment_total;
    }
}
