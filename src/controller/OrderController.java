package controller;

import app.Main;
import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.Order;
import model.OrderRepository;
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
        List<Order> orderList = new ArrayList<Order>();

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

                    Product newProduct = null;
                    try
                    {
                        newProduct = productRepository.getProductByID(Integer.parseInt(input.readLine()));
                    } catch (NumberFormatException e)
                    {
                        MessageBox.showWarningByWrongNumber(input, output);
                        break;
                    }
                    if (newProduct != null)
                    {
                        view.promptInputProductAmount();
                        int amount = 0;
                        try
                        {
                            amount = Integer.parseInt(input.readLine());
                        } catch (NumberFormatException e)
                        {
                            MessageBox.showWarningByWrongNumber(input, output);
                            break;
                        }
                        boolean isRegisteredOrder = false; // 발주 예정품목 등록 여부
                        if (!orderList.isEmpty())
                        {
                            for (int i = 0; i < orderList.size(); i++)
                            {
                                if (orderList.get(i).getProduct_id() == newProduct.getProduct_id())
                                {
                                    orderList.get(i).addQuantity(amount);
                                    isRegisteredOrder = true;
                                    break;
                                }
                            }
                        }

                        // 발주 예정품목이 아닌 경우
                        if (!isRegisteredOrder)
                        {
                            Order newOrder = new Order(Main.store.getStore_id(), newProduct.getProduct_id(), amount);
                            orderList.add(newOrder);
                        }
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
                    view.promptCheckout();

                    String confirm = input.readLine();

                    switch (confirm)
                    {
                        case "Y":
                            OrderRepository orderRepository = new OrderRepository();
                            orderRepository.registerOrder(orderList);
                            view.promptDone();
                            orderList.clear();
                            break;
                        case "N":
                            break;
                        default:
                            MessageBox.showWarningByWrongInput(input, output);
                            break;
                    }
                    break;
                case "4":
                    // 취소
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
