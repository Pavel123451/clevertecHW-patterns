package ru.clevertec.manager;

import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.model.BookingRequest;
import ru.clevertec.model.CreditCard;
import ru.clevertec.model.Room;
import ru.clevertec.observer.Observer;
import ru.clevertec.publisher.BookingPublisher;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private final List<Room> rooms = new ArrayList<>();
    private final BookingPublisher bookingPublisher = new BookingPublisher();

    public HotelManager(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void bookRoom(int roomNumber, CreditCard creditCard, int stayDuration,
                         boolean returningCustomer,
                         BookingHandler chain) {

        Room room = getRoom(roomNumber);
        chain.handleRequest(new BookingRequest
                (
                        room,
                        creditCard,
                        stayDuration,
                        returningCustomer
                )
        );

    }

    public void releaseRoom(int roomNumber) {
        Room room = getRoom(roomNumber);
        if (!room.isAvailable()) {
            room.release();
            System.out.println("Room " + roomNumber + " released successfully.");
        }
    }

    public void addObserver(Observer observer) {
        bookingPublisher.addObserver(observer);
    }

    public BookingPublisher getBookingPublisher() {
        return bookingPublisher;
    }

    private Room getRoom(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getNumber() == roomNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room "
                        + roomNumber
                        + " does not exist."));
    }
}
