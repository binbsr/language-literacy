class Book:
    def __init__(self, title, author, copies):
        self._title = title  # Protected
        self._author = author
        self.__copies = copies  # Private
        self.__available = copies

    # Encapsulation: Getters and setters
    def get_title(self):
        return self._title

    def get_available(self):
        return self.__available

    def borrow(self, num_copies=1):
        if self.__available >= num_copies:
            self.__available -= num_copies
            return f"Borrowed {num_copies} copy(ies) of '{self._title}'. {self.__available} left."
        return f"Not enough copies of '{self._title}' available."
    
    def return_book(self, num_copies=1):
        if self.__available + num_copies <= self.__copies:
            self.__available += num_copies
            return f"Returned {num_copies} copy(ies) of '{self._title}'. {self.__available} left."
        return "Cannot return more copies than total stock."

    def info(self):  # Polymorphism: to be overridden
        return f"Book: {self._title} by {self._author}, Available: {self.__available}/{self.__copies}"

class DigitalBook(Book):
    def __init__(self, title, author, copies, download_link):
        super().__init__(title, author, copies)
        self.download_link = download_link

    def borrow(self, num_copies=1):  # Override for digital logic
        return f"Digital copy of '{self.get_title()}'. Download at: {self.download_link}"

    def info(self):  # Override
        return f"Digital Book: {self.get_title()} by {self._author}, Link: {self.download_link}"

    def checkout_book(self, book_id, due_date=None, member_id=None):
        if not due_date and not member_id:
            print(f"Checked out book {book_id} with default 14-day period")
            return True
        elif due_date and not member_id:
            print(f"Checked out book {book_id} until {due_date}")
            return True
        elif due_date and member_id:
            print(f"Checked out book {book_id} to member {member_id} until {due_date}")
            return True
        return False
# Usage
def main():
    book = Book("The Great Gatsby", "F. Scott Fitzgerald", 5)
    ebook = DigitalBook("Python 101", "John Doe", 10, "http://example.com/python101")

    print(book.info())
    print(book.borrow(2))
    print(book.info())
    print(book.return_book(1))
    print()

    print(ebook.info())
    print(ebook.borrow())  # Digital book doesn't reduce copies

if __name__ == "__main__":
    main()