package view;

import io.OutputRenderer;
import model.Product;
import model.ProductRepository;
import model.Stock;
import model.StockRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        output.println("1. 재고 조회");
        output.println("2. 상품 조회");
        output.println("3. 뒤로가기");
    }

    @Override
    public void displayLast()
    {
        output.println("===========================");
        output.print("메뉴를 선택하세요: ");
    }

    public void promptShowStockList()
    {
        output.println("===========================");
        output.println("현재 재고입니다.");
    }

    public void showProductInfo(Product targetProduct)
    {
        output.println("===========================");
        output.printf("상품 ID: %d\n", targetProduct.getProduct_id());
        output.printf("이름: %s\n", targetProduct.getName());
        output.printf("분류: %s\n", targetProduct.getCategory());
        output.printf("제조사: %s\n", targetProduct.getCompany());
        output.printf("성인인증 여부: %s\n", targetProduct.getIsAdult());
        output.println("===========================");
    }

    public void showStockInfo(Stock targetStock)
    {
        output.printf("재고 ID: %d\n", targetStock.getStock_id());
        output.printf("재고 수량: %d\n", targetStock.getQuantity());
        output.printf("입고 날짜: %s\n", targetStock.getRegistered_date());
        output.printf("유통기한: %s\n", targetStock.getExpiration_date());
        output.println("---------------------------");
    }

    public void showStockInfoByList(Product targetProduct, Stock targetStock)
    {
        output.printf("%s: ", targetProduct.getName());
        for(int i = 0; i < targetStock.getQuantity(); i++)
        {
            output.print("*");
        }
        output.printf("\t(%d개)\n", targetStock.getQuantity());
    }

    public void promptInputProductID()
    {
        output.println("===========================");
        output.println("ID를 입력하세요.");
        output.print("ID: ");
    }
}
