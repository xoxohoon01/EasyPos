package controller;

import app.Main;
import io.InputProvider;
import io.OutputRenderer;
import model.Product;
import model.ProductRepository;
import model.Stock;
import model.StockRepository;
import view.RegisterStockView;

import java.util.ArrayList;
import java.util.List;

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
        while (true)
        {
            view.displayBanner();
            view.displayMenu();
            view.displayLast();

            StockRepository stockRepository = new StockRepository();
            Controller controller = null;

            String choice = input.readLine();
            int targetProduct_id, targetQuantity, targetDelivery_id;
            switch (choice)
            {
                case "1":
                    view.promptInputProduct();
                    targetProduct_id = Integer.parseInt(input.readLine());
                    view.promptInputProductAmount();
                    targetQuantity = Integer.parseInt(input.readLine());

                    if (targetQuantity >= 10)
                    {
                        stockRepository.registerStockByProductId(targetProduct_id, targetQuantity);
                    }
                    else
                    {
                        view.promptNotEnoughAmount();
                    }
                    break;
                case "2":
                    view.promptInputDelivery();
                    targetDelivery_id = Integer.parseInt(input.readLine());

                    stockRepository.registerStockByDeliveryId(targetDelivery_id);
                    break;
                case "3":
                    controller = new MainMenuController(input, output);
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
