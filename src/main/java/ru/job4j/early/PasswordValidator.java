package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Пароль не может быть null!");
        }
        if (password.length() < 8 || password.length() > 32) {
            return "Длина пароля находится в диапазоне [8, 32]";
        }
        String[] simples = new String[]{"qwerty", "12345", "password", "admin", "user"};
        char[] pass = password.toCharArray();
        boolean uper = false;
        boolean lower = false;
        boolean num = false;
        boolean spec = false;
        boolean simple = true;
        for (int i = 0; i < pass.length; i++) {
            if (Character.isLowerCase(pass[i])) {
                lower = true;
            }
            if (Character.isUpperCase(pass[i])) {
                uper = true;
            }
            if (Character.isDigit(pass[i])) {
                num = true;
            }
            if (!Character.isLetterOrDigit(pass[i])) {
                spec = true;
            }
        }
        for (int i = 0; i < simples.length; i++) {
            String lowerPass = password.toLowerCase();
            if (lowerPass.contains(simples[i])) {
                simple = false;
            }
        }
        if (!uper) {
            return "Пароль содержит хотя бы один символ в верхнем регистре";
        }
        if (!lower) {
            return "Пароль содержит хотя бы один символ в нижнем регистре";
        }
        if (!num) {
            return "Пароль содержит хотя бы одну цифру";
        }
        if (!spec) {
            return "Пароль содержит хотя бы один спец. символ (не цифра и не буква)";
        }
        if (!simple) {
            return "Пароль не содержит подстрок без учета регистра: qwerty, 12345, password, admin, user.";
        }
    return "Успешный успех";
    }
}
