package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionCalculator {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        Supplier<List<Double>> sup = ArrayList::new;
        List<Double> rsl = sup.get();
        Predicate<Double> cond = aDouble -> aDouble < end;
        Double con = (double) start;
        Function<Double, Boolean> addf = rsl::add;
        while (cond.test(con)) {
            Double num = func.apply(con);
            addf.apply(num);
            con++;
        }
        return rsl;
    }
}