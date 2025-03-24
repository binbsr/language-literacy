class Book(string title, string author, int copies)
{
    private string _title = title;
    protected string _author = author;
    private int _copies = copies;
    private int _available = copies;

    // Encapsulation via properties
    public string Title => _title;  // Read-only property
    public int Available => _available;

    public virtual string Borrow(int numCopies = 1)
    {
        if (_available >= numCopies)
        {
            _available -= numCopies;
            return $"Borrowed {numCopies} copy(ies) of '{_title}'. {_available} left.";
        }
        return $"Not enough copies of '{_title}' available.";
    }

    public string ReturnBook(int numCopies = 1)
    {
        if (_available + numCopies <= _copies)
        {
            _available += numCopies;
            return $"Returned {numCopies} copy(ies) of '{_title}'. {_available} left.";
        }
        return "Cannot return more copies than total stock.";
    }

    public virtual string Info()
    {
        return $"Book: {_title} by {_author}, Available: {_available}/{_copies}";
    }
}

class DigitalBook(string title, string author, int copies, string downloadLink)
    : Book(title, author, copies)
{
    public string DownloadLink { get; } = downloadLink;

    public override string Borrow(int numCopies = 1)
        => $"Digital copy of '{Title}'. Download at: {DownloadLink}";

    public override string Info()
        => $"Digital Book: {Title} by {_author}, Link: {DownloadLink}";

    public bool CheckoutBook(string bookId)
    {
        Console.WriteLine($"Checked out book {bookId} with default 14-day period");
        return true;
    }

    public bool CheckoutBook(string bookId, string dueDate)
    {
        Console.WriteLine($"Checked out book {bookId} until {dueDate}");
        return true;
    }

    public bool CheckoutBook(string bookId, string dueDate, string memberId)
    {
        Console.WriteLine($"Checked out book {bookId} to member {memberId} until {dueDate}");
        return true;
    }
}

partial class Program
{
    static void Main()
    {
        Book book = new("The Great Gatsby", "F. Scott Fitzgerald", 5);
        DigitalBook ebook = new("Python 101", "John Doe", 10, "http://example.com/python101");

        Console.WriteLine(book.Info());
        Console.WriteLine(book.Borrow(2));
        Console.WriteLine(book.Info());
        Console.WriteLine(book.ReturnBook(1));
        Console.WriteLine();

        Console.WriteLine(ebook.Info());
        Console.WriteLine(ebook.Borrow());
    }
}
