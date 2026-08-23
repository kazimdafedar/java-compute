package com.kazim.javacompute.examples;

import com.kazim.javacompute.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GroupByAgeExample {

    private GroupByAgeExample() {
    }

    public static Map<Integer, List<Person>> groupByAge(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::age));
    }

    public static List<Person> sortByAgeThenNameDescending(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::age)
                        .reversed()
                        .thenComparing(Person::name, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Kazim", 30),
                new Person("Dafedar", 31),
                new Person("Shirin", 25),
                new Person("Haider", 25)
        );

        System.out.println(groupByAge(people));
        System.out.println(sortByAgeThenNameDescending(people));
    }
}
