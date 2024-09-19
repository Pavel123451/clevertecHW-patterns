package ru.clevertec.decorator.impl;

import ru.clevertec.decorator.PaymentDecorator;
import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class FeeDecorator extends PaymentDecorator {
    public FeeDecorator(PaymentStrategy payment) {
        super(payment);
    }

    @Override
    public void processPayment(User user, double amount) {
        double fee = amount * 0.02;
        decoratedPayment.processPayment(user, amount + fee);
        System.out.println("Applied processing fee of $"
                + fee
                + " for user: "
                + user.getName());
    }
}
