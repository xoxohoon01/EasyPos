package view;

import io.OutputRenderer;
import model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TradeLogView implements View
{
    private final OutputRenderer output;

    public TradeLogView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void displayBanner()
    {
        output.println("===========================");
        output.println("    [EASY POS 매출 확인]     ");
        output.println("---------------------------");
    }

    @Override
    public void displayMenu()
    {
        output.println("1. 오늘 매출 확인");
        output.println("2. 전체 매출 확인");
        output.println("3. 예상 급여 확인");
        output.println("4. 뒤로가기");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void promptNoSale()
    {
        output.println("===========================");
        output.println("매출 정보가 없습니다.");
    }

    public void promptTodaySaleList()
    {
        output.println("===========================");
        output.println("오늘의 매출 정보입니다.");
        output.println("---------------------------");
    }

    public void promptSaleList()
    {
        output.println("===========================");
        output.println("전체 매출 정보입니다.");
        output.println("---------------------------");
    }

    public void showSaleInfo(Product targetProduct, Sale targetSale)
    {
        output.printf("거래 ID: %d\n", targetSale.getSale_id());
        output.printf("상품명: %s\n", targetProduct.getName());
        output.printf("갯수: %d개\n", targetSale.getQuantity());
        output.printf("매출액: %d원\n", targetProduct.getPrice() * targetSale.getQuantity());
        output.printf("거래 날짜: %s\n", targetSale.getSale_date());
        output.println("---------------------------");
    }

    public void promptStaffInfo() {
        int totalMinutes = 0;
        WorkRepository workRepository = new WorkRepository();
        List<Work> workList = workRepository.getWorkList();

        Timestamp enterTime = null;

        if (!workList.isEmpty()) {
            for (Work work : workList) {
                String cause = work.getCause();
                Timestamp logTime = work.getLog_date();

                if ("Leave".equalsIgnoreCase(cause)) {
                    enterTime = logTime; // 출근 시각 저장
                } else if ("Enter".equalsIgnoreCase(cause) && enterTime != null) {
                    long diffMillis = logTime.getTime() - enterTime.getTime();
                    int minutes = (int) (diffMillis / (1000 * 60)); // 분 단위 계산
                    totalMinutes += minutes;

                    System.out.printf("[근무] %s → %s, 근무시간: %d분%n",
                            enterTime, logTime, minutes);

                    enterTime = null; // 짝지은 후 초기화
                }
            }
        }

        double hours = totalMinutes / 60.0;
        int hourlyWage = 11000; // 예: 시급 10,000원
        int salary = (int) (hours * hourlyWage);

        System.out.printf("총 근무 시간: %.2f시간, 예상 급여: %,d원%n", hours, salary);
    }

}
