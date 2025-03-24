package Java;

public record Person(String name, int age, String email) {
    
    public Person(String name, int age) {
        this(name, age, "");
    }
    
    @Override
    public String toString() {
        return String.format("%s, %d, %s", name, age, email);
    }
}

// Usage
class Main2 {
    public static void main(String[] args) {
        var person1 = new Person("Alice", 30);
        Person person2 = new Person("Alice", 30);
        Person person3 = new Person("Bob", 25, "bob@example.com");

        System.out.println(person1);         // Alice, 30, 
        System.out.println(person1 == person2); // True (value equality)
        System.out.println(person3.email()); // bob@example.com

        // No built-in immutable update mechanism
    }
}
