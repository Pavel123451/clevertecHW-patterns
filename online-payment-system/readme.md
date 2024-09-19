# Система обработки платежей

Простая система обработки платежей с использованием паттерна **Декоратор** для добавления функциональностей к платежным стратегиям.

## Основные классы

- **PaymentStrategy**: интерфейс, определяющий метод для обработки платежей. Реализуется различными стратегиями, такими как кредитные карты, криптовалюты и PayPal.
- **PaymentDecorator**: абстрактный класс, реализующий интерфейс `PaymentStrategy` и содержащий ссылку на другую стратегию платежа. Позволяет добавлять новую функциональность к обработке платежей, такую как кешбэк, комимиссии или конвертацию валют.

## Пример использования

```java
User user = new User("Bobb");

double conversationRate = 1.1;
PaymentStrategy payment = new CashbackDecorator(
    new FeeDecorator(
        new CurrencyConversionDecorator(
            new CreditCardPayment(), 
            conversationRate
        )
    )
);

PaymentService paymentService = new PaymentService(payment);
paymentService.processPayment(user, 100.0);
