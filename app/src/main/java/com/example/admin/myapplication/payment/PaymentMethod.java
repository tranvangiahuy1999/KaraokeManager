package com.example.admin.myapplication.payment;

public class PaymentMethod {
    private PaymentStrategy strategy;

    public PaymentMethod(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(float amount) {
        strategy.pay(amount);
    }
}
