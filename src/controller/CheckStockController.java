package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.ProductRepository;
import model.Stock;
import model.StockRepository;
import view.CheckStockView;

import java.util.List;

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
        while (true)
        {
            view.displayBanner();
            view.displayMenu();
            view.displayLast();

            ProductRepository productRepository = new ProductRepository();
            StockRepository stockRepository = new StockRepository();

            Controller controller = null;
            String choice = input.readLine();
            switch (choice)
            {
                case "1":
                    view.promptShowStockList();
                    List<Stock> stockList = stockRepository.getStockList();
                    if (!stockList.isEmpty())
                    {
                        for (int i = 0; i < stockList.size(); i++)
                        {
                            view.showStockInfoByList(productRepository.getProductByID(stockList.get(i).getProduct_id()), stockList.get(i));
                        }
                        MessageBox.showEnterToContinue(input, output);
                    }
                    break;
                case "2":
                    view.promptInputProductID();
                    try
                    {
                        int targetID = Integer.parseInt(input.readLine());

                        if (targetID == 0) controller = new MainMenuController(input, output);

                        view.showProductInfo(productRepository.getProductByID(targetID));
                        view.showStockInfo(stockRepository.getStock(targetID));
                        MessageBox.showEnterToContinue(input, output);
                    }
                    catch (NumberFormatException e)
                    {
                        MessageBox.showWarningByWrongNumber(input, output);
                    }
                    break;
                case "3":
                    controller = new MainMenuController(input, output);
                    break;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    break;
            }

            if (controller != null)
            {
                controller.run();
                break;
            }
        }
    }

}
