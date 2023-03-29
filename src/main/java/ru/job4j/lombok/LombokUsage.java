package ru.job4j.lombok;

import java.util.List;

public class LombokUsage {
    public static void main(String[] args) {
        var per = Permission.builder()
                .id(1)
                .name("ADMIN")
                .rules(List.of("rule1", "rule2"))
                .rules("rule3")
                .build();
        System.out.println(per);
    }
}