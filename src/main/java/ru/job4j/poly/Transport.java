package ru.job4j.poly;

public interface Transport {
    void go();

    void countPassangers(int passangers);

    int price(int fuel);
}
