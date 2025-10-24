import java.util.ArrayList;
import java.util.Scanner;

// Represents a single book with a title, author, and availability status
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // New books are available by default
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Title: \"" + title + "\", Author: " + author + ", Status: " + (isAvailable ? "Available" : "Issued");
    }
}

// Manages the collection of books in the library
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Successfully added: " + book.getTitle());
    }

    public void showAllBooks() {
        System.out.println("\n--- Library Catalog ---");
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            // Case-insensitive search
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

// The main class that runs the user interface for the library system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding some initial books to the library
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen"));
        library.addBook(new Book("1984", "George Orwell"));

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add a new book");
            System.out.println("2. Issue a book");
            System.out.println("3. Return a book");
            System.out.println("4. Show all books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    System.out.print("Enter the title of the book to issue: ");
                    String issueTitle = scanner.nextLine();
                    Book bookToIssue = library.findBook(issueTitle);
                    if (bookToIssue != null && bookToIssue.isAvailable()) {
                        bookToIssue.borrowBook();
                        System.out.println("You have successfully issued \"" + bookToIssue.getTitle() + "\".");
                    } else if (bookToIssue != null && !bookToIssue.isAvailable()) {
                        System.out.println("Sorry, that book is already issued.");
                    } else {
                        System.out.println("Book not found in the library.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    Book bookToReturn = library.findBook(returnTitle);
                    if (bookToReturn != null && !bookToReturn.isAvailable()) {
                        bookToReturn.returnBook();
                        System.out.println("Thank you for returning \"" + bookToReturn.getTitle() + "\".");
                    } else if (bookToReturn != null && bookToReturn.isAvailable()){
                        System.out.println("This book is already marked as available.");
                    } else {
                        System.out.println("Book not found in the library.");
                    }
                    break;
                case 4:
                    library.showAllBooks();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }
}