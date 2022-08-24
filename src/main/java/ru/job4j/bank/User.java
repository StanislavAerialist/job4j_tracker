package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс User является моделью данных, содержит поля: паспорт и имя
 * @author StanislavAerialist
 * @version 1.0
 */
public class User {
    /**
     * Поле содержит паспорт пользователя типа String
     */
    private String passport;
    /**
     * Поле содержит имя пользователя типа String
     */
    private String username;

    /**
     * Контсруктор для создания объектов типа Account c параметрами
     * @param passport паспорт пользователя
     * @param username имя пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Геттер для поля паспорт пользователя
     * @return значение типа String
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Сеттер для поля паспорт пользователя
     * @param passport значение типа String
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Геттер для поля имя пользователя
     * @return значение типа String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Сеттер для поля имя пользователя
     * @param username значение типа String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод производит сравнение пользователей по полю паспорт
     * @param o на вход подается пользователь
     * @return возвращает true если паспорта идентичны и false если отличаются
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Метод для получения Хэш-кода
     * @return возвращает Хэш значение поля паспорт
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}