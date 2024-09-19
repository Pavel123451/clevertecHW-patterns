package ru.clevertec.hadler.impl;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;
import ru.clevertec.publisher.BookingPublisher;

public class BookingConfirmationHandler extends BookingHandler {
    private final BookingPublisher publisher;

    public BookingConfirmationHandler(BookingPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void handleRequest(BookingRequest request) {
        request.getRoom().book();
        publisher.notifyObservers(request);
    }
}
