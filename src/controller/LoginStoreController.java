package controller;

import app.Main;
import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.StoreRepository;
import oracle.net.ns.Message;
import view.LoginStoreView;

public class LoginStoreController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final LoginStoreView view;

    StoreRepository storeRepository;

    public LoginStoreController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new LoginStoreView(output);
        storeRepository = new StoreRepository();
    }

    @Override
    public void run()
    {
        while (true)
        {
            view.display();

            try
            {
                view.promptStoreID();
                int storeID = Integer.parseInt(input.readLine());
                if (storeRepository.checkStoreID(storeID)) // 올바른 스토어 ID를 입력한 경우
                {
                    output.print("PASSWORD: ");
                    String password = input.readLine();

                    Main.store = storeRepository.login(storeID, password);
                    if (Main.store == null) // PASSWORD가 틀렸을 경우
                    {
                        view.showLoginFailedByPassword();
                        MessageBox.showEnterToContinue(input, output);
                        continue;
                    }
                    // 스토어 ID와 패스워드를 올바르게 입력했을 경우 try-catch문 탈출

                }
                else // 잘못된 스토어 ID를 입력한 경우
                {
                    view.showLoginFailedByID();
                    MessageBox.showEnterToContinue(input, output);
                    continue;
                }
            }
            catch (NullPointerException e)
            {
                MessageBox.showWarningByWrongInput(input, output);
                continue;
            }

            // 스토어 ID와 패스워드를 모두 올바르게 입력했을 경우에만 실행됨
            view.showLoginSuccess();
            MessageBox.showEnterToContinue(input, output);
            Controller controller = new LoginStaffController(input, output);
            controller.run();
            break;
        }
    }
}
