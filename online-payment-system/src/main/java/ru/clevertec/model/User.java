package ru.clevertec.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private double cashback;
    private final List<Transaction> transactions = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void addCashback(double amount) {
        cashback += amount;
    }

    public double getCashback() {
        return cashback;
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }
}
