package controller;

import app.Main;
import app.MessageBox;
import io.InputProvider;
import io.OutputRenderer;
import model.Staff;
import model.StaffRepository;
import view.LoginStaffView;

public class LoginStaffController implements Controller
{
    private final InputProvider input;
    private final OutputRenderer output;
    private final LoginStaffView view;

    StaffRepository staffRepository;

    public LoginStaffController(InputProvider input, OutputRenderer output)
    {
        this.input = input;
        this.output = output;
        this.view = new LoginStaffView(output);
        staffRepository = new StaffRepository();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                view.displayBanner();

                view.promptStaffId();
                int staffID = Integer.parseInt(input.readLine());
                if (staffRepository.checkStaffID(staffID)) // 올바른 스토어 ID를 입력한 경우
                {
                    view.promptStaffPassword();
                    String password = input.readLine();

                    Main.staff = staffRepository.login(staffID, password);
                    if (Main.staff == null) // PASSWORD가 틀렸을 경우
                    {
                        view.showLoginFailedByPassword();
                        MessageBox.showEnterToContinue(input, output);
                        continue;
                    }
                    // 스토어 ID와 패스워드를 올바르게 입력했을 경우 try-catch문 탈출

                }
                else // 잘못된 스태프 ID를 입력한 경우
                {
                    view.showLoginFailedByID();
                    MessageBox.showEnterToContinue(input, output);
                    continue;
                }
            }
            catch (NullPointerException e)
            {
                MessageBox.showWarningByWrongInput(input, output);
                MessageBox.showEnterToContinue(input, output);
                continue;
            }

            // 스태프 ID와 패스워드를 모두 올바르게 입력했을 경우에만 실행됨
            view.showLoginSuccess();
            MessageBox.showEnterToContinue(input, output);
            Controller controller = new MainMenuController(input, output);
            controller.run();
            break;
        }
    }
}
