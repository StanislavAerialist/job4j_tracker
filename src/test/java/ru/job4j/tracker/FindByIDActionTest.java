package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIDActionTest {
    @Test
    public void whenItemFindByID() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        Item item = (new Item("Item for FindByID"));
        tracker.add(item);
        FindByIDAction rep = new FindByIDAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + item + ln));
    }

    @Test
    public void whenItemNotFoundByID() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        int id = 2;
        FindByIDAction rep = new FindByIDAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(id);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + "Заявка с введенным id: " + id + " не найдена." + ln));
    }
}