package view;

import io.OutputRenderer;
import model.Work;
import model.WorkRepository;

import java.sql.Timestamp;
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

    public void showLeave(Work lastEnter)
    {
        output.printf("마지막 로그인 시간: %s\n", lastEnter.getLog_date());
        output.printf("현재 시간: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        long diffMillis = new Timestamp(System.currentTimeMillis()).getTime() - lastEnter.getLog_date().getTime();
        int minutes = (int) (diffMillis / (1000 * 60));
        double hours = minutes / 60.0;
        int hourlyWage = 11000; // 예: 시급 10,000원
        int salary = (int) (hours * hourlyWage);

        output.printf("예상 급여: %d\n", salary);
        output.println("종료하시겠습니까? (Y, N)");
        output.print("입력: ");
    }
}
