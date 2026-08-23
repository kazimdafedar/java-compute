package com.kazim.javacompute.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamExamplesTest {

    @Test
    void flattenNestedLists() {
        List<List<Integer>> nested = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );

        assertEquals(List.of(1, 2, 3, 4), FlattenListExample.flatten(nested));
    }

    @Test
    void groupWordsByLength() {
        List<String> words = List.of("go", "java", "stream");

        Map<Integer, List<String>> grouped = GroupByLengthExample.groupByLength(words);

        assertEquals(List.of("go"), grouped.get(2));
        assertEquals(List.of("java", "stream"), grouped.get(4));
        assertEquals(List.of("stream"), grouped.get(6));
    }

    @Test
    void findPairsThatSumToTarget() {
        List<Integer> numbers = List.of(2, 4, 3, 6);

        List<int[]> pairs = PairSumExample.findPairsWithSum(numbers, 6);

        assertEquals(3, pairs.size());
        assertTrue(containsPair(pairs, 2, 4));
        assertTrue(containsPair(pairs, 4, 2));
        assertTrue(containsPair(pairs, 3, 3));
    }

    @Test
    void findSecondHighestDistinctValue() {
        List<Integer> numbers = List.of(3, 5, 9, 1, 6, 8, 9);

        assertEquals(8, SecondHighestExample.findSecondHighest(numbers).orElseThrow());
    }

    @Test
    void sumOfSquaresReturnsExpectedTotal() {
        assertEquals(55, SumOfSquaresExample.sumOfSquares(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void predicateChecksStringLength() {
        assertFalse(PredicateExample.isLongerThanFive("hello"));
        assertTrue(PredicateExample.isLongerThanFive("helloworld"));
    }

    @Test
    void supplierReturnsGreeting() {
        assertEquals("hello", SupplierExample.getGreeting());
    }

    @Test
    void firstNonRepeatingCharactersAreReturned() {
        assertEquals(List.of('w'), GroupByLengthExample.firstNonRepeatingCharacters("swiss"));
    }

    private static boolean containsPair(List<int[]> pairs, int first, int second) {
        return pairs.stream().anyMatch(pair -> pair[0] == first && pair[1] == second);
    }
}
