package view;

import app.Main;
import io.OutputRenderer;
import model.Product;
import model.ProductRepository;
import model.Stock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentView implements View
{
    private final OutputRenderer output;

    public PaymentView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void displayBanner()
    {
        output.println("===========================");
        output.println("      [EASY POS 결제]      ");
        output.println("---------------------------");
    }

    @Override
    public void displayMenu()
    {
        output.println("1. 상품 등록");
        output.println("2. 수량 변경");
        output.println("3. 상품 취소");
        output.println("4. 결제");
        output.println("5. 결제 취소");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void promptStockList(int price)
    {
        output.println("---------------------------");
        output.printf("총액: %d\n", price);
        output.println("---------------------------");
    }

    public void showStockList(Stock targetStock)
    {
        ProductRepository productRepository = new ProductRepository();
        output.printf("%d) %s (%d개) (%s)\n", targetStock.getStock_id(), productRepository.getProductByID(targetStock.getProduct_id()).getName(), targetStock.getQuantity(), targetStock.getExpiration_date());
    }

    public void promptInputProduct()
    {
        output.println("등록할 상품 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptInputProductQuantity()
    {
        output.println("구매할 갯수를 입력하세요.");
        output.print("갯수: ");
    }

    public void promptNotEnoughQuantity()
    {
        output.println("수량을 다시 확인해주세요.");
    }


    public void promptInputTargetStockToChangeQuantity()
    {
        output.println("수량을 변경할 상품을 선택하세요.");
    }

    public void promptInputTargetStockToRemove()
    {
        output.println("제외시킬 상품을 선택하세요.");
    }

    public void promptPayment(int price)
    {
        output.println("===========================");
        output.printf("총액: %d\n", price);
        output.println("---------------------------");
        output.println("1. 현금");
        output.println("2. 카드");
        output.println("3. 뒤로 가기");
        output.println("---------------------------");
        output.print("결제수단을 선택해주세요:");
    }

    public void promptPaymentDone(int customerCash, int price)
    {
        output.println("===========================");
        output.println("결제가 완료되었습니다.");
        output.printf("받은 금액: %d\n", customerCash);
        output.printf("결제 금액: %d\n", price);
        output.printf("거스름 돈: %d\n", customerCash - price);
        output.printf("현재 잔고: %d\n", Main.cash);
    }

    public void promptNotEnoughCustomerCash(int price)
    {
        output.println("===========================");
        output.println("카드의 잔액이 부족합니다.");
        output.printf("결제 금액: %d\n", price);
    }

    public void promptAdult()
    {
        output.println("===========================");
        output.println("결제 상품 중에 성인물품이 포함되어 있습니다.");
        output.println("성인인증을 진행하십시오.");
    }
}
