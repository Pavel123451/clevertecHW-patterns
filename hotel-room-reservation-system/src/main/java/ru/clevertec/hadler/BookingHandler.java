package ru.clevertec.hadler;

import ru.clevertec.model.BookingRequest;

public abstract class BookingHandler {
    protected BookingHandler nextHandler;

    public void setNextHandler(BookingHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(BookingRequest request);

    public static BookingHandler link(BookingHandler first, BookingHandler... chain) {
        BookingHandler head = first;
        for (BookingHandler nextInChain : chain) {
            head.setNextHandler(nextInChain);
            head = nextInChain;
        }
        return first;
    }
}
