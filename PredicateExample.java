import java.util.function.Predicate;

public class PredicateExample {
    static Predicate<String> lengthPredicate = str -> str.length() > 5;

    public static void main(String[] args) {
        System.out.println(lengthPredicate.test("hello"));
        System.out.println(lengthPredicate.test("helloworld"));
    }
}
