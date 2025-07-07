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

    public static Thread deliveryThread = new Thread();

    public static void main(String[] args)
    {
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

        InputProvider input = new ConsoleInputProvider();
        OutputRenderer output = new ConsoleOutputRenderer();

        Controller controller = new LoginStoreController(input, output);
        controller.run();
    }
}


