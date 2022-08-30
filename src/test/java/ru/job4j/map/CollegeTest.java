package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CollegeTest {
    @Test
    public void whenAccountIsOptionalEmpty() {
        Map<Student, Set<SubjectForCollege>> students = Map.of(
                new Student("Student1", "000001", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Math", 70),
                        new SubjectForCollege("English", 85)
                ),
                new Student("Student2", "000002", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Economic", 75),
                        new SubjectForCollege("Sociology", 65)
                )
        );
        College college = new College(students);
        assertThat(college.findByAccount("000010")).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAccountIsOptionalNotEmpty() {
        Map<Student, Set<SubjectForCollege>> students = Map.of(
                new Student("Student1", "000001", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Math", 70),
                        new SubjectForCollege("English", 85)
                ),
                new Student("Student2", "000002", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Economic", 75),
                        new SubjectForCollege("Sociology", 65)
                )
        );
        College college = new College(students);
        assertThat(college.findByAccount("000001").get().getGroup()).isEqualTo("201-18-15");
    }

    @Test
    public void whenSubjectIsOptionalEmptyWithNotFoundAccount() {
        Map<Student, Set<SubjectForCollege>> students = Map.of(
                new Student("Student1", "000001", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Math", 70),
                        new SubjectForCollege("English", 85)
                ),
                new Student("Student2", "000002", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Economic", 75),
                        new SubjectForCollege("Sociology", 65)
                )
        );
        College college = new College(students);
        assertThat(college.findBySubjectName("000010", "Sociology")).isEqualTo(Optional.empty());
    }

    @Test
    public void whenSubjectIsOptionalEmptyWithFoundAccount() {
        Map<Student, Set<SubjectForCollege>> students = Map.of(
                new Student("Student1", "000001", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Math", 70),
                        new SubjectForCollege("English", 85)
                ),
                new Student("Student2", "000002", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Economic", 75),
                        new SubjectForCollege("Sociology", 65)
                )
        );
        College college = new College(students);
        assertThat(college.findBySubjectName("000001", "Sociology")).isEqualTo(Optional.empty());
    }

    @Test
    public void whenSubjectIsOptionalNotEmpty() {
        Map<Student, Set<SubjectForCollege>> students = Map.of(
                new Student("Student1", "000001", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Math", 70),
                        new SubjectForCollege("English", 85)
                ),
                new Student("Student2", "000002", "201-18-15"),
                Set.of(
                        new SubjectForCollege("Economic", 75),
                        new SubjectForCollege("Sociology", 65)
                )
        );
        College college = new College(students);
        assertThat(college.findBySubjectName("000002", "Sociology").get().getScore()).isEqualTo(65);
    }
}