package com.kazim.javacompute.examples;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class SecondHighestExample {

    private SecondHighestExample() {
    }

    public static Optional<Integer> findSecondHighest(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 5, 9, 1, 6, 8);
        findSecondHighest(numbers).ifPresent(System.out::println);
    }
}
