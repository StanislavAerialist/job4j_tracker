package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class HbmTrackerTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @AfterEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Item").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenDeleteItemThenNull() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("item");
            tracker.add(item);
            boolean result = tracker.delete(item.getId());
            assertThat(result, is(true));
            assertThat(tracker.findById(item.getId()), is(nullValue()));
        }
    }

    @Test
    public void whenReplaceItem1ThenItem2() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            tracker.add(item1);
            boolean result = tracker.replace(item1.getId(), item2);
            assertThat(result, is(true));
            assertThat(tracker.findById(item1.getId()).getName(), is(item2.getName()));
        }
    }

    @Test
    public void whenFindAllThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> result = tracker.findAll();
            assertThat(result, is(List.of(item1, item2)));
        }
    }

    @Test
    public void whenFindByNameThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> result = tracker.findByName("item2");
            assertThat(result, is(List.of(item2)));
        }
    }
}