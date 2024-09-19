package ru.clevertec.publisher;

import ru.clevertec.model.BookingRequest;
import ru.clevertec.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BookingPublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(BookingRequest request) {
        for (Observer observer : observers) {
            observer.update(request);
        }
    }
}
