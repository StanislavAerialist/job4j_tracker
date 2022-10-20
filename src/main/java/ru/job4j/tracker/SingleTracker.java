package ru.job4j.tracker;

import java.util.List;

public final class SingleTracker {
    private static SingleTracker singleTracker = null;
    private MeMTracker meMTracker = new MeMTracker();

    private SingleTracker() {
    }

    public static SingleTracker getSingleTracker() {
        if (singleTracker == null) {
            singleTracker = new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return meMTracker.add(item);
    }

    public Item findById(int id) {
        return meMTracker.findById(id);
    }

    public List<Item> findAll() {
        return meMTracker.findAll();
    }

    public List<Item> findByName(String key) {
        return meMTracker.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return meMTracker.replace(id, item);
    }

    public boolean delete(int id) {
        return meMTracker.delete(id);
    }
}