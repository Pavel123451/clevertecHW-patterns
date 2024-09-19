package ru.clevertec.hadler.impl;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;

public class LoyaltyPointsHandler extends BookingHandler {
    @Override
    public void handleRequest(BookingRequest request) {
        if (request.isReturningCustomer()) {
            applyLoyaltyPoints(request);
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    private void applyLoyaltyPoints(BookingRequest request) {
        System.out.println("Loyalty points applied for customer.");
    }
}