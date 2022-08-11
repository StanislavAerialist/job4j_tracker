package ru.job4j.poly;

public class Train  implements Vehicle {
    @Override
    public void move() {
        System.out.println("Ту-Ту");
    }

    @Override
    public void fuel(int fuel) {
        System.out.println("Осталось угля : " + fuel);
    }
}
