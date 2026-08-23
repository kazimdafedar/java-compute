package com.kazim.javacompute.examples;

import java.util.List;

public final class SumOfSquaresExample {

    private SumOfSquaresExample() {
    }

    public static int sumOfSquares(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(number -> number * number)
                .sum();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println(sumOfSquares(numbers));
    }
}
