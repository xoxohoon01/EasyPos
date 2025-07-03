package controller;

import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
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
            view.display();

            Controller controller = null;
            String choice = input.readLine();
            switch (choice)
            {
                case "1":
                    // 제품입력 컨트롤러
                    //controller = new RegisterStockController(input, output);
                    break;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
                    continue;
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
