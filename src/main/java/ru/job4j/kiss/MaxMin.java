package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, (a, b) -> comparator.compare(a, b) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    private <T> T find(List<T> value, BiPredicate<T, T> predicate) {
        if (value.size() == 0) {
            throw new IllegalArgumentException("list is empty!");
        }
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T curr = value.get(i);
            if (predicate.test(curr, rsl)) {
                rsl = curr;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(9);
        values.add(5);
        values.add(3);
        values.add(1);
        values.add(11);
        values.add(6);
        System.out.println(new MaxMin().max(values, Integer::compare));
        System.out.println(new MaxMin().min(values, Integer::compare));
    }
}