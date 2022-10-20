package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MeMTrackerTest {
    @Test
    public void whenTestFindById() {
        MeMTracker meMTracker = new MeMTracker();
        Item bug = new Item("Bug");
        Item item = meMTracker.add(bug);
        Item result = meMTracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenTestFindAll() {
        MeMTracker meMTracker = new MeMTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        meMTracker.add(first);
        meMTracker.add(second);
        Item result = meMTracker.findAll().get(0);
        assertThat(result.getName()).isEqualTo(first.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        MeMTracker meMTracker = new MeMTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        meMTracker.add(first);
        meMTracker.add(second);
        meMTracker.add(new Item("First"));
        meMTracker.add(new Item("Second"));
        meMTracker.add(new Item("First"));
        List<Item> result = meMTracker.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        MeMTracker meMTracker = new MeMTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        meMTracker.add(first);
        meMTracker.add(second);
        meMTracker.add(new Item("First"));
        meMTracker.add(new Item("Second"));
        meMTracker.add(new Item("First"));
        List<Item> result = meMTracker.findByName(second.getName());
        assertThat(result.get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplace() {
        MeMTracker meMTracker = new MeMTracker();
        Item bug = new Item();
        bug.setName("Bug");
        meMTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        meMTracker.replace(id, bugWithDesc);
        assertThat(meMTracker.findById(id).getName()).isEqualTo("Bug with description");
    }

    @Test
    public void whenDelete() {
        MeMTracker meMTracker = new MeMTracker();
        Item bug = new Item();
        bug.setName("Bug");
        meMTracker.add(bug);
        int id = bug.getId();
        meMTracker.delete(id);
        assertThat(meMTracker.findById(id)).isNull();
    }
}