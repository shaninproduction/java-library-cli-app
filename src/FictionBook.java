import java.util.Scanner;

public class FictionBook extends Book{
    private String theme;

    public FictionBook(String author, String name, int year, String theme) {
        super(author, name, year);
        this.theme = theme;
    }

    public FictionBook() {
        super();
        this.theme = null;
    }

    @Override
    public void readBook() {
        super.readBook();

        System.out.print("Введите тему книги: ");
        Scanner console = new Scanner(System.in);
        this.theme = console.nextLine();
    }

    @Override
    public void printBook() {
        System.out.println("Жанр книги: Художественный");
        System.out.println("Тема: " + theme);
        super.printBook();
    }

    @Override
    public void printBook(String author) {
        System.out.println("Жанр книги: Художественный");
        System.out.println("Тема: " + theme);
        super.printBook(author);
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
