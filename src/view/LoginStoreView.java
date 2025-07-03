package view;

import app.Main;
import app.MessageBox;
import io.OutputRenderer;

public class LoginStoreView implements View
{
    private final OutputRenderer output;

    public LoginStoreView(OutputRenderer output)
    {
        this.output = output;
    }

    @Override
    public void display()
    {
        output.println("===========================");
        output.println("         [EASY POS]       ");
        output.println("---------------------------");
        output.println("점포 ID를 입력하세요. (0 입력 시 종료)");
        output.print("ID: ");
    }

    public void promptStoreID()
    {
        output.println("점포 ID를 입력하세요. (0 입력 시 종료)");
        output.print("ID: ");
    }

    public void promptPassword()
    {
        output.println("비밀번호를 입력하세요.");
        output.print("PASSWORD: ");
    }

    public void showLoginSuccess()
    {
        output.println("로그인에 성공했습니다.");
        output.printf("%s입니다. 환영합니다.\n", Main.store.getStore_name());
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
