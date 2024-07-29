import java.util.function.Supplier;

public class SupplyExample {
    static Supplier<String> length = () -> "hello";
    public static void main(String[] args) {
        System.out.println(length.get());
    }
}
