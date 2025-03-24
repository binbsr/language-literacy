from dataclasses import dataclass

@dataclass(frozen=True)
class Person:
    name: str
    age: int
    email: str = "" 

# Usage
person1 = Person("Alice", 30)
person2 = Person("Alice", 30)
person3 = Person("Bob", 25, "bob@example.com")

print(person1)          # Person(name='Alice', age=30, email='')
print(person1 == person2)  # True (equality based on fields)
print(person3.email)    # bob@example.com

# person1.name = "Eve"  # Raises FrozenInstanceError due to frozen=True