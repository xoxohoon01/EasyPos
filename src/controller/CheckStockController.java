package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.ProductRepository;
import model.Stock;
import model.StockRepository;
import view.CheckStockView;

import java.util.ArrayList;
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
            List<Stock> stockList;

            switch (choice)
            {
                case "1":
                    view.promptShowStockList();
                    stockList = stockRepository.getStockList();
                    List<Stock> amountList = new ArrayList<Stock>();
                    if (!stockList.isEmpty()) // 재고 DB에 있는 데이터들 중 product_id가 같은 것들 합치기
                    {
                        for (int i = 0; i < stockList.size(); i++)
                        {
                            if (!amountList.isEmpty())
                            {
                                boolean isAdded = false;
                                for (int j = 0; j < amountList.size(); j++)
                                {
                                    if (amountList.get(j).getProduct_id() == stockList.get(i).getProduct_id())
                                    {
                                        amountList.get(j).addQuantity(stockList.get(i).getQuantity());
                                        isAdded = true;
                                        break;
                                    }
                                }
                                if (!isAdded)
                                {
                                    amountList.add(stockList.get(i));
                                }
                            }
                            else
                            {
                                amountList.add(stockList.get(i));
                            }
                        }
                    }

                    if (!amountList.isEmpty())
                    {
                        for (int i = 0; i < amountList.size(); i++)
                        {
                            view.showStockInfoByList(productRepository.getProductByID(amountList.get(i).getProduct_id()), amountList.get(i));
                        }

                        MessageBox.showEnterToContinue(input, output);
                    }
                    break;
                case "2":
                    view.promptInputProductID();
                    stockList = stockRepository.getStockList();
                    try
                    {
                        int targetID = Integer.parseInt(input.readLine());

                        if (targetID == 0) controller = new MainMenuController(input, output);

                        view.showProductInfo(productRepository.getProductByID(targetID));
                        if (!stockList.isEmpty())
                        {
                            for (int  i = 0; i < stockList.size(); i++)
                            {
                                if (stockList.get(i).getProduct_id() == targetID)
                                    view.showStockInfo(stockList.get(i));
                            }
                        }

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
