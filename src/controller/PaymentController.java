package controller;

import app.Main;
import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.*;
import view.PaymentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        StockRepository stockRepository = new StockRepository();
        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository = new SaleRepository();

        List<Stock> targetStockList = new ArrayList<Stock>();
        while (true)
        {
            view.displayBanner();
            if (!targetStockList.isEmpty())
            {
                int price = 0;
                for (int i = 0; i < targetStockList.size(); i++)
                {
                    view.showStockList(targetStockList.get(i));
                    price += productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity();
                }
                view.promptStockList(price);
            }
            view.displayMenu();
            view.displayLast();

            Controller controller = null;

            String choice = input.readLine();
            switch (choice)
            {
                case "1": // 상품 선택
                    // 1. StockList 보여주고, 번호 선택
                    // 2. 수량 선택해서 Stock 만들고 targetStockList에 추가
                    List<Stock> stockList = stockRepository.getStockList();
                    if (!stockList.isEmpty()) // Try-catch문 null값 캐치, 숫자 아닐 경우 캐치
                    {
                        for (int i = 0; i < stockList.size(); i++)
                        {
                            view.showStockList(stockList.get(i));
                        }
                        view.promptInputProduct();
                        int targetStock_id = Integer.parseInt(input.readLine());

                        Stock targetStock = stockRepository.getStockByStockId(targetStock_id); // null값 캐치

                        view.promptInputProductQuantity();
                        int targetQuantity = Integer.parseInt(input.readLine());

                        // 수량 제한
                        boolean isEnough = true;
                        if (!targetStockList.isEmpty()) // targetStockList가 있을 경우, targetStockList의 수량 + 입력 수량이 재고량보다 많을 경우
                        {
                            for (int i = 0; i < targetStockList.size(); i++) // targetStoc
                            {
                                if (targetStockList.get(i).getStock_id() == targetStock_id)
                                {
                                    if (stockRepository.getStockByStockId(targetStock_id).getQuantity() < targetStockList.get(i).getQuantity() + targetQuantity)
                                    {
                                        isEnough = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (isEnough) // targetStockList에 없을 경우, 입력 수량이 재고량보다 많을 경우
                        {
                            if (targetStock.getQuantity() < targetQuantity)
                                isEnough = false;
                        }
                        if (!isEnough)
                        {
                            view.promptNotEnoughQuantity();
                            MessageBox.showEnterToContinue(input, output);
                            break;
                        }

                        if (!targetStockList.isEmpty()) // 결제 항목의 중복 체크
                        {
                            boolean isAdded = false;
                            for (int i = 0; i < targetStockList.size(); i++)
                            {
                                if (targetStockList.get(i).getStock_id() == targetStock_id)
                                {
                                    targetStockList.get(i).setQuantity(targetStockList.get(i).getQuantity() + targetQuantity);

                                    isAdded = true;
                                }
                            }

                            if (!isAdded)
                            {
                                Stock newStock = new Stock(targetStock_id, Main.store.getStore_id(), targetStock.getProduct_id(), targetQuantity, targetStock.getRegistered_date(), targetStock.getExpiration_date());

                                targetStockList.add(newStock);
                            }
                        }
                        else
                        {
                            Stock newStock = new Stock(targetStock_id, Main.store.getStore_id(), targetStock.getProduct_id(), targetQuantity, targetStock.getRegistered_date(), targetStock.getExpiration_date());

                            targetStockList.add(newStock);
                        }
                    }
                    break;

                case "2": // 수량 변경
                    if (!targetStockList.isEmpty())
                    {
                        int price = 0;
                        for (int i = 0; i < targetStockList.size(); i++)
                        {
                            view.showStockList(targetStockList.get(i));
                            price += productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity();
                        }
                        view.promptStockList(price);

                        // 상품 선택 및 수량 입력
                        view.promptInputTargetStockToChangeQuantity();
                        int targetStockId = Integer.parseInt(input.readLine());
                        view.promptInputProductQuantity();
                        int targetQuantity = Integer.parseInt(input.readLine());

                        boolean isChanged = false;
                        for (int i = 0; i < targetStockList.size(); i++)
                        {
                            if (stockRepository.getStockByStockId(targetStockId).getQuantity() >= targetQuantity)
                            {
                                targetStockList.get(i).setQuantity(targetQuantity);
                                isChanged = true;
                                break;
                            }
                        }

                        if (!isChanged)
                        {
                            view.promptNotEnoughQuantity();
                            MessageBox.showEnterToContinue(input, output);
                        }
                    }

                    // 1. targetStockList 보여주고, 번호 선택
                    // 2. 수량 선택하면 해당 Stock의 quantity 조절
                    break;
                case "3": // 상품 제거
                    if (!targetStockList.isEmpty())
                    {
                        int price = 0;
                        for (int i = 0; i < targetStockList.size(); i++)
                        {
                            view.showStockList(targetStockList.get(i));
                            price += productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity();
                        }
                        view.promptStockList(price);

                        // 상품 선택 및 수량 입력
                        view.promptInputTargetStockToRemove();
                        int targetStockId = Integer.parseInt(input.readLine());

                        boolean isRemoved = false;
                        for (int i = 0; i < targetStockList.size(); i++)
                        {
                            if (targetStockList.get(i).getStock_id() == targetStockId)
                            {
                                targetStockList.remove(targetStockList.get(i));
                                break;
                            }
                        }
                    }
                    // 1. targetStockList 보여주고, 번호 선택
                    // 2. targetStockList에서 제거
                    break;
                case "4": // 결제
                    boolean hasAdult = false;
                    int price = 0, extraCash = 0, customerCash = 0;
                    for (int i = 0; i < targetStockList.size(); i++)
                    {
                        price += productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity();
                        if (productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getIsAdult() == "Y")
                        {
                            hasAdult = true;
                        }
                    }

                    if (hasAdult)
                    {
                        view.promptAdult();
                    }

                    view.promptPayment(price);
                    if (!targetStockList.isEmpty())
                    {
                        String payment = input.readLine();
                        switch (payment)
                        {
                            case "1": //현금
                                // 총액보다 넘게 현금 제시
                                // 0 ~ 5000원 사이 랜덤 추가금 (1000원 단위)
                                extraCash = (new Random().nextInt(6)) * 1000; // 0~5000원
                                customerCash = ((price + extraCash + 999) / 1000) * 1000; // price+추가금 올림해서 1000단위로 맞춤

                                // 결제 로직
                                for (int i = 0; i < targetStockList.size(); i++)
                                {
                                    stockRepository.decreaseStock(
                                            targetStockList.get(i).getStock_id(),
                                            targetStockList.get(i).getQuantity()
                                    );
                                    saleRepository.recordSale(
                                            targetStockList.get(i).getProduct_id(),
                                            targetStockList.get(i).getQuantity(),
                                            productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity()
                                            );
                                }
                                targetStockList.clear();
                                int change = customerCash - price;

                                Main.cash += price - change;
                                view.promptPaymentDone(customerCash, price);
                                MessageBox.showEnterToContinue(input, output);
                                break;
                            case "2": //카드
                                // 총액보다 넘는 카드이거나, 잔액 부족 표현
                                price = 0;
                                for (int i = 0; i < targetStockList.size(); i++)
                                {
                                    price += productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity();
                                }

                                // -20000 ~ +20000원 사이 랜덤 추가금
                                customerCash = price + (new Random().nextInt(2) * 10000) - (new Random().nextInt(2) * 10000); // -20000 ~ +20000원

                                if (customerCash > price)
                                {
                                    // 결제 로직
                                    for (int i = 0; i < targetStockList.size(); i++)
                                    {
                                        stockRepository.decreaseStock(
                                                targetStockList.get(i).getStock_id(),
                                                targetStockList.get(i).getQuantity()
                                        );
                                        saleRepository.recordSale(
                                                targetStockList.get(i).getProduct_id(),
                                                targetStockList.get(i).getQuantity(),
                                                productRepository.getProductByID(targetStockList.get(i).getProduct_id()).getPrice() * targetStockList.get(i).getQuantity()
                                        );
                                    }
                                    targetStockList.clear();
                                    change = customerCash - price;

                                    Main.cash += price - change;
                                    view.promptPaymentDone(customerCash, price);
                                    MessageBox.showEnterToContinue(input, output);
                                }
                                else
                                {
                                    view.promptNotEnoughCustomerCash(price);
                                    MessageBox.showEnterToContinue(input, output);
                                }

                                break;
                            case "3": //뒤로가기
                                break;
                        }
                    }

                    // 1. 결제 수단 선택
                    // 2. 결제 수단 금액과 결제할 금액 비교
                    // 3. 결제 시 targetStockList 처리, StockRepository에서 처리할 것.
                    // 4. 결제 처리
                    break;
                case "5":
                    controller = new MainMenuController(input, output);
                    break;
                default:
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
