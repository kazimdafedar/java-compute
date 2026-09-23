package production;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * File 04 — J16: Streams interview recipes
 */
class StreamsRecipes {

    record Employee(String name, String department, int salary) {}

    void main() {
        List<Employee> employees = List.of(
                new Employee("Ada", "Eng", 120_000),
                new Employee("Bob", "Eng", 95_000),
                new Employee("Cara", "Sales", 110_000)
        );

        List<Employee> topSalaries = employees.stream()
                .sorted(Comparator.comparingInt(Employee::salary).reversed())
                .limit(2)
                .toList();
        IO.println("top salaries: " + topSalaries);

        Map<String, List<Employee>> byDept = employees.stream()
                .collect(groupingBy(Employee::department));
        IO.println("by dept: " + byDept);

        Map<String, Integer> nameToSalary = employees.stream()
                .collect(toMap(Employee::name, Employee::salary, (a, b) -> a));
        IO.println("name→salary: " + nameToSalary);

        List<String> uniqueDepts = employees.stream()
                .map(Employee::department)
                .distinct()
                .toList();
        IO.println("unique depts: " + uniqueDepts);

        List<List<String>> batches = List.of(List.of("a", "b"), List.of("c"));
        List<String> flat = batches.stream().flatMap(List::stream).toList();
        IO.println("flatMap: " + flat);
    }
}
