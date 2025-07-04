package view;

import io.OutputRenderer;

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

    public void promptInputProduct()
    {
        output.println("등록할 상품 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptSelectProductToChangeAmount()
    {
        output.println("수량을 변경할 상품을 선택하세요.");
    }

    public void promptRemoveProduct()
    {
        output.println("제외시킬 상품을 선택하세요.");
    }

    public void promptPayment()
    {
        output.println("결제를 진행하시겠습니까?");
    }

    public void promptCancel()
    {
        output.println("결제를 취소하시겠습니까? (Y, N)");
        output.print("입력: ");
    }
}
