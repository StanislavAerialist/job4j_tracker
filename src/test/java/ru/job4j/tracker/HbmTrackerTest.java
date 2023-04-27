package ru.job4j.tracker;

import org.hamcrest.core.IsNull;
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
            Item item = tracker.add(new Item("item"));
            int id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), is(IsNull.nullValue()));
        }
    }

    @Test
    public void whenReplaceItem1ThenItem2() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            int id = item1.getId();
            Item item2 = tracker.add(new Item("item2"));
            tracker.replace(id, item2);
            assertThat(tracker.findById(id).getName(), is("item2"));
        }
    }

    @Test
    public void whenFindAllThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            Item item2 = tracker.add(new Item("item2"));
            assertThat(tracker.findAll(), is(List.of(item1, item2)));
        }
    }

    @Test
    public void whenFindByNameThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            assertThat(tracker.findByName(item1.getName()), is(List.of(item1)));
        }
    }
}