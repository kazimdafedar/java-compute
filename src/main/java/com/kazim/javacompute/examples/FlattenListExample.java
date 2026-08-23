package com.kazim.javacompute.examples;

import java.util.List;
import java.util.stream.Collectors;

public final class FlattenListExample {

    private FlattenListExample() {
    }

    public static List<Integer> flatten(List<List<Integer>> nestedLists) {
        return nestedLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        System.out.println(flatten(nestedLists));
    }
}
