package view;

import io.OutputRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterStockView implements View
{
    private final OutputRenderer output;

    public RegisterStockView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void display()
    {
        output.println("===========================");
        output.println("    [EASY POS 상품 등록]     ");
        output.println("---------------------------");
        output.println("1. 상품 입력");
        output.println("2. 입력 결정");
        output.println("3. 취소");
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void showLeave()
    {
        output.printf("현재 시간: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        output.println("종료하시겠습니까?");
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
        output.println("입력을 완료하시겠습니까? (Y, N)");
        output.print("입력: ");
    }

    public void promptCancel()
    {
        output.println("등록을 취소하시겠습니까? (Y, N)");
        output.print("입력: ");
    }
}
