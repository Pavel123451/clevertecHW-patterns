package ru.clevertec.service;

import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(User user, double amount) {
        strategy.processPayment(user, amount);
    }
}