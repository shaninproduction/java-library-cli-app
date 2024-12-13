public class App {
    public static void start() {
        boolean running = true;

        Library lib = new Library();
        while (running)
        {
            Menu.printMainMenu();

            Menu.mainChoice = Menu.readChoice(Menu.MainChoice.class);
            running = Menu.handleMainChoice(lib);
        }
    }
}
