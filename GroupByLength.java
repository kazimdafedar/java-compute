import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupByLength {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape");
        Map<Integer, List<String>> collect = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(collect);

        Map<String, Integer> collect1 = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(collect1);

        String input = "swiss";
        Stream<Character> characterStream = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey);
        characterStream.forEach(System.out::println);

    }
}
