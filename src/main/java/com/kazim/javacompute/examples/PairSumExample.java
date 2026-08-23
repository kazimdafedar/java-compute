package com.kazim.javacompute.examples;

import java.util.List;
import java.util.stream.Collectors;

public final class PairSumExample {

    private PairSumExample() {
    }

    public static List<int[]> findPairsWithSum(List<Integer> numbers, int targetSum) {
        return numbers.stream()
                .flatMap(first -> numbers.stream()
                        .filter(second -> first + second == targetSum)
                        .map(second -> new int[]{first, second}))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 4, 3, 6, 7, 8, 9);
        findPairsWithSum(numbers, 10)
                .forEach(pair -> System.out.println(pair[0] + " " + pair[1]));
    }
}
