package Java;

class Book {
    private String title;
    protected String author;
    private int copies;
    private int available;

    public Book(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.available = copies;
    }

    // Encapsulation: Getters
    public String getTitle() {
        return title;
    }

    public int getAvailable() {
        return available;
    }

    public String borrow(int numCopies) {
        if (available >= numCopies) {
            available -= numCopies;
            return "Borrowed " + numCopies + " copy(ies) of '" + title + "'. " + available + " left.";
        }
        return "Not enough copies of '" + title + "' available.";
    }

    public String returnBook(int numCopies) {
        if (available + numCopies <= copies) {
            available += numCopies;
            return "Returned " + numCopies + " copy(ies) of '" + title + "'. " + available + " left.";
        }
        return "Cannot return more copies than total stock.";
    }

    public String info() {
        return "Book: " + title + " by " + author + ", Available: " + available + "/" + copies;
    }
}

class DigitalBook extends Book {
    private String downloadLink;

    public DigitalBook(String title, String author, int copies, String downloadLink) {
        super(title, author, copies);
        this.downloadLink = downloadLink;
    }

    @Override
    public String borrow(int numCopies) {
        return "Digital copy of '" + getTitle() + "'. Download at: " + downloadLink;
    }

    @Override
    public String info() {
        return "Digital Book: " + getTitle() + " by " + author + ", Link: " + downloadLink;
    }

    public boolean checkoutBook(String bookId) {
        System.out.println("Checked out book " + bookId + " with default 14-day period");
        return true;
    }

    public boolean checkoutBook(String bookId, String dueDate) {
        System.out.println("Checked out book " + bookId + " until " + dueDate);
        return true;
    }

    public boolean checkoutBook(String bookId, String dueDate, String memberId) {
        System.out.println("Checked out book " + bookId + " to member " + memberId + " until " + dueDate);
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 5);
        DigitalBook ebook = new DigitalBook("Python 101", "John Doe", 10, "http://example.com/python101");

        System.out.println(book.info());
        System.out.println(book.borrow(2));
        System.out.println(book.info());
        System.out.println(book.returnBook(1));
        System.out.println();

        System.out.println(ebook.info());
        System.out.println(ebook.borrow(1));
    }
}