package ru.clevertec.model;

import java.util.Objects;

public class Room {
    private final int number;
    private boolean isAvailable;

    public Room(int number) {
        this.number = number;
        this.isAvailable = true;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void book() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && isAvailable == room.isAvailable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, isAvailable);
    }
}
