package ru.clevertec;

import org.junit.jupiter.api.Test;
import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.hadler.impl.*;
import ru.clevertec.model.BookingRequest;
import ru.clevertec.model.CreditCard;
import ru.clevertec.model.Room;
import ru.clevertec.observer.impl.Admin;
import ru.clevertec.publisher.BookingPublisher;

import static org.junit.jupiter.api.Assertions.*;

class BookingHandlersTest {

    @Test
    void testFullChainWithValidBooking() {
        Room room = new Room(1);
        CreditCard creditCard = new CreditCard("1234567890123456");
        BookingPublisher publisher = new BookingPublisher();
        Admin admin = new Admin("John");
        publisher.addObserver(admin);

        BookingRequest request = new BookingRequest(room, creditCard, 5, true);

        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new MinimumStayValidationHandler(3),
                new CreditCardValidationHandler(),
                new LoyaltyPointsHandler(),
                new BookingConfirmationHandler(publisher)
        );

        assertTrue(room.isAvailable());
        chain.handleRequest(request);
        assertFalse(room.isAvailable());
        assertEquals(1, admin.getBookings().size());
    }

    @Test
    void testChainWithInvalidCreditCard() {
        Room room = new Room(2);
        CreditCard invalidCard = new CreditCard("1234");

        BookingRequest request = new BookingRequest(room, invalidCard, 4, false);

        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new CreditCardValidationHandler(),
                new BookingConfirmationHandler(new BookingPublisher())
        );

        assertTrue(room.isAvailable());
        chain.handleRequest(request);
        assertTrue(room.isAvailable());
    }

    @Test
    void testChainWithMinimumStayViolation() {
        Room room = new Room(3);
        CreditCard creditCard = new CreditCard("1234567890123456");

        BookingRequest request = new BookingRequest(room, creditCard, 2, false);

        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new MinimumStayValidationHandler(3),
                new BookingConfirmationHandler(new BookingPublisher())
        );

        assertTrue(room.isAvailable());
        chain.handleRequest(request);
        assertTrue(room.isAvailable());
    }

    @Test
    void testPartialChainWithoutLoyaltyHandler() {
        Room room = new Room(4);
        CreditCard creditCard = new CreditCard("1234567890123456");

        BookingRequest request = new BookingRequest(room, creditCard, 3, true);

        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new CreditCardValidationHandler(),
                new BookingConfirmationHandler(new BookingPublisher())
        );

        assertTrue(room.isAvailable());
        chain.handleRequest(request);
        assertFalse(room.isAvailable());
    }

    @Test
    void testChainWithOnlyAvailabilityAndConfirmation() {
        Room room = new Room(5);
        CreditCard creditCard = new CreditCard("1234567890123456");

        BookingRequest request = new BookingRequest(room, creditCard, 3, false);

        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new BookingConfirmationHandler(new BookingPublisher())
        );

        assertTrue(room.isAvailable());
        chain.handleRequest(request);
        assertFalse(room.isAvailable());
    }
}
