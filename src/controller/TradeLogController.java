package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.ProductRepository;
import model.Sale;
import model.SaleRepository;
import view.TradeLogView;

import java.util.List;

public class TradeLogController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final TradeLogView view;

    public TradeLogController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new TradeLogView(output);
    }

    @Override
    public void run()
    {
        view.displayBanner();
        view.displayMenu();
        view.displayLast();

        ProductRepository productRepository = new ProductRepository();
        SaleRepository saleRepository = new SaleRepository();

        Controller controller = null;
        String choice = input.readLine();

        switch (choice)
        {
            case "1": // 오늘 매출 확인
                view.promptTodaySaleList();
                List<Sale> todaySaleList = saleRepository.getTodaySaleList();
                if (!todaySaleList.isEmpty())
                {
                    for (int i = 0; i < todaySaleList.size(); i++)
                    {
                        view.showSaleInfo(productRepository.getProductByID(todaySaleList.get(i).getProduct_id()), todaySaleList.get(i));
                    }
                    MessageBox.showEnterToContinue(input, output);
                }
                else
                {
                    view.promptNoSale();
                    MessageBox.showEnterToContinue(input, output);
                }
                break;
            case "2": // 전체 매출 확인
                view.promptSaleList();
                List<Sale> saleList = saleRepository.getSaleList();
                if (!saleList.isEmpty())
                {
                    for (int i = 0; i < saleList.size(); i++)
                    {
                        view.showSaleInfo(productRepository.getProductByID(saleList.get(i).getProduct_id()), saleList.get(i));
                    }
                    MessageBox.showEnterToContinue(input, output);
                }
                else
                {
                    view.promptNoSale();
                    MessageBox.showEnterToContinue(input, output);
                }
                break;
            case "4": // 날짜별 매출 정보 확인
                view.promptSaleListByDate();
                try
                {
                    String[] context = input.readLine().split("-");
                    int year = Integer.parseInt(context[0]);
                    int month = Integer.parseInt(context[1]);
                    int day = Integer.parseInt(context[2]);
                    List<Sale> targetSaleList = saleRepository.getSaleListByDate(year, month, day);

                    int total = 0;
                    for (Sale sale : targetSaleList)
                    {
                        total += productRepository.getProductByID(sale.getProduct_id()).getPrice() * sale.getQuantity();
                        view.showSaleInfo(productRepository.getProductByID(sale.getProduct_id()), sale);
                    }
                    view.showSaleTotal(total);
                    MessageBox.showEnterToContinue(input, output);
                }
                catch (NullPointerException e)
                {
                    MessageBox.showWarningByWrongInput(input, output);
                }


                break;

            case "3": // 직원 정보 확인
                view.promptStaffInfo();
                break;
        }
    }


}
