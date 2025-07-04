package controller;

import io.InputProvider;
import io.OutputRenderer;
import view.CheckStockView;

public class CheckStockController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final CheckStockView view;

    public CheckStockController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new CheckStockView(output);
    }

    @Override
    public void run()
    {
        view.display();
    }


}
