import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class searchQuery extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {


        Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedString = caretModel.getCurrentCaret().getSelectedText();

        String result = JOptionPane.showInputDialog(
                "Сформируйте запрос",
                selectedString);

        JCheckBox check = new JCheckBox("Подтверждаю");
        Object[] options = {"Google", "Yandex", "Yahoo", "Bing", check};

        int x = JOptionPane.showOptionDialog(null, "Выберете поисковик",
                "Поисковик",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);


        if (check.isSelected() && x != -1) {
            System.out.println("Your choice was " + options[x]);
        } else {
            System.out.println(":( no choice");
        }

        System.out.println(result);

    }
}