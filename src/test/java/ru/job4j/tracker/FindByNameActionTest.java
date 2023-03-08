package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameActionTest {
    @Test
    public void whenItemFindByName() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        String name = "Item for FindByName";
        Item item = (new Item(name));
        tracker.add(item);
        FindByNameAction rep = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + item + ln));
    }

    @Test
    public void whenItemNotFoundByName() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        String name = "Item for FindByName";
        FindByNameAction rep = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + "Заявки с именем: " + name + " не найдены." + ln));
    }
}