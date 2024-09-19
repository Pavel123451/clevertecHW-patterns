# Hotel Booking System

Простая система бронирования номеров в отеле с использованием паттернов **Цепочка обязанностей** и **Наблюдатель**.

## Основные классы

- **BookingHandler**: реализует паттерн **Цепочка обязанностей**, отвечает за обработку запроса бронирования через цепочку различных проверок (доступность номера, валидация карты и т.д.).
- **HotelManager**: фасад для управления номерами и бронированиями, упрощает работу с системой.
- **Admin**: реализует интерфейс **Observer** и представляет администратора отеля, который получает уведомления о новых бронированиях.
- **BookingPublisher**: реализует паттерн **Наблюдатель**. Отвечает за управление списком наблюдателей(администраторов) и уведомление их о событиях бронирования.

## Пример использования

```java
HotelManager hotelManager = new HotelManager(10);

int roomNumber = 1;
int stayDuration = 3;
String creditCardNumber = "1234567812345678";
boolean isReturningCustomer = true;
String adminName = "Bobbb";

BookingHandler chain = BookingHandler.link(
    new AvailabilityCheckHandler(),
    new CreditCardValidationHandler(),
    new LoyaltyPointsHandler(),
    new BookingConfirmationHandler(hotelManager.getBookingPublisher())
);

CreditCard card = new CreditCard(creditCardNumber);
hotelManager.addObserver(new Admin(adminName));
hotelManager.bookRoom(roomNumber, card, stayDuration, isReturningCustomer, chain);

