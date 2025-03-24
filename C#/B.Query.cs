using System;
using System.Collections.Generic;
using System.Linq;

namespace LINQ;
public record Person(string Name, int Age, string Email);

class Program3
{
    static void Main()
    {
        List<Person> people =
        [
            new("Alice", 30, "alice@example.com"),
            new("Bob", 25, "bob@example.com"),
            new("Charlie", 35, ""),
            new("Diana", 28, "diana@example.com")
        ];

        // LINQ query: Filter adults (>= 30), sort by name, select name and age
        var adults = from p in people
                     where p.Age >= 30
                     orderby p.Name
                     select new { p.Name, p.Age };

        // Or using method syntax:
        // var adults = people.Where(p => p.Age >= 30)
        //                    .OrderBy(p => p.Name)
        //                    .Select(p => new { p.Name, p.Age });

        foreach (var adult in adults)
        {
            Console.WriteLine($"{adult.Name}, {adult.Age}");
        }

        var ageGroups = from p in people
                        group p by p.Age / 10 * 10 into g
                        select new { AgeRange = $"{g.Key}-{g.Key + 9}", Count = g.Count() };

        foreach (var group in ageGroups)
        {
            Console.WriteLine($"{group.AgeRange}: {group.Count}");
        }
    }
}