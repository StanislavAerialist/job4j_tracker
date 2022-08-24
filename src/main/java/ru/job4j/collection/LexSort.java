package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] lSplit = left.split("\\.");
        String[] rSplit = right.split("\\.");
        return Integer.compare(Integer.parseInt(lSplit[0]), Integer.parseInt(rSplit[0]));
    }
}