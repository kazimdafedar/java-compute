import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByAge {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(
                new Person("kazim", 30),
                new Person("dafedar", 31),
                new Person("shirin", 25),
                new Person("Haider",25)
        );
        Map<Integer, List<Person>> collect = list.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect);

        List<Person> collect3 = list.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed().thenComparing(Person::getName).reversed())
                .collect(Collectors.toList());
        System.out.println(collect3);

        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9, 2);
        List<Integer> collect1 = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(collect1);
        List<Integer> collect2 = numbers.stream()
                .sorted(Comparator.comparingInt(a -> a))
                .collect(Collectors.toList());
        System.out.println(collect2);

    }
}
