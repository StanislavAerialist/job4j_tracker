package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    public void whenComparatorDescNameAndDescPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("AAA", 0),
                new Job("AAA", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenAscComparatorByNameMore0() {
        Comparator<Job> cmpAscName = new JobAscByName();
        int rsl = cmpAscName.compare(
                new Job("AAA", 12),
                new Job("BBB", 15)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenAscComparatorByNameLess0() {
        Comparator<Job> cmpAscName = new JobAscByName();
        int rsl = cmpAscName.compare(
                new Job("BBB", 12),
                new Job("AAA", 15)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenDescComparatorByNameMore0() {
        Comparator<Job> cmpAscName = new JobDescByName();
        int rsl = cmpAscName.compare(
                new Job("AAA", 12),
                new Job("BBB", 15)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenDescComparatorByNameLess0() {
        Comparator<Job> cmpAscName = new JobDescByName();
        int rsl = cmpAscName.compare(
                new Job("BBB", 12),
                new Job("AAA", 15)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenAscComparatorByPriorityLess0() {
        Comparator<Job> cmpAscName = new JobAscByPriority();
        int rsl = cmpAscName.compare(
                new Job("AAA", 12),
                new Job("BBB", 15)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenAscComparatorByPriorityMore0() {
        Comparator<Job> cmpAscName = new JobAscByPriority();
        int rsl = cmpAscName.compare(
                new Job("AAA", 15),
                new Job("BBB", 12)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenDescComparatorByPriorityMore0() {
        Comparator<Job> cmpAscName = new JobDescByPriority();
        int rsl = cmpAscName.compare(
                new Job("AAA", 12),
                new Job("BBB", 15)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenDescComparatorByPriorityLess0() {
        Comparator<Job> cmpAscName = new JobDescByPriority();
        int rsl = cmpAscName.compare(
                new Job("AAA", 15),
                new Job("BBB", 12)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorAscNameAndDescPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("AAA", 0),
                new Job("AAA", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenComparatorDescNameAndAscPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("AAA", 0),
                new Job("AAA", 1)
        );
        assertThat(rsl).isLessThan(0);
    }
}