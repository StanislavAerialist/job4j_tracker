package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteActionTest {
    @Test
    public void whenDeleted() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        tracker.add(new Item("Item for delete"));
        DeleteAction rep = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка удалена успешно." + ln));
    }

    @Test
    public void whenNotFound() {
        Output out = new StubOutput();
        MeMTracker tracker = new MeMTracker();
        tracker.add(new Item("Item for delete"));
        DeleteAction rep = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Ошибка удаления заявки." + ln));
    }
}