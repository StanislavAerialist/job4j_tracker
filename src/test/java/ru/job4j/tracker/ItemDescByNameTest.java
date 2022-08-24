package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemDescByNameTest {
    @Test
    public void whenDescSort() {
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        List<Item> items = new ArrayList<>();
        items.add(item3);
        items.add(item2);
        items.add(item1);
        items.add(item4);
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(item4, item3, item2, item1);
        assertThat(items).hasSameElementsAs(expected);
    }

}