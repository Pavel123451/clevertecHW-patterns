package ru.clevertec.strategy.impl;

import ru.clevertec.model.Transaction;
import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(User user, double amount) {
        Transaction transaction = new Transaction("PayPal", amount);
        user.addTransaction(transaction);
        System.out.println("Processed PayPal payment of $"
                + amount
                + " for user: "
                + user.getName());
    }
}
