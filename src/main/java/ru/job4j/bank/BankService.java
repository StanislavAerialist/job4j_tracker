package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс BankService является простейшей моделью банковской системы.
 * Описывает взаимодействия между моделями данных: User и Account.
 * @author StanislavAerialist
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение информации о пользователях и их счетах осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод производит проверку на наличие пользователя в базе и добавляет пользователя если его нет
     * @param user на вход подается пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод производит проверку на наличие счета, добавляет счет пользователю
     * в случае отсутсвия счета в базе, на вход подаются:
     * @param passport паспорт пользователя
     * @param account счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод производит поиск пользователя в базе по паспорту, на вход подается
     * @param passport паспорт пользователя
     * @return возвращает пользователя если он есть базе, либо null если пользователя нет
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User key : users.keySet()) {
            if (key.getPassport().equals(passport)) {
                rsl = key;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод производит поиск счета по паспорту пользователя и реквизитам счета на вход подается
     * @param passport паспорт пользователя
     * @param requisite реквизиты счета
     * @return возвращает счет если он есть базе, либо null если счета нет
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            for (Account a : users.get(user)) {
                if (a.getRequisite().equals(requisite)) {
                    rsl = a;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод перевод деньги между счетами пользователей на вход подается
     * @param srcPassport паспорт отправителя
     * @param srcRequisite счет отправителя
     * @param destPassport паспорт получателя
     * @param destRequisite счет получателя
     * @param amount сумма перевода
     * @return возвращает true если перевод прошел и false если нет
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account scr = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (scr != null && dest != null && scr.getBalance() >= amount) {
            scr.setBalance(scr.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод для вывода всех счетов пользователя на вход подается
     * @param user пользователь
     * @return на выходе список счетов
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}