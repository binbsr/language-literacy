from typing import TypeVar, List, Callable

T = TypeVar('T')  # Generic type for items

def find_best(items: List[T], get_value: Callable[[T], float]) -> T:
    if not items:  # Python’s equivalent to null/empty check
        raise ValueError("List cannot be empty")
    return max(items, key=get_value)  # max with key mimics MaxBy

# Product class
class Product:
    def __init__(self, name: str, price: float):
        self.name = name
        self.price = price

    def __str__(self) -> str:
        return f"{self.name} (${self.price})"

# Employee class
class Employee:
    def __init__(self, name: str, salary: float):
        self.name = name
        self.salary = salary

    def __str__(self) -> str:
        return f"{self.name} (${self.salary})"

# Usage
products = [
    Product("Laptop", 999.99),
    Product("Phone", 699.99),
    Product("Headphones", 149.99)
]

best_product = find_best(products, lambda p: p.price)
print(f"Most expensive product: {best_product}")  # Most expensive product: Laptop ($999.99)

employees = [
    Employee("Alice", 75000),
    Employee("Bob", 82000),
    Employee("Charlie", 68000)
]

top_earner = find_best(employees, lambda e: e.salary)
print(f"Top earner: {top_earner}")  # Top earner: Bob ($82000)









from typing import Generic, TypeVar

T = TypeVar('T')  # T is a placeholder for any type

class Container(Generic[T]):
    def __init__(self, item: T) -> None:
        self.item = item
    
    def get_item(self) -> T:
        return self.item

# Usage
int_container = Container[int](42)        # Holds an integer
str_container = Container[str]("hello")   # Holds a string

print(int_container.get_item())  # Outputs: 42
print(str_container.get_item())  # Outputs: hello

# This is fine at runtime, but mypy would flag it as an error
wrong_container = Container[int]("not an int")