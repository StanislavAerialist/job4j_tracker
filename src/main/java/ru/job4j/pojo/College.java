package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setFio("Plyak Frol Ivanovich");
        student1.setDate(new Date());
        student1.setGroup(415);
        System.out.println("Student: " + student1.getFio() + " in group " + student1.getGroup() + " with date "
        + student1.getDate());
    }
}
