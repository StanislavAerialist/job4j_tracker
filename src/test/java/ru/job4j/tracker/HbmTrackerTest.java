package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @BeforeEach
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
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenDeleteItemThenNull() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("item"));
            int id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id)).isNull();
        }
    }

    @Test
    public void whenReplaceItem1ThenItem2() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            int id = item1.getId();
            Item item2 = tracker.add(new Item("item2"));

            assertThat(tracker.replace(id, item2)).isTrue();
        }
    }

    @Test
    public void whenFindAllThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            Item item2 = tracker.add(new Item("item2"));
            assertThat(tracker.findAll()).isEqualTo(List.of(item1, item2));
        }
    }

    @Test
    public void whenFindByNameThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            assertThat(tracker.findByName(item1.getName())).isEqualTo(List.of(item1));
        }
    }
}