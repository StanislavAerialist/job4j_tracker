package ru.job4j.early;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @Test
    public void whenValidPass() {
        String password = "Fgh1!Lo#";
        PasswordValidator.validate(password);
        assertThat(PasswordValidator.validate(password)).isEqualTo("Успешный успех");
    }

    @Test
    public void whenPassWithoutUper() {
        String password = "fgh1!go#";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль содержит хотя бы один символ в верхнем регистре");
    }

    @Test
    public void whenPassWithoutLower() {
        String password = "FGH1!GO#";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль содержит хотя бы один символ в нижнем регистре");
    }

    @Test
    public void whenPassWithoutDigit() {
        String password = "FGhR!GO#";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль содержит хотя бы одну цифру");
    }

    @Test
    public void whenPassWithoutSpec() {
        String password = "FGhR1GO2";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль содержит хотя бы один спец. символ (не цифра и не буква)");
    }

    @Test
    public void whenPassWithSimple() {
        String password = "qwerty#B2";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль не содержит подстрок без учета регистра: qwerty, 12345, password, admin, user.");
    }

    @Test
    public void whenPassIsShort() {
        String password = "FG1GO2";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Длина пароля находится в диапазоне [8, 32]");
    }

    @Test
    public void whenPassIsNull() {
        String password = null;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                });
        assertThat(exception.getMessage()).isEqualTo("Пароль не может быть null!");
    }
}