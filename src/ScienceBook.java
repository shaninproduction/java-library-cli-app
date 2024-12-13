import java.util.Scanner;

public class ScienceBook extends Book {
    private String science;

    public ScienceBook(String author, String name, int year, String science) {
        super(author, name, year);
        this.science = science;
    }

    public ScienceBook() {
        super();
        this.science = null;
    }

    @Override
    public void readBook() {
        super.readBook();

        System.out.print("Введите раздел науки этой книги: ");
        Scanner console = new Scanner(System.in);
        this.science = console.nextLine();
    }

    @Override
    public void printBook() {
        System.out.println("Жанр книги: Научный");
        System.out.println("Раздел науки: " + science);
        super.printBook();
    }

    @Override
    public void printBook(String author) {
        System.out.println("Жанр книги: Научный");
        System.out.println("Раздел науки: " + science);
        super.printBook(author);
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }
}
