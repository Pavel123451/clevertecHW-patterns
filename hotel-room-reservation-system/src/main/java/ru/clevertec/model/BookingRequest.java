package ru.clevertec.model;

import java.util.Objects;

public class BookingRequest {
    private final Room room;
    private final CreditCard creditCard;
    private final int stayDuration;
    private final boolean returningCustomer;

    public BookingRequest(Room room, CreditCard creditCard, int stayDuration, boolean returningCustomer) {
        this.room = room;
        this.creditCard = creditCard;
        this.stayDuration = stayDuration;
        this.returningCustomer = returningCustomer;
    }

    public Room getRoom() {
        return room;
    }

    public CreditCard getCreditCardNumber() {
        return creditCard;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public boolean isReturningCustomer() {
        return returningCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequest that = (BookingRequest) o;
        return stayDuration == that.stayDuration
                && returningCustomer == that.returningCustomer
                && Objects.equals(room, that.room)
                && Objects.equals(creditCard, that.creditCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, creditCard, stayDuration, returningCustomer);
    }
}
