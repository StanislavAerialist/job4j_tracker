package ru.job4j.poly;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Вжух");
    }

    @Override
    public void fuel(int fuel) {
        System.out.println("Осталось киросина : " + fuel);
    }
}
