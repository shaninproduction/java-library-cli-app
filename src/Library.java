import java.util.*;

public class Library {
    static int booksCount = 0;
    private final Map<String, List<Book>> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book)
    {
        if (books.containsKey(book.getAuthor())) {
            List<Book> authorBooks = books.get(book.getAuthor());
            authorBooks.add(book);
        } else {
            List<Book> authorBooks = new ArrayList<>();
            authorBooks.add(book);
            books.put(book.getAuthor(), authorBooks);
        }

        booksCount++;
    }

    public boolean findBookAuthor() {
        if (booksCount == 0) {
            System.out.println("В библиотеке нет книг!");
            return true;
        }

        Scanner console = new Scanner(System.in);

        System.out.print("Введите автора книги: ");
        String author = console.nextLine();

        if (books.containsKey(author)) {
            List<Book> authorBooks = books.get(author);

            System.out.println("Найденные книги автора:");
            for (Book book : authorBooks) {
                book.printBook(author);
            }
        } else {
            System.out.println("Книг по данному автору не найдено!");
        }

        return true;
    }

    public boolean findBookName() {
        if (booksCount == 0) {
            System.out.println("В библиотеке нет книг!");
            return true;
        }

        Scanner console = new Scanner(System.in);

        System.out.print("Введите название книги: ");
        String name = console.nextLine();

        boolean found = false;

        System.out.println("Найденные книги:");
        for (List<Book> curBooks : books.values()) {
            for (Book book : curBooks) {
                if (book.getName().equals(name)) {
                    book.printBook();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Не найдено книг с таким названием!");
        }

        return true;
    }

    public boolean printBooksAuthors() {
        if (booksCount == 0) {
            System.out.println("В библиотеке нет книг!");
            return true;
        }

        for (Map.Entry<String, List<Book>> elem : books.entrySet()) {
            String author = elem.getKey();
            List<Book> books = elem.getValue();

            System.out.println("Автор: " + author);
            for (Book book : books) {
                book.printBook(author);
            }
        }

        return true;
    }

    private void sortByYear(List<Book> books) {
        Comparator<Book> yearComparator = new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getYear(), b2.getYear());
            }
        };

        books.sort(yearComparator);
    }

    public boolean printBooksName() {
        if (booksCount == 0) {
            System.out.println("В библиотеке нет книг!");
            return true;
        }

        List<Book> temp_books = new ArrayList<>();
        for (List<Book> elem : books.values()) {
            temp_books.addAll(elem);
        }

        sortByYear(temp_books);

        System.out.println("Список книг библиотеки, отсортированных по году создания:");
        for (Book book : temp_books) {
            book.printBook();
        }

        return true;
    }

    public void deleteBook() {
        if (booksCount == 0) {
            System.out.println("В библиотеке нет книг!");
            return;
        }

        Scanner console = new Scanner(System.in);

        System.out.print("Введите название книги: ");
        String name = console.nextLine();

        for (List<Book> curBooks : books.values()) {
            for (Book book : curBooks) {
                if (book.getName().equals(name)) {
                    System.out.println("Удаленная книга:");
                    book.printBook();
                    curBooks.remove(book);
                    booksCount--;
                    return;
                }
            }
        }
    }
}
