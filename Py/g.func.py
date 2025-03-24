from typing import Callable

def calculate_price(base_price: float, discount: Callable[[float], float] = lambda x: x) -> float:
    # Pure function to calculate final price with an optional discount function.
    return discount(base_price)

# Usage
no_discount = calculate_price(100.0)  # Identity function (no change)
fixed_discount = calculate_price(100.0, lambda x: x * 0.9)  # 10% off
custom_discount = calculate_price(100.0, lambda x: x - 20 if x > 50 else x)  # $20 off if > $50

print(f"No discount: ${no_discount:.2f}")      # $100.00
print(f"10% off: ${fixed_discount:.2f}")       # $90.00
print(f"Custom discount: ${custom_discount:.2f}")  # $80.00