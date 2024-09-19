package ru.clevertec;

import org.junit.jupiter.api.Test;
import ru.clevertec.model.BookingRequest;
import ru.clevertec.model.CreditCard;
import ru.clevertec.model.Room;
import ru.clevertec.observer.impl.Admin;
import ru.clevertec.publisher.BookingPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingPublisherTest {

    @Test
    void testObserverNotification() {
        Room room = new Room(1);
        CreditCard creditCard = new CreditCard("1234567890123456");
        BookingRequest request = new BookingRequest(room, creditCard, 3, false);

        BookingPublisher publisher = new BookingPublisher();
        Admin admin = new Admin("Admin1");

        publisher.addObserver(admin);

        assertTrue(admin.getBookings().isEmpty());

        publisher.notifyObservers(request);

        assertEquals(1, admin.getBookings().size());
        assertEquals(room, admin.getBookings().getFirst().getRoom());
    }
}