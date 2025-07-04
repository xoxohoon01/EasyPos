package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.StaffRepository;
import view.MainMenuView;

public class MainMenuController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final MainMenuView view;

    public MainMenuController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new MainMenuView(output);
    }

    @Override
    public void run()
    {
        while (true)
        {
            view.displayBanner();
            view.displayMenu();
            view.displayLast();

            Controller controller = null;
            String choice = input.readLine();
            switch (choice)
            {
                case "1":
                    // 제품입력 컨트롤러
                    //controller = new RegisterStockController(input, output);
                    break;
                case "2":
                    break;
                case "3":
                    controller = new OrderController(input, output);
                    break;
                case "6":
                    view.showLeave();
                    String answer = input.readLine();
                    if (answer.equals("Y"))
                    {
                        StaffRepository staffRepository = new StaffRepository();
                        staffRepository.logout();
                        controller = new LoginStaffController(input, output);
                    }
                    else if (answer.equals("N"))
                    {
                        controller = new MainMenuController(input, output);
                        break;
                    }
                    else
                    {
                        MessageBox.showWarningByWrongInputGotoMainMenu(input, output);
                        controller = new MainMenuController(input, output);
                    }
            }

            try
            {
                controller.run(); // 선택한 새로운 컨트롤러 실행
            }
            catch (NullPointerException e)
            {
                MessageBox.showWarningByWrongInput(input, output);
            }
        }
    }
}
