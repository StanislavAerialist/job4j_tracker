package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("Поехали");
    }

    @Override
    public void countPassangers(int passangers) {
        System.out.println("Число пассажиров: " + passangers);
    }

    @Override
    public int price(int fuel) {
        return fuel * 50;
    }
}
