package view;

import app.Main;
import io.OutputRenderer;

public class LoginStaffView implements View
{
    private final OutputRenderer output;
    private boolean isLogin;

    public LoginStaffView(OutputRenderer output)
    {
        this.output = output;
        isLogin = false;
    }


    @Override
    public void display()
    {
        output.println("===========================");
        output.println("      [EASY POS 로그인]     ");
        output.println("---------------------------");
    }

    public void promptStaffId()
    {
        output.print("사원ID를 입력하세요: ");
    }

    public void promptStaffPassword()
    {
        output.print("비밀번호를 입력하세요: ");
    }

    public void showLoginSuccess()
    {
        output.println("로그인에 성공했습니다.");
        output.printf("%s님 환영합니다.\n", Main.staff.getStaff_name());
        output.println("===========================\n");
    }

    public void showLoginFailedByID()
    {
        output.println("ID를 잘못 입력했습니다.");
    }
    public void showLoginFailedByPassword()
    {
        output.println("비밀번호를 잘못 입력했습니다.");
    }
}
