package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int minus) {
        return x - minus;
    }

    public int divide(int division) {
        return division / x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int sumAllOperation(int y) {
        return sum(y) + minus(y) + divide(y) + multiply(y);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println("Результат умножения: " + rsl);
        rsl = calculator.divide(5);
        System.out.println("Результат деления: " + rsl);
        System.out.println("Результат вычитания: " + minus(2));
        System.out.println("Результат сложения: " + sum(5));
        rsl = calculator.sumAllOperation(1);
        System.out.println("Результат всех операций: " + rsl);
    }
}