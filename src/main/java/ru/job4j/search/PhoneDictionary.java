package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> combine = person -> person.getPhone().contains(key) || person.getAddress().contains(key)
                || person.getName().contains(key) || person.getSurname().contains(key);
        ArrayList<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (combine.test(p)) {
                result.add(p);
            }
        }
        return result;
    }
}