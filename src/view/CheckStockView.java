package view;

import io.OutputRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckStockView implements View
{
    private final OutputRenderer output;

    public CheckStockView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void displayBanner()
    {
        output.println("===========================");
        output.println("    [EASY POS 물품 조회]    ");
        output.println("---------------------------");
    }

    @Override
    public void displayMenu()
    {
        output.println("1. ID로 조회");
        output.println("2. 분류로 조회");
        output.println("3. 제조사로 조회");
        output.println("4. 뒤로가기");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }


    public void promptSearchByID()
    {
        output.println("상품 ID를 입력하세요.");
        output.print("ID: ");
    }

    public void promptSearchByCategory()
    {
        output.println("카테고리를 입력하세요.");
        output.print("ID: ");
    }

    public void promptSearchByCompany()
    {
        output.println("제조사를 입력하세요.");
        output.print("ID: ");
    }
}
