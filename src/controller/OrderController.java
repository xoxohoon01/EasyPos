package controller;

import io.InputProvider;
import io.OutputRenderer;
import view.OrderView;

public class OrderController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final OrderView view;

    public OrderController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new OrderView(output);
    }

    @Override
    public void run()
    {
        view.display();
    }


}
