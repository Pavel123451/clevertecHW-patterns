package ru.clevertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.hadler.BookingHandler;
import ru.clevertec.hadler.impl.AvailabilityCheckHandler;
import ru.clevertec.hadler.impl.BookingConfirmationHandler;
import ru.clevertec.hadler.impl.CreditCardValidationHandler;
import ru.clevertec.manager.HotelManager;
import ru.clevertec.model.CreditCard;
import ru.clevertec.model.Room;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HotelManagerTest {
    private HotelManager hotelManager;

    @BeforeEach
    void setUp() {
        hotelManager = new HotelManager(5);
    }

    @Test
    void testBookRoomSuccessfully() throws Exception {
        BookingHandler chain = BookingHandler.link(
                new AvailabilityCheckHandler(),
                new CreditCardValidationHandler(),
                new BookingConfirmationHandler(hotelManager.getBookingPublisher())
        );

        CreditCard creditCard = new CreditCard("1234567890123456");

        Room room = getRoomReflection(1);

        assertTrue(room.isAvailable());

        hotelManager.bookRoom(1, creditCard, 3, false, chain);

        assertFalse(room.isAvailable());
    }

    @Test
    void testReleaseRoom() throws Exception {
        Room room = getRoomReflection(1);
        room.book();

        hotelManager.releaseRoom(1);

        assertTrue(room.isAvailable());
    }

    private Room getRoomReflection(int roomNumber) throws Exception {
        Method getRoomMethod = HotelManager.class.getDeclaredMethod("getRoom", int.class);
        getRoomMethod.setAccessible(true);
        return (Room) getRoomMethod.invoke(hotelManager, roomNumber);
    }
}
