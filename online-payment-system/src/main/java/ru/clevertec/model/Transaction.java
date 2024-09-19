package ru.clevertec.model;

public class Transaction {
    private final String type;
    private final double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " transaction of $" + amount;
    }

    public double getAmount() {
        return amount;
    }
}
