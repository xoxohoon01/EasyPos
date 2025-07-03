package app;

import io.InputProvider;
import io.OutputRenderer;

public class MessageBox
{
    public static void showEnterToContinue(InputProvider input, OutputRenderer output)
    {
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }
    public static void showWarningByWrongInput(InputProvider input, OutputRenderer output)
    {
        output.println("\n잘못된 입력입니다. 다시 입력해주세요.\n");
        output.print("계속하려면 엔터를 누르세요...");
        input.readLine();
        output.println("");
    }
}
