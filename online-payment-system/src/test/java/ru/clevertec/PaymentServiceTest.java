package ru.clevertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.decorator.impl.CashbackDecorator;
import ru.clevertec.decorator.impl.CurrencyConversionDecorator;
import ru.clevertec.decorator.impl.FeeDecorator;
import ru.clevertec.model.User;
import ru.clevertec.service.PaymentService;
import ru.clevertec.strategy.PaymentStrategy;
import ru.clevertec.strategy.impl.CreditCardPayment;
import ru.clevertec.strategy.impl.PayPalPayment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User("Pavel");
    }

    @Test
    void testPaymentWithCreditCardStrategyAndFeeDecorator() {
        PaymentStrategy baseStrategy = new CreditCardPayment();
        PaymentStrategy decoratedStrategy = new FeeDecorator(baseStrategy);
        PaymentService service = new PaymentService(decoratedStrategy);

        service.processPayment(user, 200.0);

        assertEquals(204.0, user.getTransactions().getFirst().getAmount());
    }

    @Test
    void testPaymentWithPayPalStrategyAndCashbackDecorator() {
        PaymentStrategy baseStrategy = new PayPalPayment();
        PaymentStrategy decoratedStrategy = new CashbackDecorator(baseStrategy);
        PaymentService service = new PaymentService(decoratedStrategy);

        service.processPayment(user, 150.0);

        assertEquals(7.5, user.getCashback());
    }

    @Test
    void testPaymentWithCurrencyConversionDecorator() {
        PaymentStrategy baseStrategy = new CreditCardPayment();
        PaymentStrategy decoratedStrategy = new CurrencyConversionDecorator(
                new FeeDecorator(baseStrategy),
                0.9
        );
        PaymentService service = new PaymentService(decoratedStrategy);

        service.processPayment(user, 300.0);

        assertEquals(275.4, user.getTransactions().getFirst().getAmount());
    }

    @Test
    void testPaymentWithMultipleDecorators() {
        PaymentStrategy baseStrategy = new CreditCardPayment();
        PaymentStrategy decoratedStrategy = (new CurrencyConversionDecorator(
                        new FeeDecorator(new CashbackDecorator(baseStrategy)), 0.85
                )
        );
        PaymentService service = new PaymentService(decoratedStrategy);

        service.processPayment(user, 250.0);

        assertEquals(216.75,
                user.getTransactions().getFirst().getAmount(),
                0.01
        );

        assertEquals(10.84, user.getCashback(), 0.01);
    }
}
