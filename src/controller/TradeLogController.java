package controller;

import io.InputProvider;
import io.OutputRenderer;
import view.TradeLogView;

public class TradeLogController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final TradeLogView view;

    public TradeLogController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new TradeLogView(output);
    }

    @Override
    public void run()
    {
        view.display();
    }


}
