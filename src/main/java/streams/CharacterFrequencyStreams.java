package streams;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java 8 Streams — character / word frequency interview questions.
 */
class CharacterFrequencyStreams {

    /** 1. Most repeated character (includes spaces). */
    static char mostRepeated(String text) {
        return frequencies(text).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalArgumentException("empty"))
                .getKey();
    }

    /** 2. Highest frequency character excluding spaces. */
    static char highestExcludingSpaces(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != ' ')
                .collect(groupingBy(c -> c, counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalArgumentException("empty"))
                .getKey();
    }

    /** 3. Second highest occurring character (second distinct frequency). */
    static char secondHighest(String text) {
        Map<Character, Long> freq = frequencies(text);
        long max = freq.values().stream().max(Long::compareTo).orElse(0L);
        return freq.entrySet().stream()
                .filter(e -> e.getValue() < max)
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalArgumentException("no second frequency"))
                .getKey();
    }

    /** 4. Count occurrences of each character. */
    static Map<Character, Long> countCharacters(String text) {
        return frequencies(text);
    }

    /** 5. Count occurrences of each word. */
    static Map<String, Long> countWords(String text) {
        return Arrays.stream(text.trim().split("\\s+"))
                .filter(word -> !word.isEmpty())
                .collect(groupingBy(String::toLowerCase, LinkedHashMap::new, counting()));
    }

    /** 6. Top N frequent characters. */
    static List<Character> topNCharacters(String text, int n) {
        return frequencies(text).entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    static Map<Character, Long> frequencies(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, LinkedHashMap::new, counting()));
    }

    void main() {
        String text = "banana split";
        IO.println("text: " + text);
        IO.println("1. most repeated:            " + mostRepeated(text));            // a (3)
        IO.println("2. highest excl. spaces:     " + highestExcludingSpaces(text));  // a
        IO.println("3. second highest:           " + secondHighest(text));           // n (2)
        IO.println("4. char counts:              " + countCharacters(text));
        IO.println("5. word counts:              " + countWords(text));              // banana=1, split=1
        IO.println("6. top 3 characters:         " + topNCharacters(text, 3));       // [a, n, ...]
    }
}
