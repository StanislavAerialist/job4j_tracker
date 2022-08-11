package ru.job4j.poly;

public class BigBus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Бип-Бип");
    }

    @Override
    public void fuel(int fuel) {
        System.out.println("Осталось солярки : " + fuel);
    }
}
