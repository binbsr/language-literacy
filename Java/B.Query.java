package Java;

import java.util.*;
import java.util.stream.*;

record Person(String name, int age, String email) {
}

class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "alice@example.com"),
                new Person("Bob", 25, "bob@example.com"),
                new Person("Charlie", 35, ""),
                new Person("Diana", 28, "diana@example.com"));

        // Stream query: Filter adults (>= 30), sort by name, select name and age
        var adults = people.stream()
                .filter(p -> p.age() >= 30)
                .sorted(Comparator.comparing(Person::name))
                .map(p -> p.name() + ", " + p.age());

        adults.forEach(System.out::println);

        // 20-29: 2
        // 30-39: 2
        
        var ageGroups = people.stream()
                .collect(Collectors.groupingBy(
                        p -> String.format("%d-%d", p.age() / 10 * 10, p.age() / 10 * 10 + 9),
                        Collectors.counting()));

        ageGroups.forEach((range, count) -> System.out.println(range + ": " + count));
    }
}