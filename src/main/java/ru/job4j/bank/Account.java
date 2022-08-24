package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс Account является моделью данных, содержит поля: баланс и реквизиты
 * @author StanislavAerialist
 * @version 1.0
 */
public class Account {
    /**
     * Поле содержит реквизиты счета типа String
     */
    private String requisite;
    /**
     * Поле содержит баланс счета типа Double
     */
    private double balance;
    /**
     * Контсруктор для создания объектов типа Account c параметрами
     * @param requisite реквизи счета
     * @param balance баланс счета
     */

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Геттер для поля реквизитов
     * @return значение типа String
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Сеттер для поля реквизитов
     * @param requisite значение типа String
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Геттер для поля баланса
     * @return значение типа Double
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Сеттер для поля баланса
     * @param balance значение типа Double
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод производит сравнение счетов по полю реквизитов
     * @param o на вход подается счет
     * @return возвращает true если реквизиты идентичны и false если отличаются
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Метод для получения Хэш-кода
     * @return возвращает Хэш значение поля реквизиты
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}