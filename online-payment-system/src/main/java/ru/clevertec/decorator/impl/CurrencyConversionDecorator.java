package ru.clevertec.decorator.impl;

import ru.clevertec.decorator.PaymentDecorator;
import ru.clevertec.model.User;
import ru.clevertec.strategy.PaymentStrategy;

public class CurrencyConversionDecorator extends PaymentDecorator {
    private final double conversionRate;

    public CurrencyConversionDecorator(PaymentStrategy payment, double conversionRate) {
        super(payment);
        this.conversionRate = conversionRate;
    }

    @Override
    public void processPayment(User user, double amount) {
        double convertedAmount = convertCurrency(amount);
        decoratedPayment.processPayment(user, convertedAmount);
    }

    private double convertCurrency(double amount) {
        double convertedAmount = amount * conversionRate;
        System.out.println("Converted amount to $" + convertedAmount);
        return convertedAmount;
    }
}
