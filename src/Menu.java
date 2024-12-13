import java.util.Scanner;

public abstract class Menu {
    public static MainChoice mainChoice;
    private static AddBookChoice addBookChoice;
    private static FindBookChoice findBookChoice;
    private static PrintBookChoice printBookChoice;

    public enum MainChoice {
        ADD,
        FIND,
        DELETE,
        PRINT,
        EXIT
    }

    private enum AddBookChoice {
        SCIENCE,
        FICTION
    }

    private enum FindBookChoice {
        NAME,
        AUTHOR
    }

    private enum PrintBookChoice {
        AUTHOR,
        NAME
    }

    public static void printMainMenu() {
        System.out.println("Введите номер операции:");
        System.out.println("1. Добавить книги в библиотеку");
        System.out.println("2. Найти книгу");
        System.out.println("3. Удалить книгу");
        System.out.println("4. Вывести книги");
        System.out.println("5. Выход");

    }

    private static void printMenuAddBooks() {
        System.out.println("Выберите жанр книги:");
        System.out.println("1. Научная");
        System.out.println("2. Художественная");
    }

    private static void printMenuFindBooks() {
        System.out.println("Выберите по какому параметру произвести поиск книг:");
        System.out.println("1. Название книги");
        System.out.println("2. Автор");
    }

    private static void printMenuPrintBooks() {
        System.out.println("Выберите способ вывода списка книг:");
        System.out.println("1. По автору");
        System.out.println("2. По названиям, отсортированным по году создания");
    }


    public static <T extends Enum<T>> T readChoice(Class<T> enumType) {
        Scanner console = new Scanner(System.in);

        T choiceValue = null;
        boolean correctValue = false;

        T[] enumConstants = enumType.getEnumConstants();
        while (!correctValue) {
            try {
                System.out.print("Введите номер операции: ");
                String stringValue = console.nextLine();
                int intValue = Integer.parseInt(stringValue);

                if (intValue > enumConstants.length || intValue <= 0)
                    throw new ChoiceOutOfRange("Choice out of range");

                choiceValue = enumConstants[intValue - 1];
                correctValue = true;
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА: Введите валидный номер операции!");
            } catch (ChoiceOutOfRange e) {
                System.out.println("ОШИБКА: Номер операции выходит за пределы!");
            }
        }

        return choiceValue;
    }

    private static boolean addBooks(Library lib) {
        int count = inputCount();

        boolean status = true;
        while (count > 0 && status) {
            printMenuAddBooks();
            addBookChoice = readChoice(AddBookChoice.class);

            status = handleAddBooksChoice(lib);
            count--;
        }

        return status;
    }

    private static boolean findBook(Library lib) {
        printMenuFindBooks();
        findBookChoice = readChoice(FindBookChoice.class);

        return handleFindBooksChoice(lib);
    }

    private static boolean printBooks(Library lib) {
        printMenuPrintBooks();

        printBookChoice = readChoice(PrintBookChoice.class);
        return handlePrintBooksChoice(lib);
    }

    private static boolean deleteBook(Library lib) {
        lib.deleteBook();
        return true;
    }

    public static boolean handleMainChoice(Library lib) {
        return switch (mainChoice) {
            case ADD -> addBooks(lib);
            case FIND -> findBook(lib);
            case DELETE -> deleteBook(lib);
            case PRINT -> printBooks(lib);
            case EXIT -> false;
        };
    }

    private static boolean handleAddBooksChoice(Library lib) {
        return switch (addBookChoice) {
            case SCIENCE -> inputBooks(ScienceBook.class, lib);
            case FICTION -> inputBooks(FictionBook.class, lib);
        };
    }

    public static boolean handlePrintBooksChoice(Library lib) {
        return switch (printBookChoice) {
            case AUTHOR -> lib.printBooksAuthors();
            case NAME -> lib.printBooksName();
        };
    }

    public static boolean handleFindBooksChoice(Library lib) {
        return switch (findBookChoice) {
            case AUTHOR -> lib.findBookAuthor();
            case NAME -> lib.findBookName();
        };
    }

    private static int inputCount() {
        Scanner console = new Scanner(System.in);
        System.out.print("Введите кол-во книг, которые хотите добавить: ");

        boolean correctValue = false;
        int count = -1;
        while (!correctValue) {
            try {
                String tmp = console.nextLine();
                count = Integer.parseInt(tmp);

                if (count <= 0) {
                    throw new RuntimeException("ОШИБКА: Кол-во < 0");
                }
                correctValue = true;
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА: Невалидное значение количества");;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        return count;
    }

    private static <T extends Book> boolean inputBooks(Class<T> type, Library lib) {
            try {
                T book = type.getDeclaredConstructor().newInstance();
                book.readBook();
                lib.addBook(book);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        return true;
    }
}
