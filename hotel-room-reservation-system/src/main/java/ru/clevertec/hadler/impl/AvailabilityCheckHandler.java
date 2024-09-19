package ru.clevertec.hadler.impl;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;

public class AvailabilityCheckHandler extends BookingHandler {
    @Override
    public void handleRequest(BookingRequest request) {
        if (request.getRoom().isAvailable()) {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        } else {
            System.out.println("Room is not available.");
        }
    }
}
