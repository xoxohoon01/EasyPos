package view;

import io.OutputRenderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeLogView implements View
{
    private final OutputRenderer output;

    public TradeLogView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void display()
    {
        output.println("===========================");
        output.println("    [EASY POS 매출 확인]     ");
        output.println("---------------------------");
        output.println("1. 오늘 매출 확인");
        output.println("2. 지난 매출 확인");
        output.println("3. 뒤로가기");
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }
}
