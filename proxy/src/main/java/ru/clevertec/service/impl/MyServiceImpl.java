package ru.clevertec.service.impl;

import ru.clevertec.annotation.Log;
import ru.clevertec.service.MyService;

public class MyServiceImpl implements MyService {
    @Log
    @Override
    public void performTask() {
        System.out.println("Executing task...");
    }

    @Override
    public void anotherTask() {
        System.out.println("Another task...");
    }
}
