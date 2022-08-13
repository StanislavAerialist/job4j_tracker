package ru.job4j.ex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FactTest {

    @Test
    public void whenNLess0ThenException() {
        Fact count = new Fact();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    count.calc(-5);
                });
        assertThat(exception.getMessage()).isEqualTo("N could not be less then 0");
    }
}