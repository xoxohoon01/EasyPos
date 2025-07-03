package app;

import controller.Controller;
import controller.MainMenuController;
import io.ConsoleInputProvider;
import io.ConsoleOutputRenderer;
import io.InputProvider;
import io.OutputRenderer;

public class Main
{
    public static void main(String[] args)
    {
        InputProvider input = new ConsoleInputProvider();
        OutputRenderer output = new ConsoleOutputRenderer();

        Controller controller = new MainMenuController(input, output);
        controller.run();
    }
}
