package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.Product;
import model.ProductRepository;
import view.OrderView;

import java.util.ArrayList;
import java.util.List;

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
        ProductRepository productRepository = new ProductRepository();
        List<Product> orderList = new ArrayList<Product>();

        while (true)
        {
            view.displayBanner();
            view.promptOrderList(orderList);
            view.displayMenu();
            view.displayLast();

            Controller controller = null;
            String choice = input.readLine();

            switch (choice)
            {
                case "1":
                    // 상품 입력
                    view.promptInputProduct();

                    Product newProduct = productRepository.getProductByID(Integer.parseInt(input.readLine()));
                    if (newProduct != null)
                    {
                        orderList.add(newProduct);
                    }
                    else
                    {
                        MessageBox.showWarningByWrongInput(input, output);
                    }
                    break;
                case "2":
                    // 상품 조회
                    view.promptProductList();

                    List<Product> productList = productRepository.getProductList();
                    for (int i = 0; i < productList.size(); i++)
                    {
                        Product targetProduct = productList.get(i);
                        view.showProductInfo(targetProduct);
                    }
                    MessageBox.showEnterToContinue(input, output);
                    break;
                case "3":
                    // 발주 결정
                    break;
                case "4":
                    // 취소
                    controller = new MainMenuController(input, output);
                    break;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    break;
            }
        }
    }


}
