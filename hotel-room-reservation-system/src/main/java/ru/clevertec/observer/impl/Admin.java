package ru.clevertec.observer.impl;

import ru.clevertec.model.BookingRequest;
import ru.clevertec.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Admin implements Observer {
    private final String name;
    private final List<BookingRequest> bookings = new ArrayList<>();

    public Admin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(BookingRequest request) {
        System.out.println("Admin "
                + name
                + " received notification: Room "
                + request.getRoom().getNumber()
                + " booked.");
        addBookingToDatabase(request);
        sendEmailConfirmation(request);
    }

    private void addBookingToDatabase(BookingRequest request) {
        bookings.add(request);
        System.out.println("Admin "
                + name
                + " added booking to database for Room "
                + request.getRoom().getNumber());
    }

    private void sendEmailConfirmation(BookingRequest request) {
        System.out.println("Admin "
                + name
                + " sent email confirmation for Room "
                + request.getRoom().getNumber());
    }

    public List<BookingRequest> getBookings() {
        return bookings;
    }
}
