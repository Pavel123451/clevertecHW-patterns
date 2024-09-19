package ru.clevertec.hadler.impl;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;

public class MinimumStayValidationHandler extends BookingHandler {
    private final int minimumStayDays;

    public MinimumStayValidationHandler(int minimumStayDays) {
        this.minimumStayDays = minimumStayDays;
    }

    @Override
    public void handleRequest(BookingRequest request) {
        if (request.getStayDuration() >= minimumStayDays) {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        } else {
            System.out.println("Booking does not meet the minimum stay requirement.");
        }
    }
}

