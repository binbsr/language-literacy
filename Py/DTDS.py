from enum import Enum
import sys

emailSent: bool = True

x1: int = sys.maxsize
x2: float = sys.float_info.max
x3: str = 'test'
x4: bytes = b'test'
x5: str | None = 'something'

info = f'{emailSent}, {x1}, {x2}, {x3}, {x4}, {x5}'
print(info)





# Collections: Sequences, Set, Maps
tuple: tuple[int, str, float] = (3, 'yes', 7.5)
numbers  = [[1, 2], [2, 3]]
names: list[str] = ['Bishnu', 'Brahma', 'Mahesh']
weights: set[float] = {23.4, 45.6}
people: dict[str, float] = {'bsr': 2.0}
x: list[int | str] = [3, 5, 'test', 'fun']


print(numbers)

class EmailType(Enum): 
    Internal = 1
    Confedential = 2
    Public = 3

# Funtions
def send_email(
    address: str | list[str],
    sender: str,
    cc: list[str] | None,
    subject: str = '',
    body: list[str] | None = None,
    email_type: EmailType = EmailType.Internal
) -> bool:
    # Send logic
    return emailSent


# Querying, Projections etc.
nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
result = [x**2 for x in nums if x % 2 == 0]
print(result)

