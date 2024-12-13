import java.time.Year;
import java.util.Scanner;

public abstract class Book {
    private String author;
    private String name;
    private int year;

    public Book(String author, String name, int year) {
        this.author = author;
        this.name = name;
        this.year = year;
    }

    public Book() {
        this.author = null;
        this.name = null;
        this.year = 0;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void readBook() {
        Scanner console = new Scanner(System.in);

        System.out.print("Введите название книги: ");
        this.name = console.nextLine();

        System.out.print("Введите автора книги: ");
        this.author = console.nextLine();

        boolean correctValue = false;
        while (!correctValue) {
            try {
                System.out.print("Введите год создания книги: ");
                String tmp = console.nextLine();
                this.year = Integer.parseInt(tmp);
                if (this.year > Year.now().getValue()) {
                    throw new InvalidBookException("ОШИБКА: Год больше текущего");
                }

                if (this.year < 0) {
                    throw new InvalidBookException("ОШИБКА: Год меньше нуля");
                }

                correctValue = true;
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА: Введите валидный год создания книги");
            } catch (InvalidBookException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj.getClass() != this.getClass())
            return false;

        Book other = (Book) obj;

        return  other.year == this.year
                && other.author.equals(this.author)
                && other.name.equals(this.name);
    }

    @Override
    public String toString() {
        return  "Автор: " + author + "\n"+
                "Название: " + name + "\n" +
                "Год создания: " + year + "\n";
    }

    public String toString(String name, int year) {
        return  "Название: " + name + "\n" +
                "Год создания: " + year + "\n";
    }

    public void printBook() {
        System.out.println(this);
    }

    public void printBook(String author) {
        System.out.println(toString(name, year));
    }
}
