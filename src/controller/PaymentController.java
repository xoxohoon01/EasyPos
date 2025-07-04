package controller;

import io.InputProvider;
import io.OutputRenderer;
import view.PaymentView;

public class PaymentController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final PaymentView view;

    public PaymentController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new PaymentView(output);
    }

    @Override
    public void run()
    {
        view.displayBanner();
        view.displayMenu();
        view.displayLast();
    }


}
