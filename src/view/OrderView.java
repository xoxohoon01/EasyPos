package view;

import io.OutputRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderView implements View
{
    private final OutputRenderer output;

    public OrderView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void display()
    {
        output.println("===========================");
        output.println("    [EASY POS 발주 신청]     ");
        output.println("---------------------------");
        output.println("1. 상품 등록");
        output.println("2. 상품 조회");
        output.println("3. 발주 결정");
        output.println("4. 취소");
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void promptInputProduct()
    {
        output.println("상품 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptInputProductAmount()
    {
        output.println("수량을 입력하세요.");
        output.print("수량: ");
    }

    public void promptCheckout()
    {
        output.println("발주 신청을 진행하시겠습니까? (Y, N)");
        output.print("입력: ");
    }

    public void promptCancel()
    {
        output.println("신청을 취소하시겠습니까? (Y, N)");
        output.print("입력: ");
    }
}
