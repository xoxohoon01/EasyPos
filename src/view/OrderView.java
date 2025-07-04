package view;

import io.OutputRenderer;
import model.Order;
import model.Product;
import model.ProductRepository;

import java.util.List;

public class OrderView implements View
{
    private final OutputRenderer output;

    public OrderView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void displayBanner()
    {
        output.println("===========================");
        output.println("    [EASY POS 발주 신청]     ");
        output.println("---------------------------");
    }

    public void displayMenu()
    {
        output.println("1. 상품 등록");
        output.println("2. 상품 조회");
        output.println("3. 발주 결정");
        output.println("4. 취소");
    }

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

    public void promptInputProductAmount()
    {
        output.println("수량을 입력하세요.");
        output.print("수량: ");
    }

    public void promptOrderList(List<Order> orderList)
    {
        if (orderList.isEmpty()) return;
        output.println("발주 목록");
        ProductRepository productRepository = new ProductRepository();
        for (int i = 0; i < orderList.size(); i++)
        {
            output.printf("%d: %s\tx%d개\n", i, productRepository.getProductByID(orderList.get(i).getProduct_id()).getName(), orderList.get(i).getQuantity());
        }
        output.println("---------------------------");
    }

    public void promptProductList()
    {
        output.println("===========================");
        output.println("    [EASY POS 상품 조회]     ");
        output.println("---------------------------");
    }

    public void promptCheckout()
    {
        output.println("발주 신청을 진행하시겠습니까? (Y, N)");
        output.print("입력: ");
    }

    public void promptDone()
    {
        output.println("발주 신청을 완료했습니다.");
    }

    public void promptCancel()
    {
        output.println("신청을 취소하시겠습니까? (Y, N)");
        output.print("입력: ");
    }

    public void showProductInfo(Product targetProduct)
    {
        output.printf("%d: %s\n", targetProduct.getProduct_id(), targetProduct.getName());
    }
}
