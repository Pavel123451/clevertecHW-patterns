package ru.clevertec.strategy;

import ru.clevertec.model.User;

public interface PaymentStrategy {
    void processPayment(User user, double amount);
}
