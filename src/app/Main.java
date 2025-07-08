package app;

import controller.Controller;
import controller.LoginStoreController;
import io.ConsoleInputProvider;
import io.ConsoleOutputRenderer;
import io.InputProvider;
import io.OutputRenderer;
import model.DeliveryRepository;
import model.Staff;
import model.Store;

import java.util.Random;

public class Main
{
    public static Store store;
    public static Staff staff;
    public static int cash;

    public static Thread deliveryThread = new Thread();

    public static void main(String[] args)
    {
        // 발주 신청 이후, 입고 예정 품목들은 랜덤한 시간이 지나면 입고 품목으로 넘어가며
        // 이를 쓰레드로 구현
        DeliveryRepository deliveryRepository = new DeliveryRepository();
        new Thread(() -> {
            while (true) {
                try {
                    int delay = 10 + new Random().nextInt(21); // 10~30초 랜덤
                    Thread.sleep(delay * 1000L);

                    deliveryRepository.simulateIncomingDeliveries();
                } catch (InterruptedException e) {
                    break; // 쓰레드 종료
                }
            }
        }).start();

        // I/O 시스템 추상화
        InputProvider input = new ConsoleInputProvider();
        OutputRenderer output = new ConsoleOutputRenderer();

        // 컨트롤러 초기화
        Controller controller = new LoginStoreController(input, output);
        controller.run();
    }

    public static void initCash()
    {
        cash = 1234000;
    }

}


