package production;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

/**
 * File 04 — J07: Custom Comparator / Comparable
 */
class CustomComparator {

    record OrderKey(String customerId, Instant createdAt) implements Comparable<OrderKey> {
        @Override
        public int compareTo(OrderKey other) {
            int byTime = createdAt.compareTo(other.createdAt);
            return byTime != 0 ? byTime : customerId.compareTo(other.customerId);
        }
    }

    record Employee(String name, String department, int salary) {}

    static Comparator<Employee> byDeptThenSalary() {
        return Comparator
                .comparing(Employee::department, Comparator.nullsLast(String::compareTo))
                .thenComparingInt(Employee::salary)
                .thenComparing(Employee::name);
    }

    void main() {
        List<Employee> employees = List.of(
                new Employee("Bob", "Eng", 95_000),
                new Employee("Ada", "Eng", 120_000),
                new Employee("Cara", "Sales", 110_000)
        );
        employees.stream()
                .sorted(byDeptThenSalary())
                .forEach(e -> IO.println(e.name() + " " + e.department() + " " + e.salary()));
    }
}
