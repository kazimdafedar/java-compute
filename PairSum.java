import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PairSum {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 4, 3, 6, 7, 8, 9);
        int sum = 10;
        List<int[]> collect = numbers.stream()
                .flatMap(a -> numbers.stream()
                        .filter(b -> a + b == sum)
                        .map(b -> new int[]{a, b}))
                .collect(Collectors.toList());

        collect.forEach(arr -> System.out.println(arr[0] +" "+arr[1]));

    }
}
