using System;
using System.Collections.Generic;
using System.Linq;

class Program3
{
    static T FindBest<T>(List<T> items, Func<T, double> getValue)
    {
        if (items == null || items.Count == 0)
            throw new ArgumentException("List cannot be empty or null");
        return items.MaxBy(getValue);  // LINQ’s MaxBy is perfect here
    }

    // Example: Products with prices
    class Product
    {
        public string Name { get; }
        public double Price { get; }

        public Product(string name, double price)
        {
            Name = name;
            Price = price;
        }

        public override string ToString() => $"{Name} (${Price})";
    }

    // Example: Employees with salaries
    class Employee
    {
        public string Name { get; }
        public double Salary { get; }

        public Employee(string name, double salary)
        {
            Name = name;
            Salary = salary;
        }

        public override string ToString() => $"{Name} (${Salary})";
    }

    static void Main5()
    {
        var products = new List<Product>
        {
            new Product("Laptop", 999.99),
            new Product("Phone", 699.99),
            new Product("Headphones", 149.99)
        };

        // Usage
        var bestProduct = FindBest(products, p => p.Price);
        Console.WriteLine($"Most expensive product: {bestProduct}");  // Most expensive product: Laptop ($999.99)

        var employees = new List<Employee>
        {
            new Employee("Alice", 75000),
            new Employee("Bob", 82000),
            new Employee("Charlie", 68000)
        };

        var topEarner = FindBest(employees, e => e.Salary);
        Console.WriteLine($"Top earner: {topEarner}");  // Top earner: Bob ($82000)
    }
}









class Container<T>(T item)
{
    private T item = item;

    public T GetItem() => item;
}

class Program6
{
    static void Main()
    {
        var intContainer = new Container<int>(42);
        var strContainer = new Container<string>("hello");

        Console.WriteLine(intContainer.GetItem());  // Outputs: 42
        Console.WriteLine(strContainer.GetItem());  // Outputs: hello

        // This won’t compile: type mismatch
        // var wrongContainer = new Container<int>("not an int");
    }
}