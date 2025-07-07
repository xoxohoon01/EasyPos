package view;

import io.OutputRenderer;
import model.Product;
import model.Sale;

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
        output.println("3. 뒤로가기");
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
}
