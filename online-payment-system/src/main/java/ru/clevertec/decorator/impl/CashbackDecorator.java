package ru.clevertec.decorator.impl;

import ru.clevertec.decorator.PaymentDecorator;
import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class CashbackDecorator extends PaymentDecorator {
    public CashbackDecorator(PaymentStrategy payment) {
        super(payment);
    }

    @Override
    public void processPayment(User user, double amount) {
        decoratedPayment.processPayment(user, amount);
        applyCashback(user, amount);
    }

    private void applyCashback(User user, double amount) {
        double cashback = amount * 0.05;
        user.addCashback(cashback);
        System.out.println("Applied cashback of $"
                + cashback
                + " for user: "
                + user.getName());
    }
}

