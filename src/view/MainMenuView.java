package view;

import io.OutputRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainMenuView implements View
{
    private final OutputRenderer output;

    public MainMenuView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void displayBanner()
    {
        output.println("===========================");
        output.println("      [EASY POS 로그인]     ");
        output.println("---------------------------");
    }

    @Override
    public void displayMenu()
    {
        output.println("1. 상품 등록");
        output.println("2. 물품 조회");
        output.println("3. 발주 등록");
        output.println("4. 계산");
        output.println("5. 매출 확인");
        output.println("6. 종료");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void showLeave()
    {
        output.printf("현재 시간: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        output.println("종료하시겠습니까? (Y, N)");
        output.print("입력: ");
    }
}
