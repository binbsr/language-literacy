package Java;

import java.util.List;
import java.util.Arrays;
import java.util.function.Function;

public class Generics {
    public static <T> T findBest(List<T> items, Function<T, Double> getValue) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty or null");
        }
        return items.stream()
                    .max((a, b) -> Double.compare(getValue.apply(a), getValue.apply(b)))
                    .get();  // Stream’s max mimics MaxBy
    }

    // Product class
    static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getPrice() { return price; }
        @Override public String toString() { return name + " ($" + price + ")"; }
    }

    // Employee class
    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() { return salary; }
        @Override public String toString() { return name + " ($" + salary + ")"; }
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99),
            new Product("Phone", 699.99),
            new Product("Headphones", 149.99)
        );

        Product bestProduct = findBest(products, Product::getPrice);  // Method reference like C# lambda
        System.out.println("Most expensive product: " + bestProduct);  // Most expensive product: Laptop ($999.99)

        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 75000),
            new Employee("Bob", 82000),
            new Employee("Charlie", 68000)
        );

        Employee topEarner = findBest(employees, Employee::getSalary);
        System.out.println("Top earner: " + topEarner);  // Top earner: Bob ($82000)
    }
}







class Container<T> {
    private T item;

    public Container(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public static void main(String[] args) {
        var intContainer = new Container<>(42);      // Holds an Integer
        var strContainer = new Container<>("hello");  // Holds a String

        System.out.println(intContainer.getItem());  // Outputs: 42
        System.out.println(strContainer.getItem());  // Outputs: hello

        // This won’t compile: type mismatch
        // Container<Integer> wrongContainer = new Container<>("not an int");
    }
}