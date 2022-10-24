package ru.job4j.tracker;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindAllTest() {
        SqlTracker tracker = new SqlTracker(connection);
        Item[] items = {
                tracker.add(new Item("item1")),
                tracker.add(new Item("item1")),
                tracker.add(new Item("item1"))
        };
        List<Item> rsl = tracker.findAll();
        Assertions.assertThat(rsl).hasSameElementsAs(Arrays.asList(items));
    }

    @Test
    public void whenDeleteItemIsTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item1"));
        Assertions.assertThat(tracker.delete(item.getId())).isTrue();
        Assertions.assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenReplaceItemIsTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = new Item("item2");
        Assertions.assertThat(tracker.replace(item1.getId(), item2)).isTrue();
        Assertions.assertThat(tracker.findById(item1.getId()).getName()).isEqualTo("item2");
    }

    @Test
    public void whenAddItemTest() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Assertions.assertThat(tracker.add(item1)).isEqualTo(item1);
    }

    @Test
    public void whenFindByNameTest() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item1"));
        Item item3 = tracker.add(new Item("item3"));
        List<Item> rsl = tracker.findByName("item1");
        Assertions.assertThat(rsl).containsOnly(item1, item2);
    }
}
