package ru.clevertec.strategy.impl;

import ru.clevertec.model.Transaction;
import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class CryptoPayment implements PaymentStrategy {
    @Override
    public void processPayment(User user, double amount) {
        Transaction transaction = new Transaction("Crypto", amount);
        user.addTransaction(transaction);
        System.out.println("Processed cryptocurrency payment of $"
                + amount
                + " for user: "
                + user.getName());
    }
}
