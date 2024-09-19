package ru.clevertec.observer;

import ru.clevertec.model.BookingRequest;

public interface Observer {
    void update(BookingRequest request);
}
