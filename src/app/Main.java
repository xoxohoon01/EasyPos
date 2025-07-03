package app;

import controller.Controller;
import controller.LoginStoreController;
import io.ConsoleInputProvider;
import io.ConsoleOutputRenderer;
import io.InputProvider;
import io.OutputRenderer;
import model.Staff;
import model.Store;

public class Main
{
    public static Store store;
    public static Staff staff;

    public static void main(String[] args)
    {
        InputProvider input = new ConsoleInputProvider();
        OutputRenderer output = new ConsoleOutputRenderer();

        Controller controller = new LoginStoreController(input, output);
        controller.run();
    }
}
