package gui;
import database.Database;

public class App {
    public Database dbs = Database.getInstance();
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Window simpleWindow = new SimpleWindow();
        StartScreen screen = new StartScreen(simpleWindow);
        screen.display();
    }
}
