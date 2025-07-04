package controller;

import io.InputProvider;
import io.OutputRenderer;
import view.RegisterStockView;

public class RegisterStockController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final RegisterStockView view;

    public RegisterStockController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new RegisterStockView(output);
    }

    @Override
    public void run()
    {
        view.displayBanner();
        view.displayMenu();
        view.displayLast();
    }


}
