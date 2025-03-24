from dataclasses import dataclass
from itertools import groupby

@dataclass
class Person:
    name: str
    age: int
    email: str

# Data
people = [
    Person("Alice", 30, "alice@example.com"),
    Person("Bob", 25, "bob@example.com"),
    Person("Charlie", 35, ""),
    Person("Diana", 28, "diana@example.com")
]

# List comprehension: Filter adults (>= 30), sort by name, select name and age
adults = [(p.name, p.age) for p in sorted(people, key=lambda p: p.name) if p.age >= 30]

# Alternative using functional tools:
adults = list(map(lambda p: (p.name, p.age), 
         sorted(filter(lambda p: p.age >= 30, people), key=lambda p: p.name)))

for name, age in adults:
    print(f"{name}, {age}")

sorted_people = sorted(people, key=lambda p: p.age // 10 * 10)
age_groups = {f"{k}-{k+9}": len(list(g)) for k, g in groupby(sorted_people, key=lambda p: p.age // 10 * 10)}

for range, count in age_groups.items():
    print(f"{range}: {count}")
    