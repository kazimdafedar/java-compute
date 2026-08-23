package com.kazim.javacompute.examples;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class GroupByLengthExample {

    private GroupByLengthExample() {
    }

    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    public static Map<String, Integer> mapWordToLength(List<String> words) {
        return words.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
    }

    public static List<Character> firstNonRepeatingCharacters(String input) {
        return input.chars()
                .mapToObj(character -> (char) character)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "cherry", "date", "fig", "grape");

        System.out.println(groupByLength(words));
        System.out.println(mapWordToLength(words));
        System.out.println(firstNonRepeatingCharacters("swiss"));
    }
}
