public record Person(string Name, int Age, string Email = "")
{    
    public override string ToString() => $"{Name}, {Age}, {Email}";
}

// Usage
class Program2
{
    static void Main()
    {
        var person1 = new Person("Alice", 30);
        var person2 = new Person("Alice", 30);
        var person3 = new Person("Bob", 25, "bob@example.com");

        Console.WriteLine(person1);         // Alice, 30, 
        Console.WriteLine(person1 == person2); // True (value equality)
        Console.WriteLine(person3.Email);   // bob@example.com

        // With-expression for immutable updates
        var person4 = person1 with { Age = 31 };
        Console.WriteLine(person4);         // Alice, 31, 
    }
}