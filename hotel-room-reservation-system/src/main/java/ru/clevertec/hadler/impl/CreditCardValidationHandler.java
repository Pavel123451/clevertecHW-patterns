package ru.clevertec.hadler.impl;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;
import ru.clevertec.model.CreditCard;

public class CreditCardValidationHandler extends BookingHandler {
    @Override
    public void handleRequest(BookingRequest request) {
        if (validateCreditCard(request.getCreditCardNumber())) {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        } else {
            System.out.println("Invalid credit card.");
        }
    }

    private boolean validateCreditCard(CreditCard creditCard) {
        return creditCard.getCardNumber().length() == 16;
    }
}
