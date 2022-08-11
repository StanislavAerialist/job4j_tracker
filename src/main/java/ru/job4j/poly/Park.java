package ru.job4j.poly;

public class Park {
    public static void main(String[] args) {
        int fuel = 10;
        Vehicle bigbus1 = new BigBus();
        Vehicle bigbus2 = new BigBus();
        Vehicle train1 = new Train();
        Vehicle train2 = new Train();
        Vehicle airplane1 = new Airplane();
        Vehicle airplane2 = new Airplane();
        Vehicle[] park = new Vehicle[]{bigbus1, bigbus2, train1, train2, airplane1, airplane2};
        for (Vehicle v : park) {
            v.move();
            v.fuel(fuel);
        }
    }
}
