package ru.clevertec.decorator;

import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public abstract class PaymentDecorator implements PaymentStrategy {
    protected final PaymentStrategy decoratedPayment;

    public PaymentDecorator(PaymentStrategy payment) {
        this.decoratedPayment = payment;
    }
}
