import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SumOfSquares {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        IntStream intStream = numbers.stream()
                .mapToInt(n -> n * n);
        intStream.forEach(System.out::println);
    }
}
