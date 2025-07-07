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
    public void displayBanner()
    {
        output.println("===========================");
        output.println("    [EASY POS 상품 등록]     ");
        output.println("---------------------------");
    }

    @Override
    public void displayMenu()
    {
        output.println("1. 상품 ID로 등록");
        output.println("2. 입고 ID로 등록");
        output.println("3. 뒤로가기");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void promptInputProduct()
    {
        output.println("상품 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptInputDelivery()
    {
        output.println("입고 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptInputProductAmount()
    {
        output.println("수량을 입력하세요.");
        output.print("수량: ");
    }
    public void promptNotEnoughAmount()
    {
        output.println("최소 수량은 10개입니다.");
    }
}
