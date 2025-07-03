package view;

import io.OutputRenderer;

public class MainMenuView implements View
{
    private final OutputRenderer output;

    public MainMenuView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void display()
    {
        output.println("===========================");
        output.println("     [편의점 POS 프로그램]    ");
        output.println("---------------------------");
        output.println("1. 제품 입력");
        output.println("2. 물품 조회");
        output.println("3. 발주 등록");
        output.println("4. 계산");
        output.println("5. 매출 확인");
        output.println("6. 종료");
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }
}
