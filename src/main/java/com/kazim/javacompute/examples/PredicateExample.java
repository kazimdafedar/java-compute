package com.kazim.javacompute.examples;

import java.util.function.Predicate;

public final class PredicateExample {

    static final Predicate<String> LONGER_THAN_FIVE = word -> word.length() > 5;

    private PredicateExample() {
    }

    public static boolean isLongerThanFive(String word) {
        return LONGER_THAN_FIVE.test(word);
    }

    public static void main(String[] args) {
        System.out.println(isLongerThanFive("hello"));
        System.out.println(isLongerThanFive("helloworld"));
    }
}
